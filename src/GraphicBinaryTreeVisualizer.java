import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.JPanel;

//
// Decompiled by Procyon v0.5.36
//

class GraphicBinaryTreeVisualizer extends JPanel
{
    final Dimension screenSize;
    final int width;
    final int height;
    int xCenter;
    final int canvasPadding = 10;
    final int nodeWidth = 40;
    final int nodeHeight = 20;
    final int horizontalGap = 5;
    final int levelGap = 14;
    final int textSize = 14;
    final Point nodePadding;
    final int halfNodeWidth = 20;
    final int halfNodeHeight = 10;
    TreeNode tree;

    public GraphicBinaryTreeVisualizer() {
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.width = Math.min(1024, this.screenSize.width);
        this.height = Math.min(600, this.screenSize.height);
        this.nodePadding = new Point(2, 3);
        this.setPreferredSize(new Dimension(this.width, this.height));
        setBackground(Color.pink);
    }

    public void update(final TreeNode tree) {
        this.tree = tree;
        this.repaint();
    }

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        if (this.tree == null) {
            return;
        }
        this.xCenter = this.getWidth() / 2;
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setFont(new Font("ComicSans", Font.BOLD, 69));
        final AffineTransform originalTransform = g2d.getTransform();
        g2d.translate(0, 10);
        final int[] subtreeWidthBalance = this.tree.graphicBreadth(this.tree.height());
        g2d.translate((this.coordsToPix(new Point(subtreeWidthBalance[0] - subtreeWidthBalance[1], 0)).x - this.xCenter) / 2, 0);
        this.drawTree(this.tree, new Point(0, 1), g2d);
        g2d.setTransform(originalTransform);
    }

    private Point coordsToPix(final Point p) {
        return new Point((int)(this.xCenter + (p.x - 1.0) * 40 + p.x * 5), p.y * 68);
    }

    private void drawTree(final TreeNode node, final Point loc, final Graphics2D g2d) {
        final AffineTransform originalTransform = g2d.getTransform();
        final Point pixelLoc = this.coordsToPix(loc);
        g2d.drawString(GraphicBinaryTreeRunner.fmt(node.val()), pixelLoc.x + this.nodePadding.x, pixelLoc.y - this.nodePadding.y);
        g2d.drawOval(pixelLoc.x-15, pixelLoc.y-60, 120, 60);
        g2d.translate(20, 0);
        final TreeNode lChild = node.left();
        final TreeNode rChild = node.right();
        final TreeNode[] children = { lChild, rChild };
        for (int i = children.length - 1; i >= 0; --i) {
            final TreeNode n = children[i];
            if (n != null) {
                final int otherDirection = (i + 1) % 2;
                final TreeNode otherSide = children[otherDirection];
                final int[] shift = n.graphicBreadth(Math.min(n.height(), (otherSide == null) ? 0 : otherSide.height()));
                final Point shiftedLoc = new Point(loc.x + ((i == 0) ? (-shift[1] - 1) : (shift[0] + 1)), loc.y + 1);
                final Point shiftedLocPix = this.coordsToPix(shiftedLoc);
                final Point connectLineStart = (Point)pixelLoc.clone();
                final Point connectLineEnd = (Point)shiftedLocPix.clone();
                final int dx = shiftedLoc.x - loc.x;
                final int thirdNodeWidth = 13;
                final int sixthNodeWidth = 6;
                if (dx >= 1) {
                    final Point point = connectLineStart;
                    point.x += sixthNodeWidth;
                    final Point point2 = connectLineEnd;
                    point2.x -= sixthNodeWidth;
                }
                if (dx <= -1) {
                    final Point point3 = connectLineStart;
                    point3.x -= sixthNodeWidth;
                    final Point point4 = connectLineEnd;
                    point4.x += sixthNodeWidth;
                }
                if (dx >= 10 || dx <= -10) {
                    final int thirdNodeHeight = 6;
                    final Point point5 = connectLineStart;
                    point5.y -= thirdNodeHeight;
                    final Point point6 = connectLineEnd;
                    point6.y += thirdNodeHeight;
                }
                if (dx >= 3) {
                    final Point point7 = connectLineStart;
                    point7.x += thirdNodeWidth;
                    final Point point8 = connectLineEnd;
                    point8.x -= thirdNodeWidth;
                }
                if (dx <= -3) {
                    final Point point9 = connectLineStart;
                    point9.x -= thirdNodeWidth;
                    final Point point10 = connectLineEnd;
                    point10.x += thirdNodeWidth;
                }
                g2d.drawLine(connectLineStart.x, connectLineStart.y, connectLineEnd.x, connectLineEnd.y - 20);
                g2d.setTransform(originalTransform);
                this.drawTree(n, shiftedLoc, g2d);
            }
        }
    }
}