public class Leetcode42 {
    class Solution {
        public int trap(int[] height) {
            int len = height.length;
            int[] leftMax = new int[len], rightMax = new int[len];
            leftMax[0] = height[0];
            rightMax[len - 1] = height[len - 1];
            for (int i = 1; i < len; i++) {
                leftMax[i] = Math.max(leftMax[i - 1], height[i]); 
            }
            for (int j = len - 2; j >= 0; j--) {
                rightMax[j] = Math.max(rightMax[j + 1], height[j]);
            }
            int ans = 0, min, tmp;
            for (int k = 1; k < len - 1; k++) {
                min = Math.min(leftMax[k - 1], rightMax[k + 1]);
                ans += (tmp = min - height[k]) > 0 ? tmp : 0; 
            }
            return ans;
        }
    }
}
