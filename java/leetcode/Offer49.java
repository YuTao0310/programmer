public class Offer49 {
    class Solution {
        public int nthUglyNumber(int n) {
            int[] dp = new int[n];
            int a = 0, b = 0, c = 0;
            dp[0] = 1;
            for (int i = 1; i < n; i++) {
                int m = dp[a] * 2, k = dp[b] * 3, p = dp[c] * 5;
                dp[i] = Math.min(Math.min(m, k), p);
                if (dp[i] == m) {
                    a++;
                }
                if (dp[i] == k) {
                    b++;
                }
                if (dp[i] == p) {
                    c++;
                }
            }
            return dp[n - 1];
        }
    }
}
