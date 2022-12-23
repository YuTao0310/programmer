import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.print.FlavorException;

public class Leetcode139 {
    class Solution {
        Trie root;
        boolean[] failed = new boolean[301];
        
        public boolean wordBreak(String s, List<String> wordDict) {
            Trie root = new Trie();
            for (String str : wordDict) {
                Trie node = root;
                for (char c : str.toCharArray()) {
                    node.putIfAbsent(c, new Trie());
                    node = node.get(c);
                }
                node.put('#', new Trie());
            }
            this.root = root;
            return isWordBreak(root, s, 0);
        }
        private boolean isWordBreak(Trie trie, String s, int index) {
            if (failed[index]) return false;
            if (index == s.length()) return trie.containsKey('#');
            boolean res = false;
            if (trie.containsKey('#')) res = res || isWordBreak(root, s, index);
            if (res) return res;
            char c = s.charAt(index);
            if (trie.containsKey(c)) res = res || isWordBreak(trie.get(c), s, index + 1);
            if (!res) failed[index + 1] = true;
            return res;
        }
    }
    class Trie extends HashMap<Character, Trie> {
        
    }

    public void run() {
        new Solution().wordBreak("leetcode", Arrays.asList("leet", "code"));
    }

    public static void main(String[] args) {
        new Leetcode139().run();
    }
}
