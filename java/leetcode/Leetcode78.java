import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Leetcode78 {
    class Solution {
        List<List<Integer>> ans = new ArrayList<>();
        int[] nums;
        int n;
        public List<List<Integer>> subsets(int[] nums) {
           this.nums = nums;
           n = nums.length;
           Deque<Integer> tmp = new ArrayDeque<>();
           backtrack(tmp, 0); 
           return ans;
        }
        private void backtrack(Deque<Integer> tmp, int i) {
            if (i == n) {
                ans.add(new ArrayList<Integer>(tmp));
            }
            for (int index = i; index < n; index++) {
                tmp.add(nums[i]);
                backtrack(tmp, index + 1);
                tmp.removeLast();
            }
        }
    }
}
