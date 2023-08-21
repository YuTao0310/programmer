import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Test2 {
    static List<List<Integer>> tree = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt(), y = in.nextInt();
            tree.get(x).add(y);
        }

        System.out.println(dfs(0, 0, k));
    }

    public static int dfs(int root, int depth, int k) {
        if (depth - k > 0) return 0;
        int ans = 1;
        if (!tree.get(root).isEmpty()) {
            for (Integer child : tree.get(root)) {
                ans += dfs(child, depth + 1, k);
            }
        } else {
            ans += Math.max(0, k - depth);
        }
        return ans;
    }
}
