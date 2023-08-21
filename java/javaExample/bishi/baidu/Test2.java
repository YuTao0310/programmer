public class Test2 {
    public static void main(String[] args) {
        int[] nums = new int[] {-1, 2, 3, -2, 4};
        int[] dp = new int[nums.length + 1];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= nums.length; i++) {
            dp[i] = dp[i - 1] >= 0 ? nums[i - 1] + dp[i - 1] : nums[i - 1]; 
            res = Math.min(res, dp[i]);
        }
        System.out.println(res);
    }
}
