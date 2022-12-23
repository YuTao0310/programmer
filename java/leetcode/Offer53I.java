public class Offer53I {
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length, mid = 0;
            while (left < right) {
                mid = (left + right) / 2;
                if (nums[mid] == target) {
                    break;
                } else if (nums[mid] < target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
            if (nums.length == 0 ||  nums[mid] != target) {
                return 0;
            } 
            for (left = mid - 1; left >=0; left--) {
                if (nums[left] != target) {
                    break;
                }
            }
            for (right = mid + 1; right < nums.length; right++) {
                if (nums[right] != target) {
                    break;
                }
            }
            return right - left - 1;
        }
    }
}
