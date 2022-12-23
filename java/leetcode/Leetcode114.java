import java.util.ArrayList;
import java.util.List;

public class Leetcode114 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
    class Solution {
        List<TreeNode> traverse = new ArrayList<>();
        public void flatten(TreeNode root) {
            dfs(root);
            for (int i = 1; i < traverse.size(); i++) {
                root.left = null;
                root = root.right = traverse.get(i);
            }
        }
        private void dfs(TreeNode node) {
            if (node == null) return ;
            traverse.add(node);
            dfs(node.left);
            dfs(node.right);
        }
    }
}
