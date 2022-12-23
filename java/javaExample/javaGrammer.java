import java.util.Arrays;

import javax.swing.text.html.ObjectView;

import java.util.ArrayList;
import hero.Hero;
public class javaGrammer {

    private static void f() {
        System.out.print("f\n");
    }
    private void f1() {
        System.out.print("f1\n");
    }
    public static void main(String[] args) {
        // String str0 = "hello";
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
        String ss1 = new String("计算机");
        String ss2 = ss1.intern();
        String ss3 = "计算机";
        System.out.println("ss1 == ss2? " + (ss1 == ss2)); //false
        System.out.println("ss3 == ss2? " + (ss3 == ss2)); //true
        String s1 = new String("he") + new String("llo"); 
        String s2 = new String("h") + new String("ello"); 
        String s3 = s1.intern(); 
        String s4 = s2.intern(); 
        System.out.println(s1 == s2); // false
        System.out.println(s1 == s3); // true
        System.out.println(s1 == s4); // true
        int y = 2^8;
        System.out.println(y);
        int[] d = new int[]{1, 2, 3};
        int[] e = new int[]{1, 2, 3};
        System.out.println(d.equals(e));
        Integer aint = 1;
        Object aobj = aint;
        System.out.println(aobj);
        Integer i1 = 1;
        Integer i2 = 1;
        Integer i3 = new Integer(1);
        Integer i4 = new Integer(1);
        Integer i5 = Integer.valueOf(1);
        Integer i6 = Integer.valueOf(1);
        System.out.println((i1 == i2) + " " 
                         + (i3.hashCode() == i4.hashCode()) + " " + (i3.equals(i4)) + " " + (i3 == i4) + " "
                         + (i5.hashCode() == i6.hashCode()) + " " + (i5.equals(i6)) + " " + (i5 == i6));
        String string1 = "hhh";
        String string2 = "hhh";
        String string3 = new String("hhh");
        String string4 = new String("hhh");
        System.out.println((string1 == string2) + " " + (string1 == string3) + " " + (string3 == "hhh") + " "
                         + (string3.hashCode() == string4.hashCode()) + " " + (string3.equals(string4)) + " " + (string3 == string4));
    }
}
