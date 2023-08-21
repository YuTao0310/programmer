package bishi;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class XunfeiTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n + 1], b = new int[n + 1], address = new int[n + 1];
        for (int i = 1; i <=n; i++) {
            a[i] = in.nextInt();
            address[a[i]] = i;
        }
        for (int i = 1; i <=n; i++) {
            b[i] = in.nextInt();
        }
        long res = n * 1L * (n + 1);
        int left = 1;
        while (left <= n) {
            int right = left + 1, index = address[b[left]] + 1;
            while(index <= n && right <= n) {
                if (a[index] != b[right]) break;
                index++;
                right++;
            }
            int common_length = right - left;
            res -=  1L * common_length * (common_length + 1) / 2;
            left = right;
        }
        System.out.println(res);
    }
}