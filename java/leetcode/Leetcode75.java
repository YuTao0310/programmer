public class Leetcode75 {
    class Solution {
        public void sortColors(int[] nums) {
            int len = nums.length;
            int left = 0, right = len - 1;
            for (int i = 0; i < len - 1; i++) {
                if (nums[i] == 2) {
                    swap(nums, i, right--);
                } else if (nums[i] == 0) {
                    swap(nums, i, left++);
                }
            }
        }
        private void swap(int[] nums, int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
