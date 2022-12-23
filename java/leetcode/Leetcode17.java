import java.util.ArrayList;

import javax.swing.event.InternalFrameAdapter;

/*
 * @lc app=leetcode.cn id=17 lang=java
 *
 * [17] 电话号码的字母组合
 */

// @lc code=start
class Solution {
    String[] maps = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> ans = new ArrayList<>();
    StringBuilder temp = new StringBuilder();
    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<String>();
        }
        backtrack(digits, 0);
        return ans;
    }
    public void backtrack(String digits, int index) {
        if (index == digits.length()) {
            ans.add(temp.toString());
            return;
        }
        String val = maps[digits.charAt(index) - '2'];
        for (char c : val.toCharArray()) {
            temp.append(c);
            backtrack(digits, index + 1);
            temp.deleteCharAt(temp.length() - 1);
        }
    }
}
// @lc code=end

