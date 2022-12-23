import java.util.Set;

public class Leetcode142 {
    class ListNode {
        int val;
        ListNode next;
    }
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            Set<ListNode> set = new HashSet<>();
            while (head != null) {
                if (set.contains(head)) {
                    return head;
                }
                set.add(head);
                head = head.next;
            }
            return null;
        }
    }
}
