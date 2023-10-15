import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            in.nextLine();
            String[] strs = new String[n];
            for (int j = 0; j < n; j++) 
                strs[j] = in.nextLine();
            System.out.println(getRes(strs) ? "YES" : "NO");
        }
    }

    public static boolean getRes(String[] strs) {
        if (strs.length == 0) return false;
        Set<String> set = new HashSet<>();
        for (String str : strs) {
            Set<String> tmpSet = new HashSet<>();
            for (int i = 1; i <= str.length(); i++) {
                String tmp = str.substring(i, str.length()) + str.substring(0, i);
                if (set.contains(tmp)) return true;
                tmpSet.add(tmp);
            }
            set.addAll(tmpSet);
        }
        return false;
    }
}
