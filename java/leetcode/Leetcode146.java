import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Iterator;

public class Leetcode146 {
    class LRUCache {
        Map<Integer, Integer> map;
        int capacity;

        public LRUCache(int capacity) {
           this.capacity = capacity;
           map = new LinkedHashMap<>();
        }
        
        public int get(int key) {
            if (map.containsKey(key)) {
                map.put(key, map.get(key));
            }
            return map.getOrDefault(key, -1);
        }
        
        public void put(int key, int value) {
            if (map.containsKey(key)) map.put(key, value);
            else {
                if (map.size() == capacity) {
                    Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
                    int removeKey = iter.next().getKey();
                    map.remove(removeKey);
                }
                map.put(key, value);
            }
            LiinkedHashMap
        }
    }
}
