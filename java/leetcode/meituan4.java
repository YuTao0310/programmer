import java.util.Scanner;

public class meituan4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long n = (long)input.nextInt();
        long ans = n * (n - 1) * (n - 1) + n * (n - 1) * (n - 2) * (n - 2);
        System.out.println(ans % 1000);
        input.close();
    }
}
