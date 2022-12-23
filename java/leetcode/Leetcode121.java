class Leetcode121 {
    class Solution {
        public int maxProfit(int[] prices) {
            int res = 0;
            for (int i = 1; i < prices.length; i++) {
                res = Math.max(res, prices[i] - prices[i - 1]);
                prices[i] = Math.min(prices[i - 1], prices[i]);
            }
            return res;
        }
    }
}