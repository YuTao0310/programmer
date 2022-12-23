package reflection;

public class StaticSynchronizedTest {
	public static void main(String[] args) throws InterruptedException {
		Thread t1= new Thread(){
			public void run(){
				//����method1
				StaticSynchronizedTest.method1();
			}
		};
		t1.setName("��һ���߳�");
		t1.start();
		
		//��֤��һ���߳��ȵ���method1
		Thread.sleep(1000);
		
		Thread t2= new Thread(){
			public void run(){
				//����method2
				StaticSynchronizedTest.method2();
			}
		};
		t2.setName("�ڶ����߳�");
		t2.start();

        Thread t3 = new Thread("�������߳�") {
            public void run() {
                new StaticSynchronizedTest().method3();
            }
        };
        t3.start();
	}

	public static void method1() {

		synchronized (StaticSynchronizedTest.class) {
			// ����method1���ԣ�ͬ��������TestReflection.class��ֻ��ռ��TestReflection.class�ſ���ִ�е�����
			System.out.println(Thread.currentThread().getName() + " ������method1����");
			try {
				System.out.println("����5��");
				Thread.sleep(5000);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
	}

	public static synchronized void method2() {
		// ����mehotd2���ԣ���Ȼ�и�ͬ������ͨ���۲췢�֣���ĳ���߳���method1�У�ռ����TestReflection.class֮��
		// ���޷�����method2���ƶϳ���method2��ͬ�����󣬾���TestReflection.class
		System.out.println(Thread.currentThread().getName() + " ������method2����");
		try {
			System.out.println("����5��");
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

    public synchronized void method3() {
        System.out.println(Thread.currentThread().getName() + " ������method3����");
		try {
			System.out.println("����5��");
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
    }
}
