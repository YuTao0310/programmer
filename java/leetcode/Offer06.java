import java.util.Stack;

public class Offer06 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    class Solution {
        public int[] reversePrint(ListNode head) {
            Stack<Integer> s = new Stack<>();
            int length = 0;
            while(head != null) {
                s.push(head.val);
                head = head.next;
                length++;
            }
            int[] reverseList = new int[length];
            for (int i = 0; i < length; i++) {
                reverseList[i] = s.pop();
            }
            return reverseList;
        }
    }
}
