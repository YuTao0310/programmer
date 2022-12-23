package character;

import java.util.Random;

public class StringBufferTest{
    public static void main(String[] args) {
        /**
         * String��StringBuffer����������?
         *
         * ����10λ���ȵ�����ַ���
         * Ȼ��,��ʹ��String��+,����10000������ַ���,�������ĵ�ʱ��
         * Ȼ��,��ʹ��StringBuffer����10000������ַ���,�������ĵ�ʱ��
         */
        long start = System.currentTimeMillis();
        String s="";
        for (int i=0;i<10000;i++){
            s = s + getString(10);
        }
        long end = System.currentTimeMillis();
        System.out.println("string�ܹ���ʱ��:"+(end-start));
 
        long start1 = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<10000;i++){
            sb.append(getString(10));
        }
        long end1 = System.currentTimeMillis();
        System.out.println("stringbuffer�ܹ���ʱ��:"+(end1-start1));

        long start2 = System.currentTimeMillis();
        MyStringBuffer msb = new MyStringBuffer();
        for(int i = 0; i < 10000; i++){
            msb.append(getString(10));
        }
        long end2 = System.currentTimeMillis();
        System.out.println("MyStringBuffer�ܹ���ʱ�ˣ�" + (end2 - start2));
    }
 
    //��������ַ����ķ���
    public static String getString(int num){
        String s="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuffer buffer = new StringBuffer();
        Random random = new Random();
        for (int i=1;i<=num;i++){
            int i1 = random.nextInt(62);
            buffer.append(s.charAt(i1));
        }
        return buffer.toString();
    }
}