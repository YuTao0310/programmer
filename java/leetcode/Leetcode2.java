import java.util.LinkedList;

/*
 * @lc app=leetcode.cn id=2 lang=java
 *
 * [2] 两数相加
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(), temp = res;
        int carry = 0, value;
        while (l1 != null && l2 != null) {
            value = l1.val + l2.val + carry;
            carry = value / 10;
            temp.next = new ListNode(value > 9 ? value - 10 : value); 
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            value = l1.val + carry;
            carry = value / 10;
            temp.next = new ListNode(value > 9 ? value - 10 : value); 
            temp = temp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            value = l2.val + carry;
            carry = value / 10;
            temp.next = new ListNode(value > 9 ? value - 10 : value); 
            temp = temp.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return res.next;
    }
}
// @lc code=end

