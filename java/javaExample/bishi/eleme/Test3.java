import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Test3 {
    static int[][] distances;
    static int n, m, q;
    static int[] nodes;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        q = in.nextInt();
        distances = new int[n + 1][n + 1];
        nodes = new int[q];
        for (int i = 0; i < m; i++) {
            int x = in.nextInt(), y = in.nextInt(), dis = in.nextInt();
            distances[x][y] = dis;
            distances[y][x] = dis;
        }
        for (int i = 0; i < q; i++) {
            nodes[i] = in.nextInt();
        }

        int sum = 0; 
        for (int i = 0; i < q; i++) {
            sum += 2 * findMin(1, nodes[i]);
        }
        System.out.println(sum);
    }

    public static int findMin(int start, int k) {
        int ans = Integer.MAX_VALUE;
        if (start == k) return 0;
        for (int j = 1; j <= n; j++) {
            if (distances[start][j] != 0) {
                ans = Math.min(ans, distances[start][j] + findMin(j, k));
            }
       }
       return ans;
    }
}