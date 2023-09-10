import java.util.*;


 class TreeNode {
   int val = 0;
   TreeNode left = null;
   TreeNode right = null;
   public TreeNode(int val) {
     this.val = val;
   }
  }
 

class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     * 返回节点权值1个数比0的个数多一的路径数
     * @param root TreeNode类 权值为0和1的二叉树根节点
     * @return int整型
     */

    int res = 0;
    int[] sum  = new int[2];

    public int pathNumber (TreeNode root) {
        // write code here
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            sum[root.val]++;
            if (sum[1] > sum[0]) res++;
            sum[root.val]--;
            return;
        }
        sum[root.val]++;
        if (root.left != null) dfs(root.left);
        if (root.right != null) dfs(root.right);
        sum[root.val]--;
    }

}

public class Test1 {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(0);
        root.right = new TreeNode(0);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(0);
        root.right.right = new TreeNode(1);
        System.out.println(new Solution().pathNumber(root));
    }
}