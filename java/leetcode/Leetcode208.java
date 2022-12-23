class Trie {
    TrieNode root;
    private class TrieNode {
        private boolean isEnding;
        private TrieNode[] next;
        public TrieNode() {
            isEnding = false;
            next = new TrieNode[26];
        }
    }
    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (temp.next[index] == null) temp.next[index] = new TrieNode();
            temp = temp.next[index]; 
        }
        temp.isEnding = true;
    }
    
    public boolean search(String word) {
        TrieNode temp = root;
        for (char c : word.toCharArray()) {
            if (temp.next[c - 'a'] == null) return false;
            temp = temp.next[c - 'a'];
        }
        return temp.isEnding;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode temp = root;
        for (char c : prefix.toCharArray()) {
            if (temp.next[c - 'a'] == null) return false;
            temp = temp.next[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
public class Leetcode208 {
    public static void main(String[] args) {
        Trie t = new Trie();
        System.out.println(t.search("a"));
    }
}
