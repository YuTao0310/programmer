public class Offer14II {
    class Solution1 {
        public int cuttingRope(long n) {
            if (n <= 2) {
                return 1;
            }
            if (n == 3) {
                return 2;
            }
            long maxCount = n / 3;
            long max = 1;
            long mulValue = 1;
            for (long threeCount = 0; threeCount <= maxCount; threeCount++) {
                long remain = n - 3 * threeCount;
                if (remain % 2 == 0) {
                    mulValue = (long) (Math.pow(3, threeCount) * Math.pow(2, remain / 2));
                    if (mulValue > max) {
                        max = mulValue;
                    }
                }
            }
            return (int)(mulValue  % 1000000007);
        }
    }
}
