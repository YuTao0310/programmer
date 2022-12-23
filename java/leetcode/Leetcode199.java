import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import Offer26.TreeNode;


/*
 * @lc app=leetcode.cn id=199 lang=java
 *
 * [199] 二叉树的右视图
 */

// @lc code=start
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        List<Integer> rights = new ArrayList<>();
        Queue<TreeNode> visited = new LinkedList<>();
        visited.offer(root);
        while (!visited.isEmpty()) {
            int len = visited.size();
            for (int i = 0; i < len; i++) {
                TreeNode temp = visited.poll();
                if (i == 0) {
                    rights.add(temp.val);
                }
                if (temp.right != null) {
                    visited.add(temp.right);
                }
                if (temp.left != null) {
                    visited.add(temp.left);
                }

            }
        }
        return rights;
    }
}
// @lc code=end
