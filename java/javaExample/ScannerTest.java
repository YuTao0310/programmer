import java.util.Scanner;

public class ScannerTest {

    static Object object1 = new Object();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            System.out.println(sc.nextLine());
            System.out.println(a + b);
        }
    }
}