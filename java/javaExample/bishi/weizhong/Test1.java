/*
 * 切糖果
 */
import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] taste = new int[n];
        for (int i = 0; i < n; i++) taste[i] = in.nextInt();
        System.out.println(getRes(taste));
    }

    public static int getRes(int[] taste) {
        Set<Integer> visit = new HashSet<>();
        for (int i = 0; i < taste.length; i++) {
            if (visit.contains(taste[i])) return i;
            visit.add(taste[i]);
        }
        return taste.length;
    }
}