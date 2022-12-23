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
        /** 1��HashSet����  */
        start = System.currentTimeMillis();
        HashSet<String> hs = new HashSet<>();
        for (int i = 0; i < length; i++) {
            hs.add(strs[i]);
        }
        int count = hs.size();
        end = System.currentTimeMillis();
        long hashSetTime = end - start;

        /** 2����¼���� */
        start = System.currentTimeMillis();
        end = System.currentTimeMillis();
        long otherTime = end - start;

        System.out.println("Hashset����ʱ�䣺" + hashSetTime);
        System.out.println("�ظ�Ԫ�صĸ����� " + count + "�ֱ��ǣ�" + hs);
        System.out.println("��������ʱ�䣺" + otherTime);

    }
}
