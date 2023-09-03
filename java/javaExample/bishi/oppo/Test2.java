import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(findOppo(str));
    }

    public static int findOppo(String str) {
        int ans = 0;
        int mod = (int)1e9 + 7;
        if (str.length() < 4) return 0;
        Set<Character> set = new HashSet<>();
        set.add('a'); 
        set.add('e');
        set.add('i');
        set.add('o');
        set.add('u');
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (set.contains(c)) {
                map.putIfAbsent(c, new ArrayList<>());
                for (Integer index : map.get(c)) {
                    for (Map.Entry<Character, List<Integer>> entry : map.entrySet()) {
                        if (!set.contains(entry.getKey())) {
                            int count = 0;
                            for (Integer k : entry.getValue()) {
                                if (index < k) break;
                                count++;
                            }
                            ans += compute(map.get(entry.getKey()).size() - count) % mod;
                        }
                    }
                }
                map.get(c).add(i);
            } else {
                map.computeIfAbsent(c, key -> new ArrayList<>()).add(i);
            }
        }
        return ans;
    }

    private static int compute (int m) {
        return m * (m - 1) / 2;
    }
}