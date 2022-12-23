public class Offer55I {
    class Solution {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        int depth;
        public int maxDepth(TreeNode root) {
            this.depth = 0;
            dfs(root, 0);
            return depth;
        }
        public void dfs(TreeNode root, int d) {
            if (root == null) {
                depth = Math.max(depth, d);
                return ;
            }
            dfs(root.left, ++d);
            dfs(root.right, d);
        }
    }
}
