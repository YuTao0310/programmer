import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Offer59I {
    /*
     * 超出时间限制
     */
    class Solution0 {
        public int[] maxSlidingWindow(int[] nums, int k) {
            int[] ans = new int[nums.length - k + 1];
            int max;
            for (int i = 0; i < ans.length; i++) {
                max = nums[i];
                for (int j = i + 1; j < i + k; j++) {
                    max = Math.max(max, nums[j]);
                }
                ans[i] = max;
            }
            return ans;
        }
    }

    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            Deque<Integer> deque = new LinkedList<>();
            int[] ans = new int[nums.length - k + 1];
            for (int i = 0; i < nums.length; i++) {
                if (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                    deque.pollFirst();
                }
                while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                    deque.pollLast();
                }
                deque.offerLast(i);
                if (i >= k - 1) {
                    ans[i - k + 1] = nums[deque.peekFirst()];
                }
            }
            return ans;
        }
    }

}
