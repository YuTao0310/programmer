import java.util.Stack;

public class Offer22 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
    class Solution {
        public ListNode getKthFromEnd(ListNode head, int k) {
            Stack<ListNode> s = new Stack<>();
            while(head != null) {
                s.push(head);
                head = head.next;
            }
            for (int i = 0; i < k - 1; i++) {
                s.pop();
            }
            return s.peek();
        }
    }
}