/*
 * @lc app=leetcode.cn id=4 lang=java
 *
 * [4] 寻找两个正序数组的中位数
 */

// @lc code=start
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int k = (m + n) / 2;
        int i = 0, j = 0;
        int res1 = 0, res2 = 0;
        while (i < m || j < n) {
            if (j == n || i != m && nums1[i] < nums2[j]) {
                i++;
                if (i + j == k) {
                    res1 = nums1[i - 1];
                }
                if (i + j == k + 1) {
                    res2 = nums1[i - 1];
                }
            } else if (i == m || j != n && nums1[i] >= nums2[j]) {
                j++;
                if (i + j == k) {
                    res1 = nums2[j - 1];
                }
                if (i + j == k + 1) {
                    res2 = nums2[j - 1];
                }
            }
        }
        return (m + n) % 2 == 1 ? res2 : (res1 + res2) / 2.0;
    }
}
/* 
class Leetcode4 {
    public static void main(String[] args) {
        Solution2 s = new Solution2();
        System.out.println(s.findMedianSortedArrays(new int[] {1, 2}, new int[] {3, 4}));
    }
}
*/
// @lc code=end
