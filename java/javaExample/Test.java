

public class Test {
    public static void main(String[] args) {    
        String s1 = new String("计算") + new String("机");
        String s2 = s1.intern();
        String s3 = "计算机hh";
        System.out.println("s1 == s2? " + (s1 == s2)); //true
        System.out.println("s3 == s2? " + (s3 == s2)); //true
    }
}
