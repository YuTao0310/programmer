package quickStart;
import java.util.Scanner;

class ScannerExample {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入升高（m）");
        float height = s.nextFloat();
        System.out.println("请输入体重（kg）");
        float weight = s.nextFloat();
        float bmi = weight / (height*height);
        System.out.print("BMI是"+bmi);
        String str = s.nextLine();
        System.out.println(str.length());
        s.close();
    }
}
