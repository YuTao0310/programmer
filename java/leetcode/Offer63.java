public class Offer63 {
    // 此法无法通过[3,3,5,0,0,3,1,4]
    class Solution0 {
        public int maxProfit(int[] prices) {
            int min = 0, max = 1;
            for (int i = 2; i < prices.length; i++) {
                if (prices[i] >= prices[max]) {
                    for (int j = min + 1; j < i; j++) {
                        min = prices[j] <= prices[min] ? j : min;
                    }
                    max = i;
                }
            }
            return prices[max] - prices[min] > 0 ? prices[max] - prices[min] : 0;
        }
    }

    static class Solution {
        public int maxProfit(int[] prices) {
            int length = prices.length;
            if (length == 0) {
                return 0;
            }
            int[] dp = new int[length];
            int min = prices[0];
            for (int i = 1; i < length; i++) {
                if (prices[i] - min < dp[i - 1]) {
                    dp[i] = dp[i - 1];
                } else {
                    dp[i] = prices[i] - min;
                }
                min = Math.min(min, prices[i]);
            }
            return dp[length - 1];
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.maxProfit(new int[] {3,2,6,5,0,3}));
    }
}
