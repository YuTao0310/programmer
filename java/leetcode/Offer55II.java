public class Offer55II {
    class Solution {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        boolean ans = true;
        public boolean isBalanced(TreeNode root) {
            if (ans == false || root == null) {
                return ans;
            }
            ans = Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 ? true : false;
            ans = ans && isBalanced(root.left);
            ans = ans && isBalanced(root.right);
            return ans;
        }

        public int maxDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
        }
    }
}
