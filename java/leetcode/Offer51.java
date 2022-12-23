public class Offer51 {
    class Solution1 {
        public int reversePairs(int[] nums) {
            int res = 0;
            for (int i = 0; i < nums.length - 1; i++)
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < nums[i]) {
                        res++;
                    }
                }
            return res;
        }
    }

    static class Solution {
        public int reversePairs(int[] nums) {
            return mergeSort(nums, 0, nums.length);
        }

        public int mergeSort(int[] nums, int left, int right) {
            int mid = (left + right) / 2;
            if (right -left <= 1) {
                return 0;
            }
            return mergeSort(nums, left, mid) + mergeSort(nums, mid, right) + merge(nums, left, mid, right);
        }

        public int merge(int[] nums, int left, int mid, int right) {
            int i = left, j = mid;
            int[] temp = new int[right - left];
            int pos = 0, res = 0;;
            while (i < mid && j < right) {
                if (nums[i] <= nums[j]) {
                    temp[pos++] = nums[i++];
                } else {
                    temp[pos++] = nums[j++];
                    res += mid - i;
                }
            }
            while (i < mid) {
                temp[pos++] = nums[i++];
            }
            while (j < right) {
                temp[pos++] = nums[j++];
            }
            System.arraycopy(temp, 0, nums, left, right - left);
            return res;
        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        int[] nums = new int[] {7, 1};
        System.out.println(s.reversePairs(nums));
    }
}
