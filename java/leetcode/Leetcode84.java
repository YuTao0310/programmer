public class Leetcode84 {
    class Solution {
        public int largestRectangleArea(int[] heights) {
            int n = heights.length;
            int[] left = new int[n];
            int[] right = new int[n];
            Arrays.fill(right, n);
            
            Deque<Integer> mono_stack = new ArrayDeque<Integer>();
            for (int i = 0; i < n; ++i) {
                while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                    right[mono_stack.peek()] = i;
                    mono_stack.pop();
                }
                left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
                mono_stack.push(i);
            }
            
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
            }
            return ans;
        }
    }
}
