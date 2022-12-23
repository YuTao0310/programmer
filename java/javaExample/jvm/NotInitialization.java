package jvm;

class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }
    public static String HELLOWORLD = "hello world";
}

/**
 * 非主动使用类字段演示
 **/
public class NotInitialization {
    private String str = "test";
    public static void main(String[] args) {
        System.out.println(ConstClass.HELLOWORLD);
    }
}
