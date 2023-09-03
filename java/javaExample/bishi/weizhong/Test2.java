/*
 *加单位 
 */
import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n;  i++) a[i] = in.nextInt();
        System.out.println(getRes(a));
    }

    public static int getRes(int[] a) {
        int res = 0, left = 0;
        Arrays.sort(a);
        Set<Integer> set = new HashSet<>();
        for (int num : a) set.add(num);
        if (a[a.length - 1] - a[0] + 1 <= a.length) left = a[a.length - 1];
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                left = Math.max(a[i], left) + 1;
                while (set.contains(left)) {
                    left++;
                }
                res += left - a[i];
                set.add(left);
            }
        }
        return res;
    }
}
