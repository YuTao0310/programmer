public class Leetcode104 {
    class Solution {
        public int maxDepth(TreeNode root) {
            return maxDepth(root, 0);
        }
        private int maxDepth(TreeNode root, int depth) {
            if (root == null) return depth;
            depth++;
            return Math.max(maxDepth(root.left, depth), maxDepth(root.right, depth));
        }
        
    }
}
