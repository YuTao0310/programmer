import java.util.Arrays;

public class Leetcode462 {
    class Solution {
        public int minMoves2(int[] nums) {
            int cost = Arrays.asList(nums).stream().mapToInt(s -> s).sum();
            int mid  = (int)Math.ceil(cost / 2.0);
            int ans = 0;
            for (int i : nums) ans += Math.abs(mid - i);
            return ans; 
        }
    }
}
