import java.util.LinkedList;

public class Offer62 {
    // 超出时间限制
    class Solution0 {
        public int lastRemaining(int n, int m) {
            LinkedList<Integer> remains = new LinkedList<>();
            for (int i = 0; i < n; i++) {
                remains.add(i);
            }
            int temp = 0;
            for (int i = 0; i < n - 1; i++) {
                temp = (temp + m - 1) % remains.size();
                remains.remove(temp);
            }
            return remains.getFirst();
        }
    }

    // 超出时间限制
    class Solution1 {
        public int lastRemaining(int n, int m) {
            ListNode head = new ListNode(0), now = head, prev = null;
            for (int i = 1; i < n; i++) {
                now.next(i);
                now = now.next;
            }
            now.next = head;
            prev = now;
            now = head;
            while (n != 1) {
                for (int j = 0; j < (m - 1) % n; j++) {
                    prev = now;
                    now = now.next;
                }
                prev.next = now.next;
                now = prev.next;
                n--;
            }
            return now.val;
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int val) {
            this.val = val;
        }

        void next(int val) {
            this.next = new ListNode(val);
        }
    }

    class Solution {
        public int lastRemaining(int n, int m) {
            int dp = 0;
            for (int i = 2; i <= n; i++) {
                dp = (dp + m) % i;
            }
            return dp;
        }
    }
}
