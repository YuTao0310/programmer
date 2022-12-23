import java.util.HashSet;
import java.util.Set;

public class Offer52 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    class Solution1 {
        ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> collection = new HashSet<>();
            for (ListNode now = headA; now != null; now = now.next) {
                collection.add(now);
            }
            for (ListNode now = headB; now != null; now = now.next) {
                if (collection.contains(now)) {
                    return now;
                }
            }
            return null;
        }
    }

    public class Solution {
        public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            ListNode A = headA, B = headB;
            while (A != B) {
                A = A != null ? A.next : headB;
                B = B != null ? B.next : headA;
            }
            return A;
        }
    }
}
