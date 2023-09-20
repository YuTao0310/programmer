import java.util.*;

public class Test2_1 {
    static int[][] visited;
    static int res = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) 
                nums[i][j] = in.nextInt();
        
        visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            visited[i][0] = dfs(nums, i, 0, 0);
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static int  dfs(int[][] nums, int row, int column, int temp) {
        if (!check(nums, row, column)) return Integer.MAX_VALUE;
        if (visited[row][column] != 0) return visited[row][column];
        if (column == nums[0].length - 1) {
            res = Math.min(res, temp);
            return temp;
        }
        int ans = Integer.MAX_VALUE;
        nums[row][column] = 0;
        ans = Math.min(ans, dfs(nums, row - 1, column, temp + 1));
        ans = Math.min(ans, dfs(nums, row + 1, column, temp + 1));
        ans = Math.min(ans, dfs(nums, row, column - 1, temp + 1));
        ans = Math.min(ans, dfs(nums, row, column + 1, temp + 1));
        nums[row][column] = 1;
        return ans;
    }

    private static boolean check(int[][] nums, int row, int column) {
        if (row < 0 || column < 0 || row == nums.length || column == nums[0].length || nums[row][column] == 0) return false;
        return true;
    }
}
