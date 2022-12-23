package test;

public class InstrRearrTest1 {
	static int a = 0;
    static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        while (true){
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    a = 1;
                    flag = true;
                }
            };
            Thread t2 = new Thread() {
                @Override
                public void run() {
                    if (flag){
                        if (a==0){
                            System.out.println("----------指令重排序------------");
                        }
                    }
                }
            };

            t1.start();
            t2.start();
            t1.join();
            t2.join();

            a = 0;
            flag = false;
        }
    }
}
