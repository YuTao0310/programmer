/*
 * 平均值
 */

import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), u = in.nextInt(), v = in.nextInt();
        int[] nums = new int[n];
        long sum = 0, res = 0;
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();
        for (int len = 1; len <= n; len++) {
            if (len % v != 0) continue;
            sum = len / v * u;
            long temp = 0;
            for (int i = 0; i < len; i++) temp += nums[i];
            if (temp == sum) res++;
            for (int i = len; i < n; i++) {
                temp += nums[i] - nums[i - len];
                if (temp == sum) res++;
            }
        }
        System.out.println(res);
    }
}
