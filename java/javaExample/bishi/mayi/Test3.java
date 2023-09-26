import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Test3 {
    static char[] sb;
    static Map<Integer, List<Integer>> tree;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
         tree = new HashMap<>();
        for (int i = 0; i < n - 1; i++) {
            int t1 = in.nextInt(), t2 = in.nextInt();
            tree.computeIfAbsent(t1, k -> new ArrayList<>()).add(t2);
            tree.computeIfAbsent(t2, k -> new ArrayList<>()).add(t1);
        }
        sb = new char[n];
        Arrays.fill(sb, 'B');
        dfs(1, -1);
        System.out.println(sb.toString());
    }

    private static void dfs(int start, int prev) {
        int count = 0;
        for (Integer next : tree.get(start)) {
            if (next == prev) continue;
            if (count == 0) 
        }
    }
}