import java.util.*;

public class Test5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n;  i++) nums[i] = in.nextInt();
        System.out.println(getRes(nums, k));
    }

    public static long getRes(int[] nums, int k) {
        long res = 0;
        long count = 0;
        int[] bits = new int[31];
        for (int num : nums) {
            int i = 0;
            while (num > 0) {
                i = Integer.numberOfTrailingZeros(num);
                bits[i]++;
                count++;
                num = num & (num - 1);
            }
        }
        if (count <= k) return 0;
        int start = 30;
        while (k > 0) {
            while (bits[start] == 0) {
                start--;
            }
            bits[start]--;
            k--;
        }
        for (int i = start; i >= 0; i--) res += bits[i] * (1L << i);
        return res; 
    }
}
