import java.util.*;


class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}

class Solution {
    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 返回一个指向链表头部的指针。
     * @param a ListNode类一维数组 指向这些数链的开头
     * @return ListNode类
     */
    public ListNode solve (ListNode[] a) {
        // write code here
        List<ListNode> list = new ArrayList<>();
        for (ListNode head : a) {
            while (head != null) {
                list.add(head);
                head = head.next;
            }
        }
        if (list.size() == 1) return null;
        Collections.sort(list, (t1, t2) -> (t1.val - t2.val));
        ListNode head = list.get(0), prev = head;
        for (int i = 1; i < list.size(); i++) {
            prev.next = list.get(i);
            prev = prev.next;
        }
        prev.next = null;
        return head;
    }
}

public class Test1 {

}