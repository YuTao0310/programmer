import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class Offer48 {
    static class Solution {
        public int lengthOfLongestSubstring(String s) {
            HashSet<Character> hs = new HashSet<>();
            Queue<Character> q = new LinkedList<>();
            int maxLength = 0, nowLength = 0;
            char[] cs = s.toCharArray();
            for (char c : cs) {
                if (!hs.contains(c)) {
                    hs.add(c);
                    nowLength++;
                } else {
                    maxLength = Math.max(maxLength, nowLength);
                    while (q.peek() != c) {
                        nowLength--;
                        hs.remove(q.peek());
                        q.poll();
                    }
                    q.poll();
                }
                q.offer(c);
            }
            return Math.max(maxLength, nowLength);
        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.lengthOfLongestSubstring("bbbbb"));
    }
}
