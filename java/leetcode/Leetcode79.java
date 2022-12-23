public class Leetcode79 {
    class Solution {
        char[][] board;
        char[] wordArray;
        int m, n, len;
        public boolean exist(char[][] board, String word) {
           this.board = board;
           this.wordArray = word.toCharArray();
           this.m = board.length;
           this.n = board[0].length;
           this.len = word.length();
           return dfs(0, 0, 0); 
        }

        private boolean dfs(int x, int y, int index) {
            if (index == len) return true;
            if (x < 0 || x == m || y < 0 || y == n) return false;
            for (int i = x; i < m; i++) {
                for (int j = y; j < n; j++) {
                    if (board[i][j] == wordArray[index]) {
                        index++;
                        char tmp = board[i][j];
                        board[i][j] = '#';
                        if (dfs(i - 1, j, index) || dfs(i + 1, j, index) || dfs(i, j - 1, index) || dfs(i, j + 1, index)) {
                            return true;
                        }
                        board[i][j] = tmp;
                        index--;
                    }
                    if (index > 0) return false;
                }
            }
            return false;
        }
    }
}
