import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offer32I {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }

    class Solution {
        public int[] levelOrder(TreeNode root) {
            if (root == null) {
                return new int[0];
            }
            Queue<TreeNode> visited = new LinkedList<>();
            List<Integer> list = new ArrayList<>();
            visited.offer(root);
            while (!visited.isEmpty()) {
                TreeNode temp = visited.poll();
                list.add(temp.val);
                if (temp.left != null) {
                    visited.offer(temp.left);
                }
                if (temp.right != null) {
                    visited.offer(temp.right);
                }
            }
            int[] ans = new int[list.size()];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = list.get(i);
            }
            return ans;
        }
    }

    class Solution1 {
        List<Integer> l1 = new ArrayList<>();

        public int[] levelOrder(TreeNode root) {
            if (root == null)
                return new int[0];
            List<TreeNode> l = new ArrayList<>();
            l.add(root);
            bfs(l);
            int[] ans = new int[l1.size()];
            for (int i = 0; i < ans.length; i++) {
                ans[i] = l1.get(i);
            }
            return ans;
        }
        
        public void bfs(List<TreeNode> children) {
            List<TreeNode> allChildren = new ArrayList<>();
            for (TreeNode node : children) {
                l1.add(node.val);
                if (node.left != null) {
                    allChildren.add(node.left);
                }
                if (node.right != null) {
                    allChildren.add(node.right);
                }
            }
            if (allChildren.size() > 0) {
                bfs(allChildren);
            }
        }
    }
}
