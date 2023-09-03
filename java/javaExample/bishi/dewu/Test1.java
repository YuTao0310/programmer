import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        in.nextLine();
        String str = in.nextLine();
        System.out.println(findHuiWen(str, n, k));
    }

    public static int findHuiWen(String str, int n, int k) {
        for (int i = 0; i < n - k + 1; i++) {
            int left = i, right = i + k - 1;
            while (left < right) {
                if (str.charAt(left) != str.charAt(right)) break;
                left++;
                right--;
            } 
            if (left >= right) return 1;
        }
        return 0;
    }
}