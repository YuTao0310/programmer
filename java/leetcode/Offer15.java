public class Offer15 {
    static class Solution {
        public int hammingWeight(int n) {
            int weight = 0;
            for (int i = 0; i < 32; i++) {
                weight += n & 1;
                n = n >>> 1;
            }
            return weight;
        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        s.hammingWeight(11);
    }
}
