package jvm;

public class TestClass {
    // private final int k = 2232;
    int m = 3;
    private final int N = 1234;
    private String str1 = "str1";
    private static String str2 = "str2";
    private int int1 = 123;
    private static int int2 = 234;
    private double double1 = 1.23;
    private static double double2 = 2.34;
    private char char1 = 'c';
    private static char char2 = 'd';
    private static TestClass test = new TestClass();

    public int inc() {
        int x = 200;
        return x + 1;
    }

    public static void main(String[] args) {
        TestClass test = new TestClass();
        String test1 = new String("testtest");
        double d3 = 56.2;
        System.out.println(d3);
        int2++;
        test1.substring(0, 1);
    }
}