import java.util.Stack;

public class Offer31 {
    class Solution {
        private Stack<Integer> s = new Stack<>();
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            int popIndex = 0;
            int length = pushed.length;
            if (length != popped.length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                s.push(pushed[i]);
                while (!s.isEmpty() && s.peek() == popped[popIndex]) {
                    popIndex++;
                    s.pop();
                }
                if (popIndex == length) {
                    return true;
                }
            }
            return s.isEmpty() ? true : false;
        }
    }
}
