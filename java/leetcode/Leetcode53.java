/*
 * @lc app=leetcode.cn id=53 lang=java
 *
 * [53] 最大子数组和
 */

// @lc code=start
class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp  = new int[len];
        dp[0] = nums[0];
        int max = nums[0];
        for (int i = 1; i < len; i++) {
            dp[i] = dp[i - 1] >= 0 ? dp[i - 1] + nums[i] : nums[i];
            max = Math.max(dp[i], max);
        }
        return max;
    }
}

// @lc code=end

