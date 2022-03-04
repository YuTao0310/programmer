import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Offer35 {
    class Node {
        int val;
        Node next;
        Node random;
    
        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
    class Solution {
        public Node copyRandomList(Node head) {
            List<Node> list  = new ArrayList<>();
            HashMap<Node, Integer> map = new HashMap<>();
            Node pointer = head;
            int i = 0;
            while(pointer != null) {
                Node n = new Node(pointer.val);
                list.add(n);
                map.put(pointer, i++);
                pointer = pointer.next;
            }
            list.add(null);
            map.put(null, i);
            for (i = 0; i < list.size() - 1; i++) {
                list.get(i).next = list.get(i + 1);
                list.get(i).random = list.get(map.get(head.random));
                head = head.next;
            }
            return list.get(0);
            
        }
    }
}
