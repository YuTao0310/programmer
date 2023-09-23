import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] temp = in.nextLine().split(",");
        int n = Integer.parseInt(temp[0]), radius = Integer.parseInt(temp[1]);
        int[][] towers = new int[n][3];
        for (int i = 0; i < n; i++) {
            temp = in.nextLine().split(",");
            towers[i][0] = Integer.parseInt(temp[0]);
            towers[i][1] = Integer.parseInt(temp[1]);
            towers[i][2] = Integer.parseInt(temp[2]);
        }

        int maxValue = -1, minX = 10000, minY = 10000, val = 0;
        for (int x = -100; x <= 200; x++)
            for (int y = -100; y <= 200; y++) {
                val = 0;
                for (int[] tower : towers) val += compute(x, y, tower, radius);
                if (val == maxValue) {
                    if (x < minX) {
                        minX = x;
                        minY = y;
                    } else if (x == minX && y < minY) {
                        minY = y;
                    }
                } else if (val > maxValue) {
                    minX = x;
                    minY = y;
                    maxValue = val;
                }
            }
        System.out.println(minX + "," + minY);
    }

    private static int compute(int x, int y, int[] tower, int radius) {
        double dis = Math.sqrt(Math.pow(x - tower[0], 2) + Math.pow(y - tower[1], 2));
        if (dis > radius) return 0;
        return (int)(tower[2] / (1 + dis));
    }
}