public class RedBookTest2 {
    static int n;
    static int[] t , h , a;

    public static void main(String[] args) {
        n = 3;
        t = new int[]{1, 2, 4};
        h = new int[]{2, 3, 3};
        a = new int[]{2, 3, 5};
        int Hmax = 5, Tmax = 5;
        System.out.println(findBestHappiness1(0, Hmax, Tmax));
         System.out.println(findBestHappiness2(0, Hmax, Tmax));

    }

    /* 迭代实现 */
    private static long findBestHappiness1(int index, int Hmax, int Tmax) {
        if (index >= n) return 0;
        if (h[index] > Hmax || t[index] > Tmax) return findBestHappiness1(index + 1, Hmax, Tmax);
        else return Math.max(findBestHappiness1(index + 1, Hmax, Tmax), a[index] + findBestHappiness1(index + 1, Hmax - h[index], Tmax - t[index]));
    }

    /* 动态规划实现 */
    private static long findBestHappiness2(int index, int Hmax, int Tmax) {
        long [][][] dp = new long[n + 1][Hmax + 1][Tmax + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= Hmax; j++)
                for (int k = 1; k <= Tmax; k++) {
                    if (h[i - 1] > j || t[i - 1] > k) dp[i][j][k] = dp[i - 1][j][k];
                    else dp[i][j][k] = Math.max(dp[i - 1][j][k], a[i - 1] + dp[i - 1][j - h[i - 1]][k  - t[i - 1]]);
                }
        return dp[n][Hmax][Tmax];
    }
}