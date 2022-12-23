public class Leetcode48 {
    class Solution {
        public void rotate(int[][] matrix) {
            int len = matrix[0].length;
            for (int i = 0; i < len / 2; i++)
                for (int j = i; j < len - i - 1; j++) {
                    int temp = matrix[i][j];
                    int p = i, q = j, m;
                    for (int k = 0; k < 3; k++) {
                        matrix[p][q] = matrix[len - 1 -q][p];
                        m = p;
                        p = len - 1 - q;
                        q = p;
                    }
                    matrix[p][q] = temp;
                }
        }
    }
}
