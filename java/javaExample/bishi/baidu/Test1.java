import java.util.Deque;
import java.util.LinkedList;

public class Test1 {

    static class Node {
        int val;
        Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node addNext(Node next) {
            this.next = next;
            return next;
        }


    }
    public static void main(String[] args) {
        Node root = new Node(1);
        root.addNext(new Node(2)).addNext(new Node(3)).addNext(new Node(4));
        root = reverseList(root);
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.next;
        }
        System.out.println();
    }

    public static Node reverseList(Node root) {
        Deque<Node> stack = new LinkedList<>();
        Node temp = root;
        while (root != null) {
            stack.push(root);
            root = root.next;
        }
        temp.next = null;
        Node res = stack.pop();
        while (!stack.isEmpty()) {
            res.next = stack.pop();
            res = res.next;
        }
        return res;
    }

}