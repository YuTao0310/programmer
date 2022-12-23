public class Offer54 {
    class Solution {
        public class TreeNode {
            int val;
            TreeNode left;
            TreeNode right;

            TreeNode(int x) {
                val = x;
            }
        }

        int K, ans;

        public int kthLargest(TreeNode root, int k) {
            K = k;
            dfs(root);
            return ans;
        }

        public void dfs(TreeNode root) {
            if (root == null) {
                return;
            }
            if (K != 0) {
                dfs(root.right);
                K--;
                if (K == 0) {
                    ans = root.val;
                    return;
                }
                dfs(root.left);
            }
        }
    }
}
