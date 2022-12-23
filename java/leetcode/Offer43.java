public class Offer43 {
    class Solution {

        public int countDigitOne(int n) {
            int count = getCount(n);
            return countDigitOne(n, count);
        }
        private int countDigitOne(int n, int count) {
            if (n == 1) {
                return 1;
            }
            if (n > 1 && n < 10) {
                return 0;
            }
            int mod  = (int)Math.pow(10, count - 1);
            int firstDigit = n / mod;
            int remainder = n % mod;
            if (firstDigit == 1) {
                return remainder +  countDigitOne(remainder, count - 1) + countDigitOne(mod- 1, count - 1);
            } else {
                return mod - 1 + countDigitOne(remainder, count - 1) + firstDigit * countDigitOne(mod - 1, count - 1);
            }
            
        }
        private int getCount(int n) {
            int count = 0;
            while (n > 0) {
                n /= 10;
                count++;
            }
            return count;
        }
    }
}
