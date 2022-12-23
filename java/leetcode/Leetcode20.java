import java.util.Deque;
import java.util.LinkedList;

/*
 * @lc app=leetcode.cn id=20 lang=java
 *
 * [20] 有效的括号
 */

// @lc code=start
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            char peek = ' ';
            if (!stack.isEmpty() 
                && (peek = stack.peek()) == '[' &&  c == ']'
                || peek == '{' && c == '}'
                || peek == '(' && c == ')') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
// @lc code=end

