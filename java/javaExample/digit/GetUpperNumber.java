package digit;
import java.util.Scanner;

public class GetUpperNumber {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入字符串：");
        String str = s.next();
        char[] charArray = str.toCharArray();
        for(char c : charArray){
            if(Character.isUpperCase(c) || Character.isDigit(c))
                System.out.print(c);
        }
        s.close();
        
    }
}
