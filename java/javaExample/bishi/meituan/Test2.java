import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = in.nextInt();
        }
        System.out.println(getMax(nums));
    }

    public static long getMax(long[] nums) {
        if (nums.length == 1) return nums[0];
        long sum = 0, ans;
        for (long num : nums) sum += num;
        ans = sum;
        for (int i = 0; i < nums.length - 1; i++) {
            ans = Math.max(ans, sum - nums[i] - nums[i + 1] + nums[i] * nums[i + 1]);
        }
        return ans;
    }
}
