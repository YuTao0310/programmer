import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.naming.LinkException;

public class OfferII44 {
    
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	public TreeNode() {
		
	}
	public TreeNode(int d) {
		val=d;
	}
	
	public TreeNode(TreeNode left,TreeNode right,int d) {
		this.left=left;
		this.right=right;
		val=d;
	}
}

class Solution {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> visit = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        int max = Integer.MIN_VALUE, len = 0;
        TreeNode temp = null;
        visit.offer(root);
        while (!visit.isEmpty()) {
           len = visit.size();
           for (int i = 0; i < len; i++) {
                temp = visit.poll();
                max = Math.max(max, temp.val);
                if (temp.left != null) {
                    visit.add(temp.left);
                }
                if (temp.right != null) {
                    visit.add(temp.right);
                }
           }
           res.add(max);
        }
        return res;
    }
}