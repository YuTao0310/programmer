public class Offer13 {
    class Solution {
        int count = 0;
        int[][] track;
        int limit;
        int row;
        int column;
        public int movingCount(int m, int n, int k) {
            row = m;
            column = n;
            limit = k;
            track = new int[m][n];
            track[0][0] = 1;
            count++;
            move(0, 0);
            return count;
        }
        public void move(int i, int j) {
            if (canMove(i + 1, j)) {
                count++;
                track[i + 1][j] = 1;
                move(i + 1, j);
            }
            if (canMove(i - 1, j)) {
                count++;
                track[i - 1][j] = 1;
                move(i - 1, j);
            }
            if (canMove(i, j + 1)) {
                count++;
                track[i][j + 1] = 1;
                move(i, j + 1);
            }
            if (canMove(i, j - 1)) {
                count++;
                track[i][j - 1] = 1;
                move(i, j - 1);
            }
        }
        public boolean canMove(int i, int j) {
            if (i < 0 || i >= row) {
                return false;
            } 
            if (j < 0 || j >= column) {
                return false;
            }
            if (track[i][j] == 1) {
                return false;
            }
            if (i / 10 + i % 10 + j / 10 + j % 10 > limit) {
                 return false;
            }
            return true;
        }
    }
}
