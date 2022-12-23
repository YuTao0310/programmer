public class Offer14I {
    static class Solution1 {
        public int cuttingRope(int n) {
            switch (n) {
                case 1:
                case 2:
                    return 1;
                case 3:
                    return 2;
                default:
                    int maxCount = n / 3;
                    int max = 1;
                    int mulValue = 1;
                    for (int threeCount = 0; threeCount <= maxCount; threeCount++) {
                        int remain = n - 3 * threeCount;
                        if (remain % 2 == 0) {
                            mulValue = (int) (Math.pow(3, threeCount) * Math.pow(2, remain / 2));
                            if (mulValue > max) {
                                max = mulValue;
                            }
                        }
                    }
                    return mulValue;
            }
        }
    }
}
