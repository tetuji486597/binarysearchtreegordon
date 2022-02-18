import java.awt.*;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JFrame;

//
// Decompiled by Procyon v0.5.36
//

public class GraphicBinaryTreeWindow extends JFrame
{
    final String title = "Graphic Binary Tree";

    private static JLabel getCenterAlignedJLabel(final String text) {
        final JLabel l = new JLabel(text, 0);
        l.setAlignmentX(0.5f);
        return l;
    }

    public GraphicBinaryTreeWindow() {
        this.setDefaultCloseOperation(3);
        this.setTitle("Graphic Binary Tree");
        this.setBackground(Color.PINK);
        this.setLayout(new BoxLayout(this.getContentPane(), 1));
        final UserInputPanel userInputPanel = new UserInputPanel();
        final GraphicBinaryTreeVisualizer graphicBinaryTreeVisualizer = new GraphicBinaryTreeVisualizer();
        final BSTcontroller controller = new BSTcontroller(userInputPanel, graphicBinaryTreeVisualizer);
        final JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.X_AXIS));
        infoPanel.add(getCenterAlignedJLabel("Please input a node and hit enter on your keyboard to add it to the Binary Tree"));
        this.add(infoPanel);
        this.add(userInputPanel);
        this.add(graphicBinaryTreeVisualizer);
        this.pack();
        this.setVisible(true);
    }
}
