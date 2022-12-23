public class Offer56I {
    class Solution {
        public int[] singleNumbers(int[] nums) {
            int x = 0, y = 0, m = 1, temp = 0;
            for (int i : nums) {
                temp ^= i;
            }
            while ((temp & m) != 0) {
                m <<= 1;
            }
            for (int i : nums) {
                if ((i & m)  != 0) {
                    x ^= i;
                } else {
                    y ^= i;
                }
            }
            return new int[] {x, y};
        }
    }
}
