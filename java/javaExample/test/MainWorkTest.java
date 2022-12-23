package test;

import java.util.concurrent.ExecutorService;

class ThreadDemo implements Runnable {

	private boolean flag = false;

	@Override
	public void run() {

		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {

		}
		flag = true;
		System.out.println("flag=" + isFlag());
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}


public class MainWorkTest {
   public static void main(String[] args) throws InterruptedException {
    ThreadDemo td = new ThreadDemo();
    Thread threadA = new Thread(td);
    threadA.start();

    while (true) {
        //threadA.join();
        //System.out.println("打开这句以后，会进入if条件中，程序可正常结束！");
        //或者是打开下面这句
        //Thread.sleep(1);
        if (td.isFlag()) {
            System.out.println("------------------");
            break;
        }
    }
   } 

}
