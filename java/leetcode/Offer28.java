public class Offer28 {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    static class Solution {
        public boolean isSymmetric(TreeNode root) {
            TreeNode mirror = mirrorTree(root);
            return isTreeEqual(root, mirror);
        }
        public TreeNode mirrorTree(TreeNode root) {
            if (root == null) {
                return null;
            }
            TreeNode mirror = new TreeNode(root.val);
            mirror.left = mirrorTree(root.right);
            mirror.right = mirrorTree(root.left);
            return mirror;
        }
        public boolean isTreeEqual(TreeNode A, TreeNode B) {
            boolean res;
            if (A == null) {
                if (B == null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                if (B == null) {
                    return false;
                } else {
                    if (A.val != B.val) {
                        return false;
                    }
                    res = isTreeEqual(A.left, B.left) && isTreeEqual(A.right, B.right);
                }
            }
            return res;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.right = new TreeNode(1);
        root.right.right = new TreeNode(1);
        Solution s = new Solution();
        System.out.println(s.isSymmetric(root));

    }
}
