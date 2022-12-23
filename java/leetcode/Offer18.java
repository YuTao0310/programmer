public class Offer18 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x;}
    }
    class Solution {
        public ListNode deleteNode(ListNode head, int val) {
            ListNode pre = head;
            ListNode pointer = head;
            if (pointer.val == val) {
                return head.next;
            }
            while(pointer != null) {
                if (pointer.val == val) {
                    pre.next = pointer.next;
                    break;
                }
                pre = pointer;
                pointer = pointer.next;
            }
            return head;
        }
    }
}
