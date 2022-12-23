import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Leetcode128 {
    class Solution {
        public int longestConsecutive(int[] nums) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int i : nums) map.put(i, 1);
            for (int i : nums) {
                if (map.containsKey(i - 1)) {
                    map.putIfAbsent(i, map.get(i - 1) + 1);
                } else if (map.containsKey(i + 1)) {
                    map.putIfAbsent(i, map.get(i + 1) + 1);
                }
            }
        }
    }
}
