public class Offer66 {
    class Solution {
        public int[] constructArr(int[] a) {
            int len = a.length;
            if (len == 0) {
                return new int[0];
            }
            int[] b = new int[len];
            b[0] = 1;
            int temp = 1;
            for (int i = 1; i < len; i++) {
                b[i] = b[i - 1] * a[i - 1];
            }
            for (int i = len - 2; i >= 0; i--) {
                temp *= a[i + 1];
                b[i] *= temp;
            }
            return b;
        }
    }
}
