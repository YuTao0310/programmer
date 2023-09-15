import java.util.*;

public class Test5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int[] x = new int[n], c = new int[n];
        for (int i = 0; i < n; i++) x[i] = in.nextInt();
        for (int i = 0; i < n; i++) c[i] = in.nextInt();
        System.out.println(getRes(x, c, k));
    }

    public static int getRes(int[] x, int[] c, int k) {
        int kCount = 0;
        for (int num : c)
            if (num == 1) kCount++;
        if (kCount == k) return 0;
        return 1;
    }
}
