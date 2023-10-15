import java.util.*;

public class AliGuoJiTest1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int temp = 0;
        Set<Character> map = new HashSet<>();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.contains(c)) {
                temp++;
                map = new HashSet<>();
            } else {
                map.add(c);
            }
        }
        System.out.println(str.length() - 2 * temp);
    }
}
