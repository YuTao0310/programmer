import java.util.ArrayList;

import Leetcode142.ListNode;

public class Leetcode148 {
    class Solution {
        public ListNode sortList(ListNode head) {
            if (head == null) return null;
            List<ListNode> list = new ArrayList<>();
            while (head != null) {
                list.add(head);
                head = head.next;
            }
            Collections.sort(list, (a, b) -> a.val - b.val);
            ListNode ans = new ListNode(), temp = ans;
            for (ListNode node : list) {
                temp.next = node;
                temp = node;
            }
            temp.next = null;
            return ans.next;
        }
    }
}
