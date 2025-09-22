import com.sun.source.tree.Tree;

public class MaxPathSumBinaryTree {
    public static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int val) {
            this.val = val;
        }
    }
    static int maxSum;

    public static int maxPathSum(TreeNode root) {
        maxSum = Integer.MIN_VALUE;
        dfs(root);
        return maxSum;
    }
    private static int dfs(TreeNode node) {
        if (node == null) return 0;

        // if negative path, ignore by taking max with 0
        int left = Math.max(0, dfs(node.left));
        int right = Math.max(0, dfs(node.right));

        // calculate path sum passing through this node
        int currentSum = node.val + left + right;

        // update global max
        maxSum = Math.max(maxSum, currentSum);

        // return max path including current node + one side
        return node.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        // Example Tree
        TreeNode root = new TreeNode(-10);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        System.out.println("Maximum Path Sum" + maxPathSum(root));
    }
}
