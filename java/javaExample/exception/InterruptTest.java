package exception;

import java.io.IOException;

class TestException extends IOException {
    public TestException(String message) {
        super(message);
    }
}
public class InterruptTest {
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().isInterrupted());
        int[] a = {1, 2, 3};
        try {
            a[-1] = 3;
            throw new TestException("test");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("it's okay");
        } catch (TestException e) {
            System.out.println("hhh");
        }
        System.out.println("done");
    }
}
