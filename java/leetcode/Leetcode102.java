import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Leetcode102 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
    class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            List<List<Integer>> ans = new ArrayList<>();
            Deque<TreeNode> queue = new ArrayDeque<>();
            if (root != null) queue.offer(root);
            while (!queue.isEmpty()) {
                List<Integer> temp = new ArrayList<>();
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    temp.add(node.val);
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                ans.add(temp);
            }
            return ans;
        }
    } 
}
