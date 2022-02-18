public class BSTcontroller
{
    private TreeNode treeRoot;
    private UserInputPanel controls;
    private GraphicBinaryTreeVisualizer view;

    public BSTcontroller(final UserInputPanel controls, final GraphicBinaryTreeVisualizer view) {
        this.controls = controls;
        this.view = view;
        controls.setController(this);
    }

    public void addNode(final double num) {
        final TreeNode newNode = new TreeNode(num);
        if (this.treeRoot == null) {
            this.treeRoot = newNode;
        }
        else {
            TreeNode n = this.treeRoot;
            while (true) {
                if (newNode.val() <= n.val()) {
                    final TreeNode l = n.left();
                    if (l == null) {
                        n.left(newNode);
                        break;
                    }
                    n = l;
                }
                else {
                    final TreeNode r = n.right();
                    if (r == null) {
                        n.right(newNode);
                        break;
                    }
                    n = r;
                }
            }
        }
        this.view.update(this.treeRoot);
    }
}