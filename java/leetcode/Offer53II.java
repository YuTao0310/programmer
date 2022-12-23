public class Offer53II {
    class Solution {
        public int missingNumber(int[] nums) {
            int left = 0, right = nums.length - 1;
            while (left <= right) {
                int mid = (left + right) / 2;
                if (nums[mid] != mid) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            return left;
        }
    }
}
