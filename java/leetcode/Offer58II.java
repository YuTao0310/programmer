public class Offer58II {
    class Solution {
        public String reverseLeftWords(String s, int n) {
            StringBuilder sb = new StringBuilder();
            sb.append(s.substring(n));
            for (int i = 0; i < n; i++) {
                sb.append(s.charAt(i));
            }
            return sb.toString();
        }
    }
}
