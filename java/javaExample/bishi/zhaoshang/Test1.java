import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int start = in.nextInt(), end = in.nextInt();
            map.computeIfAbsent(start, key -> new ArrayList<>()).add(end);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }
        
        int res = 0, end = 19, start = 5;
        while (start <= end) {
            if (!map.containsKey(start)) {
                start++;
                continue;
            }
            end = Math.min(end, map.get(start).get(0));
            int temp = 0, i = start;
            while (i <= 19) {
                if (!map.containsKey(i)) {
                    i++;
                    continue;
                }
                temp++;
                i = map.get(i).get(0);
            }
            res = Math.max(res, temp);
            start++;
        }
        System.out.println(res);
    }
}