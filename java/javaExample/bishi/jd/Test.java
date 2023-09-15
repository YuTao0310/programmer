/* 
给你一个字符串str，请你反转字符串中单词的顺序。
单词是由非空格字符组成的字符串。str中使用至少一个空格将字符串中的 单词 分隔开。
返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。

实例1：
输入：str = "jd star love work"
输出："work love star jd"

实例2：
输入：str = "   jdstar love     job  "
输出："job love jdstar"
*/
import java.util.*;

class Solution {
    public String reverse(String str) {
        List<String> strs = new ArrayList<>();
        for (int i = 0; i < str.length(); ) {
            if (str.charAt(i) == ' ') {
                i++;
                continue;
            }
            StringBuilder sb = new StringBuilder();
            while (i < str.length() && str.charAt(i) != ' ') {
                sb.append(str.charAt(i));
                i++;
            }
            strs.add(sb.toString());
        }
        StringBuilder res = new StringBuilder();
        for (int i = strs.size() - 1 ; i > 0; i--) {
            res.append(strs.get(i));
            res.append(' ');
        }
        res.append(strs.get(0));
        return res.toString();
    }
}
public class Test {
    public static void main(String[] args) {
        String str =  " jdstar love     job  ";
        System.out.println(new Solution().reverse(str));
        for (String s : str.split(" "))
            System.out.println(s);
    }
}

