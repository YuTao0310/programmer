import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();
        System.out.println(getRes(nums, k));
    }

    public static long getRes(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(nums.length);
        for (int num : nums) queue.add(num);
        while (k > 0) {
            int temp = queue.poll();
            if (temp % 2 == 1) temp = temp * 2;
            else temp = temp * 2 + 1;
            queue.add(temp);
            k--;
        }
        long res = 0;
        for (Integer i : queue) res += i;
        return res;
    }
}
