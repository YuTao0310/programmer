import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Leetcode94 {
    class Solution {
        public List<Integer> inorderTraversal(TreeNode root) {
            if (root == null) return new ArrayList<>();
            List<Integer> ans = new ArrayList<>();
            Deque<TreeNode> visit = new ArrayDeque<>();
            TreeNode mid = root;
            
            while(!visit.isEmpty() || mid != null) {
                while (mid != null) {
                    visit.push(mid);
                    mid = mid.left;
                }
                TreeNode tmp = visit.pop();
                ans.add(tmp.val);
                mid = tmp.right;
            }
            return ans;
        }
    }
}
