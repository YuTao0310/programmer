import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int query = in.nextInt();
        for (int i = 0; i < query; i++) {
            int n = in.nextInt();
            List<Integer> a = new ArrayList<>();
            List<Integer> original = new ArrayList<>();
            int[] b = new int[n - 1];
            for (int j = 0; j < n; j++) {
                a.add(in.nextInt());
                original.add(a.get(j));
            }
            for (int j = 0; j < n - 1; j++) b[j] = in.nextInt();
            getRes(original, a, b);
        }
    }

    public static void getRes(List<Integer> original, List<Integer> a, int[] b) {
        Collections.sort(a);
        printInfo(a);
        for (int i : b) {
            int index = find(a, original.get(i - 1));
            a.remove(index);
            printInfo(a);
        }
        System.out.println();
    }

    public static void printInfo(List<Integer> a) {
        int temp = a.size();
        if (temp % 2 == 1) {
            System.out.print(a.get(temp / 2) + " ");
        } else {
            int r = a.get(temp / 2), l = a.get(temp / 2 - 1);
            if ((l + r) % 2 == 0) System.out.print((l + r) / 2 + " ");
            else System.out.printf("%.1f ", (l + r) / 2.0);
        }
    }

    public static int find(List<Integer> a, int target) {
        int l = 0, r = a.size() - 1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (a.get(mid) == target) return mid;
            else if (a.get(mid) < target) l = mid + 1;
            else r = mid - 1;
        }
        return -1;
    }
}