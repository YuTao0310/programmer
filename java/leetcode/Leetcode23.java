import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
/*
 * @lc app=leetcode.cn id=23 lang=java
 *
 * [23] 合并K个升序链表
 */

// @lc code=start
/* 
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
*/
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode head = new ListNode(0), tmp = head;
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            tmp = tmp.next = queue.poll();
            if (tmp.next != null) {
                queue.offer(tmp.next);
            }
        }
        return head.next;
    }
}
// @lc code=end
