public class Leetcode33 {
    class Solution {
        public int search(int[] nums, int target) {
            int left = 0, right = nums.length - 1;
            int mid;
            while (left <= right) {
                mid = (left + right) / 2;
                if (nums[left] <= nums[right]) {
                    if (nums[mid] < target) {
                        left = mid + 1;
                    } else if (nums[mid] > target) {
                        right = mid - 1;
                    } else {
                        return mid;
                    }
                } else {
                    if (nums[mid] > nums[left]) {
                        if (nums[mid] < target) {
                            left = mid + 1;
                        } else if (nums[mid] > target) {
                            if (nums[left] < target) {
                                right = mid - 1;
                            } else {
                                left = mid + 1;
                            }
                        } else {
                            return mid;
                        }
                    } else {
                        if (nums[mid] < target) {
                            if (nums[right] > target) {
                                left = mid + 1;
                            } else {
                                right = mid - 1;
                            }
                        } else if (nums[mid] > target) {
                            right = mid - 1;
                        } else {
                            return mid;
                        }
                    }
                }
            }
            return -1;
        }
    }
}
