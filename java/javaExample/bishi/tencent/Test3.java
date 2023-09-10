import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        System.out.println(getRes(a));
    }

    public static int getRes(int[] a) {
        int res = 0;
        for (int num : a) res = Math.max(res, num);
        return res;
    }
}
