import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode85 {
    class Solution {
        public int maximalRectangle(char[][] matrix) {
            int m = matrix.length, n = matrix[0].length;
            int[][] pre = new int[m + 1][n + 1];
            for (int i = 1; i <= m; i++) {
                for (int j = 1; j <= n; j++) {
                    pre[i][j] = matrix[i - 1][j - 1] == '1' ? pre[i][j - 1] + 1 : 0;
                }
            }
            int ans = 0;
            for (int j = 1; j <= n; j++) {
                int[] up = new int[m + 1];
                int[] down = new int[m + 1];
                Arrays.fill(down, m + 1);
                Deque<Integer> stack = new ArrayDeque<>();
                for (int i = 1; i <= m; i++) {
                    while (!stack.isEmpty() && pre[stack.peek()][j] >= pre[i][j]) {
                        down[stack.peek()] = i;
                        stack.pop();
                    }
                    up[i] = stack.isEmpty() ? 0 : stack.peek();
                    stack.push(i);
                }
                for (int i = 1; i <= m; i++) {
                    ans = Math.max(ans, pre[i][j] * (down[i] - up[i] - 1));
                }
            }
            return ans;
        }
    }
}