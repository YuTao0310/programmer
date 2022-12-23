public class Offer67 {
    class Solution {
        public int strToInt(String str) {
            int flag = 1, i = 0;
            StringBuilder ans = new StringBuilder();
            str = str.trim();
            int len = str.length();
            String min = String.valueOf(Integer.MIN_VALUE).substring(1), max = String.valueOf(Integer.MAX_VALUE);
            if (len == 0) {
                return 0;
            }
            char c;
            if ((c = str.charAt(0)) == '+') {
                flag = 1;
                i = 1;
            } else if (c == '-') {
                flag = -1;
                i = 1;
            }
            for (; i < len; i++) {
                if ((c = str.charAt(i)) >= '0' && c <= '9') {
                    ans.append(c);
                } else {
                    break;
                }
            }
            String res = ans.toString();
            for (i = 0; i < res.length() - 1; i++) {
                if (res.charAt(i) != '0') {
                    break;
                }
            }
            if (res.length() == 0) {
                return 0;
            }
            res = res.substring(i);
            int l1 = res.length(), l2 = max.length();
            if ((l1 > l2 && flag == -1) || (l1 == l2 && res.compareTo(min) >= 0 && flag == -1)) {
                return Integer.MIN_VALUE;
            } else if (l1 > l2 || (l1 == l2 && res.compareTo(max) >= 0)) {
                return flag * Integer.MAX_VALUE;
            } else {
                return flag * Integer.valueOf(res);
            }
        }
    }
}
