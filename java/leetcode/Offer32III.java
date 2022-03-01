import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Offer32III {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    static class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new LinkedList<>();
            }
            List<TreeNode> tmp = new LinkedList<>();
            tmp.add(root);
            List<List<Integer>> res = levelOrder(tmp);
            for(int i=1;i<res.size();i+=2){
                Collections.reverse(res.get(i));
            }
            return res;
        }

        List<List<Integer>> levelOrder(List<TreeNode> list) {
            List<TreeNode> tmpList = new LinkedList<>();
            List<List<Integer>> res = new LinkedList<>();
            List<Integer> tmpRes = new LinkedList<>();

            for (TreeNode node : list) {
                tmpRes.add(node.val);
                if (node.left != null) {
                    tmpList.add(node.left);
                }
                if (node.right != null) {
                    tmpList.add(node.right);
                }
            }
            res.add(tmpRes);
            if (!tmpList.isEmpty()) {
                res.addAll(levelOrder(tmpList));
            }
            return res;
        }
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        Solution s = new Solution();
        List<List<Integer>> res = s.levelOrder(root);
        System.out.println(res);
    }
}
