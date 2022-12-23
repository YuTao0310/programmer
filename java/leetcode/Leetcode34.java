public class Leetcode34 {
    class Solution {
        public int[] searchRange(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] < target) {
                    left = mid + 1;
                } else if (nums[mid] > target) {
                    right = mid - 1;
                } else {
                    int L = mid - 1, R = mid + 1;
                    for (; L >= 0; L--) {
                        if (nums[L] != nums[mid]) {
                            break;
                        }
                    }
                    for (; R < nums.length; R++) {
                        if (nums[R] != nums[mid]) {
                            break;
                        }
                    }
                    return new int[] {L + 1, R - 1};
                }
            }
            return new int[] {-1, -1};
        }
    } 
}
