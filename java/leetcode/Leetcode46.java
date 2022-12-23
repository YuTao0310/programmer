import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Leetcode46 {
    class Solution {
        List<List<Integer>> ans = new ArrayList<>();
        int[] nums;
        public List<List<Integer>> permute(int[] nums) {
            this.nums = nums;
            Deque<Integer> temp = new ArrayDeque<>();
            dfs(temp, 0);
            return ans;
        }
        public void dfs(Deque<Integer> temp, int idx) {
            if (idx == nums.length) {
                ans.add(new ArrayList<>(temp));
                return;
            }
            for (int i = idx; i < nums.length; i++) {
                temp.add(nums[i]);
                swap(idx, i);
                dfs(temp, i + 1);
                swap(idx, i);
                temp.removeLast();
            }
        }
        public void swap(int i, int j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    } 
}
