import java.util.Scanner;

public class Test4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();
        System.out.println(count(nums));
    }

    public static int count(int[] nums) {
        int mod = (int)1e9 + 7;
        long sum = 0;
        for (int i = 0; i < nums.length; i++) sum += nums[i];
        long res = compute(sum, nums.length);
        for (int num : nums) {
            res -= compute(sum - num, nums.length - 1);
        }
        return (int)(res % mod);
    }

    private static long compute(long sum, long length) {
        return (long)Math.pow(length, sum - length);
    }
}
