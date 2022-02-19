import java.math.BigInteger;

public class Offer14II {
    class Solution1 {
        public int cuttingRope(int n) {
            if (n <= 2) {
                return 1;
            }
            if (n == 3) {
                return 2;
            }
            int maxCount = n / 3;
            BigInteger maxValue = new BigInteger("1");
            BigInteger mulValue = new BigInteger("1");
            for (int threeCount = 0; threeCount <= maxCount; threeCount++) {
                int remain = n - 3 * threeCount;
                if (remain % 2 == 0) {
                    mulValue = new BigInteger("3").pow(threeCount).multiply(new BigInteger("2").pow(remain/2));
                    if (mulValue.compareTo(maxValue) == 1) {
                        maxValue = mulValue;
                    }
                }
            }
            return maxValue.remainder(new BigInteger("1000000007")).intValue();
        }
    }
}
