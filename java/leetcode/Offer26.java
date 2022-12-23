public class Offer26 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    class Solution {
        public boolean isSubStructure(TreeNode A, TreeNode B) {
            if (B == null) {
                return false;
            }
            return isSubStructure1(A, B);
        }
        public boolean isSubStructure1(TreeNode A, TreeNode B) {
            boolean res, res1;
            if (A == null && B != null) {
                return false;
            }
            if (B == null) {
                return true;
            }
            
            res1 = isFirstPart(A, B);
    
            res = res1 || isSubStructure(A.left, B) || isSubStructure(A.right, B);
            return res;
        }
        public boolean isFirstPart(TreeNode A, TreeNode B) {
            boolean res = false;
            if (A == null && B != null) {
                return false;
            }
            if (B == null) {
                return true;
            }
            if (A.val != B.val) {
                return false;
            }
            res = isFirstPart(A.left, B.left) && isFirstPart(A.right, B.right);
            return res;
        }
    }
}