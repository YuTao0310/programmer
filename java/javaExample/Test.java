import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.HashMap;

import org.omg.CORBA.INTERNAL;

class A {
    int i;
    public A() {
        i = 1;
        System.out.println("A: i = " + i);
    }
    protected int test() {
        return 1;
    }
}

class B extends A {
    public B() {
        i = 2;
        System.out.println("B: i = " + i);
    }
    public int test() {
        return 2;
    }{}
    public int hashcode() {
        return 42;
    }
}
public class Test {

    public static void main(String[] args) {
        System.out.println(Integer.parseInt("0123"));
        int[] a = {1, 2, 3};
        Object b = new int[] {1, 2, 3};
        boolean[] c = new boolean[1];
        System.out.println("c is " + c[0]);

        B b1 = new B();
        B b2 = new B();
        System.out.println(b1 == b2);
        String s = null;
        System.out.println(" s = " + s);
        System.out.println(Integer.MAX_VALUE);
        int i = 2147483647;
        long j = i * 1L * 10;
        System.out.println(j);
        System.out.printf("%d\n", j);
        System.out.println(i == 2147483647.00);
        int[] arr = new int[]{1, 2, 3};
        // Arrays.sort(arr, (n1, n2) -> (n2 - n1));
        String a0 = new String("124");
        String b0 = "124";
        System.out.println(a0 == b0);
        List<long[]> list = new ArrayList<>();
    }
}
