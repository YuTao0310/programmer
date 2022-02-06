package collection;

import java.util.HashSet;

public class HashSetTest {

    static char getRandomChar(){
        double rand0 = Math.random();
        int rand;
        if(rand0 < 1.0/2){
            rand = (int)(Math.random() * 5 + 65);
        }else{
            rand = (int)(Math.random() * 5 + 97);
        }
        return (char)rand;
    }

    public static void main(String[] args) {
        int length = 1000;
        String[] strs = new String[length];
        for (int i = 0; i < length; i++) {
            strs[i] = getRandomChar() + "" +getRandomChar();
        }
        long start, end;
        /** 1、HashSet方法  */
        start = System.currentTimeMillis();
        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < length; i++) {
            hs.add(strs[i]);
        }
        int count = hs.size();
        end = System.currentTimeMillis();
        long hashSetTime = end - start;

        /** 2、记录方法 */
        start = System.currentTimeMillis();
        end = System.currentTimeMillis();
        long otherTime = end - start;

        System.out.println("Hashset方法时间：" + hashSetTime);
        System.out.println("重复元素的个数： " + count + "分别是：" + hs);
        System.out.println("其他方法时间：" + otherTime);

    }
}
