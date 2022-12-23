public class Offer61 {
    static class Solution {
        public boolean isStraight(int[] nums) {
            int[] temp = new int[14];
            int min = 14, max = -1;
            for (int i = 0; i < nums.length; i++) {
                temp[nums[i]]++;
                if (nums[i] != 0) {
                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }
            }

            for (int i = min; i < max + 1; i++) {
                if (temp[i] > 1 || temp[0] < 0) {
                    return false;
                } else if (temp[i] == 0) {
                    temp[i] = 1;
                    temp[0]--;
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isStraight(new int[] { 0, 0, 1, 2, 5 }));
    }
}
