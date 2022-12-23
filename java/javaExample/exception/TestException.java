package exception;

public class TestException {
    public static void main(String[] args) {
        // 内存不足error
        try {
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                str.append('3');
            }
        } catch (Error e) {
            System.out.println("这是个错误！！");
            e.printStackTrace();
        }

        // 除数为零异常
        try {
            int m = (int) Math.random() * 10000;
            int n = 0;
            System.out.println("结果等于:" + m / n);
        } catch (ArithmeticException e) {
            System.out.println("除数不可以等于零！！");
            e.printStackTrace();
        }

        // 数组下标异常
        try {
            int[] a = new int[10];
            System.out.println("输出此数组的值:" + a[10]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("数组下标异常！！");
            e.printStackTrace();
        }

        // 空指针异常
        try {
            @SuppressWarnings("unused")
            String str = null;
            // System.out.println("输出此字符串长度:" + str.length());
        } catch (NullPointerException e) {
            System.out.println("空指针异常！！");
            e.printStackTrace();
        }
    }
}