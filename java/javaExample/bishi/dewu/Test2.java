import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), x = in.nextInt();
        System.out.println(findMaxBuilding(n, m, x));   
    }

    public static int findMaxBuilding(int n, int m, int x) {
        int left = 1, right = m - n + 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, n, m, x)) right--;
            else left++;
        }
        return right;
    }
    //private static boolean check(long h, int n, int m, int x) 
    private static boolean check(long h, long n, long m, long x) {
        long end1 = h - (x - 1), end2 = h - (n - x);
        long res = 0;
        if (end1 >= 1) {
            res += (h + end1)  * x / 2;
        } else {
            res += (h + 1)  * h / 2 + (x - h);
        }

        if (end2 >= 1) {
            res += (h + end2) * (n - x + 1) / 2;
        } else {
            res += (h + 1)  * h / 2 + (n - x + 1 - h);
        }

        res -= h;
        return (int)res > m;
    }
}
