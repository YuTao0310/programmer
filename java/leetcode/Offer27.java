public class Offer27 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    /**
     * mirrorTree会改变root对应的树结构
     */
    class Solution1 {
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode temp = root.left;
            root.left = mirrorTree(root.right);
            root.right = mirrorTree(temp);
            return root;
        }
    }
    /**
     * mirrorTree不会改变root对应的树结构
     */
    class Solution2 {
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode mirror = new TreeNode(root.val);
            mirror.left = mirrorTree(root.right);
            mirror.right = mirrorTree(root.left);
            return mirror;
        }
    }
}
