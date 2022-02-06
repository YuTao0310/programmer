package quickStart;

public class QuickStart {
    public static void main(String[] args) {
        System.out.println("Hello, World.");
        String s1 = "hh";
        String s2 = "hh";
        QuickStart i1 = new QuickStart();
        QuickStart i2 = new QuickStart();
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));
        System.out.println(s1.hashCode() + ", " + s2.hashCode());
        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));
        System.out.println(i1.hashCode() + ", " + i2.hashCode());
        System.out.println(Math.floor(-1.5));
    }
}