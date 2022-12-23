import java.util.Scanner;

public class meituan1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String str = input.nextLine();
        int ans = 0;
        for (int i = 0; i < str.length(); i++) {
            if (i==0 && str.charAt(i)=='-') continue;
            switch (str.charAt(i)){
                case '0':
                case '6':
                case '9':
                    ans++;
                    break;
                case '8':
                    ans=ans+2;
                    break;
                default:break;
            }
        }
        System.out.println(ans);
        input.close();
    }
}
