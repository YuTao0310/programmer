package collection;

import java.util.LinkedHashSet;

public class LinkedHashTest {
    public static void main(String[] args) {
        String pi = String.valueOf(Math.PI);
        char[] cs = pi.toCharArray();
        LinkedHashSet<Integer> lhm = new LinkedHashSet<>();
        for (char c : cs) {
            if (c != '.') {
                lhm.add(Integer.parseInt(c + ""));
            }
        }
        System.out.println(lhm);
    }
}
