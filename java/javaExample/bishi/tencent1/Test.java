public class Test {
    public static void main(String[] args) {
        String str = "abc";
        System.out.println(str.substring(1, str.length()) + str.charAt(0));
        System.out.println(str.substring(3, 3) + str.substring(0, 3));
    }
}
