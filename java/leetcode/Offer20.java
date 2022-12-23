public class Offer20 {
    static class Solution {
        public boolean isNumber(String s) {
            boolean res = false;
            s = s.trim();
            int i;
            for (i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == 'e' || c == 'E') {
                    String first = s.substring(0, i);
                    String last = s.substring(i + 1, s.length());
                    res = isNormalNumber(first) && isInteger(last);
                    break;
                }
            }
            if (i == s.length()) {
                res = isNormalNumber(s);
            }
            return res;
            
        }
        public boolean isInteger(String s) {
            boolean res = false;
            if (s.length() == 0) {
                return  res;
            }
            char firstChar = s.charAt(0);
            if (firstChar == '+' || firstChar == '-') {
                res = isUnsignedInteger(s.substring(1));
            } else {
                res = isUnsignedInteger(s);
            }
            return res;
        }
        public boolean isUnsignedInteger(String s) {
            boolean res = false;
            if (s.length() == 0) {
                return res;
            }
            res = true;
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c > '9' || c < '0') {
                    res = false;
                    break;
                }
            }
            return res;
        }
        public boolean isDecimal(String s) {
            boolean res = false;
            if (s.length() == 0) {
                return res;
            }
            char firstChar = s.charAt(0);
            if (firstChar == '+' || firstChar == '-') {
                res = isUnsignedDecimal(s.substring(1));
            } else {
                res = isUnsignedDecimal(s);
            }
            return res;
        }
        public boolean isUnsignedDecimal(String s) {
            boolean res = false;
            if (s.length() == 0) {
                return res;
            }
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '.') {
                    String first = s.substring(0, i);
                    String last = s.substring(i + 1, s.length());
                    res = (first.length() == 0 || isUnsignedInteger(first)) 
                            && (last.length() == 0 || isUnsignedInteger(last)) 
                            && !(first.length() == 0 && last.length() == 0);
                }
            }
            return res;
        }
        public boolean isNormalNumber(String s) {
            return isInteger(s) || isDecimal(s);
        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        s.isNumber("e");
    }
}
