import java.util.ArrayList;
import java.util.List;

public class Offer36 {
    static class Node {
        public int val;
        public Node left;
        public Node right;
    
        public Node() {}
    
        public Node(int _val) {
            val = _val;
        }
    
        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    };
    static class Solution {
        List<Node> path = new ArrayList<>();
        public Node treeToDoublyList(Node root) {
            inOrderTraverse(root);
            int length = path.size();
            if (length == 0) {
                return root;
            }
            if (length == 1) {
                root.left = root;
                root.right = root;
            }
            path.get(0).left = path.get(length - 1);
            path.get(0).right = path.get(1);
            path.get(length - 1).left = path.get(length - 2);
            path.get(length - 1).right = path.get(0);
            for (int i = 1; i < length - 2; i++) {
                path.get(i).left = path.get(i - 1);
                path.get(i).right = path.get(i + 1);
            }
            return path.get(0);
            
        }
        void inOrderTraverse(Node root) {
            if (root == null) {
                return;
            }
            inOrderTraverse(root.left);
            path.add(root);
            inOrderTraverse(root.right);
        }
    }
    public static void main(String[] args) {
        Node node = new Node(4);
        node.left = new Node(2);
        node.right = new Node(5);
        node.left.left = new Node(1);
        node.left.right = new Node(3);
        Solution s = new Solution();
        Node copy = s.treeToDoublyList(node);
        System.out.println(copy);
    }
}
