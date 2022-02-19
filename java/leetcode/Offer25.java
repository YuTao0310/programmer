public class Offer25 {

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode pointer1 = l1;
            ListNode pointer2 = l2;
            ListNode pred = null;
            ListNode temp = null;
            if (l1 == null) {
                return l2;
            }
            if (l2 == null) {
                return l1;
            }
            if (pointer1.val > pointer2.val) {
                pred = pointer2;
                pointer2 = pointer2.next;
                pred.next = pointer1;
                l1 = pred;
            }
            while (pointer1 != null && pointer2 != null) {  
                if (pointer1.val > pointer2.val) {
                    temp = pointer2;
                    pointer2 = pointer2.next;
                    pred.next = temp;
                    temp.next = pointer1;
                    pred = temp;
                } else {
                    pred = pointer1;
                    pointer1 = pointer1.next;
                } 
            }     
            if (pointer1 == null) {
                pred.next = pointer2;
            }
            return l1;
        }
    }
}