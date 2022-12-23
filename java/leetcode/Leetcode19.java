import Offer06.ListNode;

/*
 * @lc app=leetcode.cn id=19 lang=java
 *
 * [19] 删除链表的倒数第 N 个结点
 */

// @lc code=start
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    List<ListNode> visited = new ArrayList<>();
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        while (temp != null) {
            visited.add(temp); 
            temp = temp.next;
        }
        int index = visited.size() - n - 1;
        if (index < 0) return head.next;
        ListNode indexNode = visited.get(index);
        indexNode.next  = indexNode.next.next;
        return head;
    }
}
// @lc code=end

