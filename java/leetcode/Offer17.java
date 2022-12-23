public class Offer17 {
    class Solution1 {
        public int[] printNumbers(int n) {
            int maxValue = (int)Math.pow(10, n) - 1;
            int[] res = new int[maxValue];
            for (int i = 1; i <= maxValue; i++) {
                res[i - 1] = i;
            }
            return res;
        }
    }
}
