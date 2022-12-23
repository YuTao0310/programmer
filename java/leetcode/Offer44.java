public class Offer44 {
    static class Solution {
        public int findNthDigit(int n) {
            if (n < 10) {
                return n;
            }
            long sum_end = 0;
            int digit = 0;
            while (sum_end < n) {
                digit++;
                sum_end = (long)(Math.pow(10, digit) * digit - (Math.pow(10, digit) - 1) /9);
            }
            digit--;
            long sum_start = (long)(Math.pow(10, digit) * digit - (Math.pow(10, digit) - 1) /9);
            long remain = n - sum_start - 1;
            digit ++;
            long number = (long)Math.pow(10, digit - 1) + remain / digit;
            int order = (int)(remain % digit);
            for (int i = 1; i < digit - order; i++) {
                number /= 10;
            }
            return (int)(number % 10);
        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.findNthDigit(19000));
        System.out.println((-2) % 1);
    }
}