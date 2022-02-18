
public class GraphicBinaryTreeRunner
{
    public static void main(final String[] args) {
        new GraphicBinaryTreeWindow();
    }

    public static String fmt(final double d) {
        if (d == (long)d) {
            return String.format("%d", (long)d);
        }
        return String.format("%s", d);
    }
}