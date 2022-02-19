public class Offer16 {
    class Solution {
        public double myPow(double x, int n) {
            if (n < 0) {
                if (n == Integer.MIN_VALUE) {
                    return 1.0 / (x * myPow(x, Integer.MAX_VALUE));
                } else {
                    return 1.0 /myPow(x, -n);
                }
            }
            if (n == 0) {
                return 1.0;
            }
            if (n % 2 == 0) {
                return myPow(x * x, n / 2);
            } else {
                return x * myPow(x * x, n / 2);
            }
        }
    }
}
