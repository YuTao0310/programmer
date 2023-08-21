import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        System.out.println(getSum(str));
    }

    public static long getSum(String str) {
        char[] cs = str.toCharArray();
        int[] temp0 = new int[cs.length + 1], temp1 = new int[cs.length + 1];
        char detect = '0';
        for (int i = 1; i <= cs.length; i++) {
            if (cs[i - 1] != detect) temp0[i] = temp0[i - 1] + 1;
            else temp0[i] = temp0[i - 1];
            detect = (char)('1' - detect + '0');
            if (cs[i - 1] != detect) temp1[i] = temp1[i - 1] + 1;
            else temp1[i] = temp1[i - 1];
        }
        long ans = 0;
        for (int i = 1; i < cs.length; i++)
            for (int j = i + 1; j <= cs.length; j++) {
                ans += Math.min(temp0[j] - temp0[i - 1], temp1[j] - temp1[i - 1]);
            }
        return ans;
    }
}
