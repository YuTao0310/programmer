public class Offer46 {
    class Solution {
        int[] memo;
        
        public int translateNum(int num) {
            String numString = String.valueOf(num);
            memo = new int[numString.length() + 1];
            return translateNumString(numString);
        }

        public int translateNumString(String numString) {
            int l = numString.length();
            if (l <= 1) {
                return 1;
            }
            String twoNum = numString.substring(0, 2);
            if (memo[l] == 0) {
                if (twoNum.compareTo("25") <= 0 && twoNum.substring(0, 1).compareTo("0") != 0) {
                    memo[l] = translateNumString(numString.substring(2)) + translateNumString(numString.substring(1));
                } else {
                    memo[l] = translateNumString(numString.substring(1));
                }
            }
            return memo[l];
        }
    }
}
