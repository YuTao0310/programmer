import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Leetcode39 {
    static class Solution {
        List<List<Integer>> ans = new ArrayList<>();
        int[] candidates;
        public List<List<Integer>> combinationSum(int[] candidates, int target) {
            LinkedList<Integer> combine = new LinkedList<>();
            this.candidates = candidates;
            dfs(combine, 0, target);
            return ans;
        }
        @SuppressWarnings("unchecked")
        public void dfs(LinkedList<Integer> combine, int idx, int target) {
            if (target == 0) {
                ans.add((List<Integer>)combine.clone());
                return;
            }
            if (target < 0) {
                return;
            }
            for (int i = idx; i < candidates.length; i++) {
                combine.add(candidates[i]);
                dfs(combine, i, target - candidates[i]);
                combine.removeLast();
            }
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> ans = s.combinationSum(new int[] {2,3,5,7}, 7);
    }
}
