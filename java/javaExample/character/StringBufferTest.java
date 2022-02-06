package character;

import java.util.Random;

public class StringBufferTest{
    public static void main(String[] args) {
        /**
         * String与StringBuffer的性能区别?
         *
         * 生成10位长度的随机字符串
         * 然后,先使用String的+,连接10000个随机字符串,计算消耗的时间
         * 然后,再使用StringBuffer连接10000个随机字符串,计算消耗的时间
         */
        long start = System.currentTimeMillis();
        String s="";
        for (int i=0;i<10000;i++){
            s = s + getString(10);
        }
        long end = System.currentTimeMillis();
        System.out.println("string总共耗时了:"+(end-start));
 
        long start1 = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer();
        for (int i=0;i<10000;i++){
            sb.append(getString(10));
        }
        long end1 = System.currentTimeMillis();
        System.out.println("stringbuffer总共耗时了:"+(end1-start1));

        long start2 = System.currentTimeMillis();
        MyStringBuffer msb = new MyStringBuffer();
        for(int i = 0; i < 10000; i++){
            msb.append(getString(10));
        }
        long end2 = System.currentTimeMillis();
        System.out.println("MyStringBuffer总共耗时了：" + (end2 - start2));
    }
 
    //生成随机字符串的方法
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