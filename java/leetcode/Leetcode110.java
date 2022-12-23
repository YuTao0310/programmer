import javax.swing.text.rtf.RTFEditorKit;

/*
 * @lc app=leetcode.cn id=110 lang=java
 *
 * [110] 平衡二叉树
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isBalanced(TreeNode root) {
       return isBalanced1(root) != -1;
    }
    public int isBalanced1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = isBalanced1(root.left);
        if (left == -1) {
            return -1;
        }
        int right = isBalanced1(root.right);
        if (right == -1) {
            return -1;
        }
        return Math.abs(left - right) <= 1 ? Math.max(left, right) + 1 : -1;
    }
}
// @lc code=end

