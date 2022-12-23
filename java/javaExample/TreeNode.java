import java.util.ArrayList;
import java.util.List;

public class TreeNode {
	int data;
	TreeNode leftNode;
	TreeNode rightNode;
	public TreeNode() {
		
	}
	public TreeNode(int d) {
		data=d;
	}
	
	public TreeNode(TreeNode left,TreeNode right,int d) {
		leftNode=left;
		rightNode=right;
		data=d;
	}
	public static void main(String[] args) {
		TreeNode root = new TreeNode(2);
		System.out.println(root.leftNode);
		List<TreeNode>  test  = new ArrayList<>();
	}
}