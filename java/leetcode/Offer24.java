
public class Offer24 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
    class Solution {
        public ListNode reverseList(ListNode head) {
            ListNode pre = null;
            while (head != null) {
                ListNode tmp = head.next;
                head.next = pre;
                pre = head;
                head = tmp;
            }
            return pre;
        }
    }
}
