import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class test {
    public static boolean equalFrequency(String word) {
        Map<Character, Integer> hashmap = new HashMap<>();
        int len = word.length();
        for (char c : word.toCharArray()) {
            hashmap.put(c, hashmap.getOrDefault(c, 0) + 1);
        }
        int flag1 = 0, flag2 = 0;
        Integer[] res = hashmap.values().toArray(new Integer[0]);
        int temp1 = res[0], temp2 = 0, flag = 0;
        for (Integer i : res) {
            if (i == temp1) {
                flag1++;
            } else if (i == temp2) {
                flag2++;
            } else if (flag == 0) {
                temp2 = i;
                flag2++;
                flag = 1;
            } else {
                break;
            }
        }
        if (flag1 == 1 && flag2 == hashmap.size() - 1 && temp1 == 1 || temp1 == temp2 + 1) {
            return true;
        }
        if (flag1 == hashmap.size() - 1 && flag2 == 1 && temp2 == 1 || temp2 == temp1 + 1) {
            return true;
        }
        if (flag1 == hashmap.size() && temp1 == 1) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) {
        System.out.println(equalFrequency("ddabcc"));
        System.out.println(Arrays.asList(new int[]{1, 2, 3}));
    }
}

class run1 implements Runnable {
    private static final int A = 1;
    @Override
    public void run() {
        System.out.println(A);
    }
}
