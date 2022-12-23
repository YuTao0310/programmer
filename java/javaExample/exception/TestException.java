package exception;

public class TestException {
    public static void main(String[] args) {
        // �ڴ治��error
        try {
            StringBuffer str = new StringBuffer();
            for (int i = 0; i < Integer.MAX_VALUE; i++) {
                str.append('3');
            }
        } catch (Error e) {
            System.out.println("���Ǹ����󣡣�");
            e.printStackTrace();
        }

        // ����Ϊ���쳣
        try {
            int m = (int) Math.random() * 10000;
            int n = 0;
            System.out.println("�������:" + m / n);
        } catch (ArithmeticException e) {
            System.out.println("���������Ե����㣡��");
            e.printStackTrace();
        }

        // �����±��쳣
        try {
            int[] a = new int[10];
            System.out.println("����������ֵ:" + a[10]);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("�����±��쳣����");
            e.printStackTrace();
        }

        // ��ָ���쳣
        try {
            @SuppressWarnings("unused")
            String str = null;
            // System.out.println("������ַ�������:" + str.length());
        } catch (NullPointerException e) {
            System.out.println("��ָ���쳣����");
            e.printStackTrace();
        }
    }
}