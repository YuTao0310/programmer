import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int x1 = in.nextInt(), y1 = in.nextInt();
        int x2 = in.nextInt(), y2 = in.nextInt();
        int x3 = in.nextInt(), y3 = in.nextInt();
        System.out.println(f(x1, x2, m) + f(x2, x3, m) + f(y1, y2, n) + f(y2, y3, n));
    }

    public static int f(int i, int j, int len) {
        return Math.min(Math.abs(i - j), Math.min(i, j) + len - Math.max(i, j));
    }
}
