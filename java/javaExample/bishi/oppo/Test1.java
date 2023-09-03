import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(findMax(n, nums));
    }

    public static int findMax(int n, int[] nums) {
        int ans = 0;
        for (int i = 0; i < n - 1; i++)
            for (int j = i + 1; j < n; j++) {
                ans = Math.max(ans, dis(i, j, n) * (nums[i] + nums[j]));
            }
        return ans;
    }

    private static int dis(int i, int j, int n) {
        return Math.min(j - i, Math.abs(i + n - j));
    }
}