package digit;

public class TestNumber {
    public static void main(String[] args) {
        // 1. 把浮点数3.14转化为"3.14"
        float f = 3.14f;
        //方法1
        String s1 = String.valueOf(f);
        System.out.println(s1);
        
        //方法2
        Float f1 = f;
        String s2 = f1.toString();
        System.out.println(s2);

        // 2. 把"3.14"转化为3.14
        String s = "3.14";
        float f3 = Float.parseFloat(s);
        System.out.println(f3);

        // 3. 字符串"3.1a4"转化成啥. 会报错
        /*
        s = "3.1a4";
        float f4 = Float.parseFloat(s);
        System.out.println(f4);
        */
    }
}
