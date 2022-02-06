package collection;

import hero.Hero;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HashMapTest {
    public static void main(String[] args) {
        long start, end;
        int length = 3000000;
        Hero[] hs = new Hero[length];
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*10000);
            hs[i] = new Hero("hero-" + number);
        }
        /** 1、for循环查找 */
        start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            if (hs[i].getName().equals("hero-5555")) {
                System.out.println("Find it in index " + i);
            }
        }
        end = System.currentTimeMillis();
        long forTime = end - start;

        /** 2、HashMap查找 */
        start = System.currentTimeMillis();
        HashMap<String, List<Hero>> hm = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (hm.containsKey(hs[i].getName())) {
                hm.get(hs[i].getName()).add(hs[i]);
            } else {
                List<Hero> lh = new ArrayList<>();
                lh.add(hs[i]);
                hm.put(hs[i].getName(), lh);
            }
        }
        end = System.currentTimeMillis();
        long createHashMapTime = end - start;

        start = System.currentTimeMillis();
        if (hm.get("hero-5555") != null) {
            System.out.println("Find it " + hm.get("hero-5555"));
        } else {
            System.out.println("Not find it");
        }
        end = System.currentTimeMillis();
        long hashMapTime = end - start;
        System.out.println("For循环时间：" + forTime);
        System.out.println("HashMap创建时间：" + createHashMapTime);
        System.out.println("HashMap查找时间：" + hashMapTime);
    }
}
