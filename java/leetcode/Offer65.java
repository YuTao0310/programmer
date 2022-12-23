public class Offer65 {
    static class Solution {
        public int add(int a, int b) {
            int p = 1, c = 0, ans = a, temp;
            for (int i = 0; i < 32; i++) {
                temp = b & p;
                ans = ans ^ temp ^ c;
                p = p << 1;
                c = ((a & temp) | (c & temp) | (a & c)) << 1;
            }
            return ans;
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.add(4, 7));
    }
}
