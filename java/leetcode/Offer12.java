
public class Offer12 {
    public static void main(String[] args) {
        Solution s = new Solution();
        char[][] cs = new char[][]{{'A','B','C','E'},{'S','F','E','S'},{'A','D','E','E'}};
        
        String str = "ABCESEEEFS";
        s.exist(cs, str);
    }
    static class Solution {
        int[][] track;
        char[][] map;
        String matchWord;
        
        public boolean exist(char[][] board, String word) {
            if (board.length == 0 || board[0].length == 0 || word.equals("")) {
                return false;
            }
            
            char firstChar = word.charAt(0);
            boolean state = false;
            map = board;
            matchWord = word;
            for (int i = 0; i < board.length; i++) {
                if (state == true) {
                    break;
                }
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == firstChar) {
                        track = new int[board.length][board[0].length];
                        track[i][j] = 1;
                        if (word.length() == 1) {
                            state = true;
                            break;
                        }
                        if (state =  move(i, j, 1)) {
                            break;
                        }
                    }
                }
            }
            return state;
        }
   
        public boolean move(int i, int j, int matchCount) {
            if (canMove(i + 1, j, matchCount)) {
                if (update(i + 1, j, matchCount)) {
                    return true;
                }
            }
            if (canMove(i - 1, j, matchCount)) {
                if (update(i - 1, j, matchCount)) {
                    return true;
                }
            }
            if (canMove(i, j + 1, matchCount)) {
                if (update(i, j + 1, matchCount)) {
                    return true;
                }
            }
            if (canMove(i, j - 1, matchCount)) {
                if (update(i, j - 1, matchCount)) {
                    return true;
                }
            }
            return false;
        }
        public boolean canMove(int i, int j, int matchCount) {
            if (i < 0 || i >= map.length) {
                return false;
            }
            if (j < 0 || j >= map[0].length) {
                return false;
            }
            if (track[i][j] == 1) {
                return false;
            }
            if (map[i][j] == matchWord.charAt(matchCount)) {
                return true;
            }
            return false;
        }
        public boolean update(int i, int j, int matchCount) {
            track[i][j] = 1;
            if (++matchCount == matchWord.length() || move(i, j, matchCount)) {
                return true;
            }
            track[i][j] = 0;
            return false;
        }
    }
}
