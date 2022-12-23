import java.util.Arrays;

public class Leetcode56 {
    class Solution {
        public int[][] merge(int[][] intervals) {
            Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
            int[][] ans = new int[intervals.length][2];
            int idx = -1;
            for (int[] ints : intervals) {
                if (idx == -1 || ints[0] > ans[idx][1]) {
                    ans[++idx] = ints;
                } else {
                    ans[idx][1] = Math.max(ans[idx][1], ints[1]);
                }
            }
            return Arrays.copyOf(ans, idx + 1);
        }
    }
}
