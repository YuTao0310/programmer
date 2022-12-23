import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offer37 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
    public class Codec {
        // Encodes a tree to a single string.
        public Integer[] serialize(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return new Integer[]{};
            }
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while(!q.isEmpty()) {
                TreeNode node = q.poll();
                if (node == null) {
                    list.add(null);
                    continue;
                }
                list.add(node.val);
                q.add(node.left);
                q.add(node.right);

            }   
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i) == null) {
                    list.remove(i);
                } else {
                    break;
                }
            }
            Integer[] tmp = new Integer[list.size()];
            return list.toArray(tmp);
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(Integer[] data) {
            TreeNode head = null;
            int length = data.length;
            if (length == 0) {
                return head;
            }
            Queue<TreeNode> q = new LinkedList<>();
            head = new TreeNode(data[0]);
            q.add(head);
            int i = 1;
            while(!q.isEmpty()) {
                TreeNode node = q.poll();
                if (i < length) {
                    if (data[i] != null) {
                        node.left = new TreeNode(data[i]);
                        q.add(node.left);
                    } 
                    i++;
                }
                if (i < length) {
                    if (data[i] != null) {
                        node.right = new TreeNode(data[i]);
                        q.add(node.right);
                    } 
                    i++;
                }
            }
            return head;
        }
    }
}
