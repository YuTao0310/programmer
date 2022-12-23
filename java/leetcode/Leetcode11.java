/*
 * @lc app=leetcode.cn id=11 lang=java
 *
 * [11] 盛最多水的容器
 */

// @lc code=start
class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int res = -1;
        while (i < j) {
            if (height[i] < height[j]) {
                res = Math.max(res, height[i] * (j - i));
                i++;
            } else {
                res = Math.max(res, height[j] * (j - i));
                j--;
            }
        }
        return res;
    }
}
// @lc code=end

