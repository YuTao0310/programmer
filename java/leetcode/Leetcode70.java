public class Leetcode70 {
    class Solution {
        public int climbStairs(int n) {
            int pre1 = 0, pre2 = 1;
            for (int i = 1; i <= n; i++) {
                int temp = pre2;
                pre2 = pre1 + pre2;
                pre1 = temp;
            }
            return pre2;
        }
    }
}
