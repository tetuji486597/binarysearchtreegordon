class TreeNode
{
    private final double val;
    private TreeNode left;
    private TreeNode right;

    public double val() {
        return this.val;
    }

    public void left(final TreeNode l) {
        this.left = l;
    }

    public void right(final TreeNode r) {
        this.right = r;
    }

    public TreeNode left() {
        return this.left;
    }

    public TreeNode right() {
        return this.right;
    }

    public TreeNode(final double val) {
        this.val = val;
    }

    public int height() {
        int height = -1;
        TreeNode leftChild;
        for (TreeNode n = this; n != null; n = ((leftChild == null) ? n.right() : leftChild)) {
            ++height;
            leftChild = n.left();
        }
        return height;
    }

    public static int getMaxWidth(final TreeNode node) {
        int maxWidth = 0;
        for (int h = node.height(), i = 1; i <= h; ++i) {
            final int width = getWidth(node, i);
            if (width > maxWidth) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }

    private static int getWidth(final TreeNode node, final int level) {
        if (node == null) {
            return 0;
        }
        if (level == 1) {
            return 1;
        }
        if (level > 1) {
            return getWidth(node.left, level - 1) + getWidth(node.right, level - 1);
        }
        return 0;
    }

    public int visualWidthInDirection(final int leftOrRight, final int x, final int depth, final int maxDepth) {
        if (depth > maxDepth) {
            return x;
        }
        final TreeNode r = this.right();
        final TreeNode l = this.left();
        int biggestX = x;
        if (r != null) {
            biggestX = Math.max(biggestX, r.visualWidthInDirection(leftOrRight, x + leftOrRight * 2 - 1, depth + 1, maxDepth));
        }
        if (l != null) {
            biggestX = Math.max(biggestX, l.visualWidthInDirection(leftOrRight, x + (leftOrRight + 1) % 2 * 2 - 1, depth + 1, maxDepth));
        }
        System.out.println(biggestX);
        return biggestX;
    }

    public int[] graphicBreadth(final int maxDepth) {
        return this.graphicBreadth(0, maxDepth);
    }

    public int[] graphicBreadth(final int depth, final int maxDepth) {
        final int[] maxBreadth = { 0, 0 };
        if (depth > maxDepth) {}
        final TreeNode r = this.right();
        final TreeNode l = this.left();
        if (r != null) {
            final int[] breadth = r.graphicBreadth(depth + 1, maxDepth);
            maxBreadth[1] = breadth[0] + breadth[1] + 1;
        }
        if (l != null) {
            final int[] breadth = l.graphicBreadth(depth + 1, maxDepth);
            maxBreadth[0] = breadth[0] + breadth[1] + 1;
        }
        return maxBreadth;
    }
}