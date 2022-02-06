package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CollectionsTest {

    public static final int[] LIST = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};  

    public static boolean shuffleOnce() {

        List<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < LIST.length; i++) {
            list.add(LIST[i]);
        }
        Collections.shuffle(list);
        if (judge(list)) {
            return true;
        }
        return false;
    }

    public static boolean judge(List<Integer> list) {
        if (list.get(0) == 3 && list.get(1) == 1 && list.get(2) == 4) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {    
        
        int count = 0;
        int number = 1000000;

        for(int i = 0; i < number; i++) {
            if (shuffleOnce()) {
                count++;
            }
        }

        System.out.print("probability is " + count * 100.0 /number + "%");
        System.out.printf("\nprobability is %.4f %%", count * 100.0 /number);
        System.out.format("\nprobability is %.4f %%", count * 100.0 /number);
    }
}
