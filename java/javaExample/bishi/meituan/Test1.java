import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int q = in.nextInt();
        int[] m = new int[q], x = new int[q];
        for (int i = 0; i < q; i++) {
            m[i] = in.nextInt();
            x[i] = in.nextInt();
        }
        for (int i = 0; i < q; i++) System.out.println(format(m[i], x[i]));
    }

    public static int format(int m, int x) {
        return  (x - 1) % m + 1;
    }
}