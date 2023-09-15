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
                strs[i] = in.nextLine();
            System.out.println(getRes(strs) ? "YES" : "NO");
        }
    }

    public static boolean getRes(String[] strs) {
        if (strs.length == 0) return false;
        Set<String> set = new HashSet<>();
        for (String str : strs) {
            set.add(str.substring(1, str.length()) + str.charAt(0));
        }
        for (String str : strs)
            if (set.contains(str))
                return true;
        return false;
    }
}
