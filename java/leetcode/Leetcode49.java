import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leetcode49 {
    class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            List<List<String>> ans = new ArrayList<>();
            Map<String, Integer> hashmap = new HashMap<>();
            for (String str : strs) {
                char[] charArray = str.toCharArray();
                Arrays.sort(charArray);
                String key = new String(charArray);
                if (!hashmap.containsKey(key)) {
                    hashmap.put(key, ans.size());
                    ans.add(new ArrayList<>());
                }
                ans.get(hashmap.get(key)).add(str);
            }
            return ans;
        }
    }
}
