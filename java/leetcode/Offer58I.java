import java.util.Stack;

public class Offer58I {
    public static void main(String[] args) {
        Solution s = new Solution();
        s.reverseWords("the sky is blue");
    }
}

class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        Stack<String> strs = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp != ' ') {
                flag = true;
                sb.append(temp);
            } else if (flag == true) {
                flag = false;
                strs.push(sb.toString());
                sb = new StringBuilder();
            }
        }
        strs.push(sb.toString());
        sb = new StringBuilder();
        int length = strs.size();
        for (int i = 0; i < length - 1; i++) {
            sb.append(strs.pop() + " ");
        }
        sb.append(strs.pop());
        return sb.toString();
    }
}