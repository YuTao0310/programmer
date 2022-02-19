import java.util.Arrays;

public class Offer29 {
    static class Solution {
        public int[] spiralOrder(int[][] matrix) {
            int rows = matrix.length;
            if (rows == 0) {
                return new int[0];
            }
            if (rows == 1) {
                return matrix[0];
            }
            int columns = matrix[0].length;
            int[] res = new int[rows * columns];
            if (columns == 0) {
                return new int[0];
            }
            if (columns == 1) {
                for (int i = 0; i < rows; i++) {
                    res[i] = matrix[i][0];
                }
                return res;
            }
            int end = rows * columns - (rows - 2) * (columns - 2) - 1;
            int lastLineEnd = end - (rows - 2);
            for (int i = 0; i < rows; i++) {
                if (i == 0) {
                    System.arraycopy(matrix[i], 0, res, 0, columns);
                } else if (i == rows - 1) {
                    for (int j = 0; j < columns; j++) {
                        res[lastLineEnd - j] = matrix[i][j];
                    }
                } else {
                    res[end - (i - 1)] = matrix[i][0];
                    res[columns + (i - 1)] = matrix[i][columns - 1];
                }
            }
            int[][] remains = new int[rows - 2][columns - 2];
            for (int i = 0; i < rows - 2; i++) {
                remains[i] = Arrays.copyOfRange(matrix[i + 1], 1, columns - 1);
            }
            System.arraycopy(spiralOrder(remains), 0, res, end + 1, rows * columns - end - 1);
            return res;
        }
    }
    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] arr = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[] res = s.spiralOrder(arr);
        System.out.println(res);
    }
}
