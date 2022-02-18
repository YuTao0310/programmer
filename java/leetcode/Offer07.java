
public class Offer07 {
    public static void main(String[] args) {
        int[] pre = new int[]{1, 2};
        int[] in = new int[]{2, 1};
        Solution1 s = new Solution1();
        s.buildTree(pre, in);
    }
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    static class Solution1 {
        int[] preOrder;
        int[] inOrder;
        int pos = 0;
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0) {
                return null;
            }
            preOrder = preorder;
            inOrder = inorder;
            return buildTree(0, preorder.length);
        }
        private TreeNode buildTree(int start, int end) {
            int rootValue = preOrder[pos++];
            TreeNode root = new TreeNode(rootValue);
            int leftStart = start;
            int rightEnd = end;
            int leftEnd = 0;
            int rightStart = 0;
            if ((end - start) == 1) {
                root.left = null;                
                root.right = null;
            } else {
                for (int i = start; i < end; i++) {
                    if (inOrder[i] == rootValue) {
                        leftEnd = i;
                        rightStart = i + 1;
                        break;
                    }
                }
                root.left = (leftStart == leftEnd ? null : buildTree(leftStart, leftEnd));
                root.right = (rightStart == rightEnd ? null : buildTree(rightStart, rightEnd));
            }
            return root;
        }
    }
}

