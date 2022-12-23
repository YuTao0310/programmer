import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Offer50 {
    class Solution {
        public char firstUniqChar(String s) {
            Map<Character, Integer> hm = new HashMap<>();
            int L = s.length();
            for (int i = 0; i < L; i++) {
                char c = s.charAt(i);
                if (hm.containsKey(c)) {
                    hm.put(c, L);
                } else {
                    hm.put(c, i);
                }
            }
            int min = L;
            char uniqueChar = ' ';
            for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
                if (entry.getValue() < min) {
                    min = entry.getValue();
                    uniqueChar = entry.getKey();
                }
            }
            return uniqueChar;
        }
    }
}
