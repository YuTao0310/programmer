import java.util.*;

public class Test2 {
    static boolean[][] used;
    static int res = Integer.MAX_VALUE;
    static int temp = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) 
                nums[i][j] = in.nextInt();
        
        for (int i = 0; i < m; i++) {
            temp = 0;
            used = new boolean[m][n];
            if (nums[i][0] == 1) bfs(nums, i, 0);
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void bfs(int[][] nums, int row, int column) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, row, column});
        used[row][column] = true;
        while (!queue.isEmpty()) {
            int[] a = queue.poll();
            int d = a[0], x = a[1], y = a[2];
            if (y == nums[0].length - 1) res = Math.min(res, d);
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (!check(nums, nx, ny)) continue;
                used[nx][ny] = true;
                queue.offer(new int[]{d + 1, nx, ny});
            }
        }
    }

    private static boolean check(int[][] nums, int row, int column) {
        if (row < 0 || column < 0 || row == nums.length || column == nums[0].length || used[row][column] || nums[row][column] == 0 ) return false;
        return true;
    }
}
