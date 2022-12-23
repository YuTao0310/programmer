package multiplethread;

class Producer extends Thread {
    MyStack<Character> s ;

    public Producer(MyStack<Character> s) {
        this.s = s;
    }

    public Producer(MyStack<Character> s, String name) {
        super(name);
        this.s = s;
    }

    @Override
    public void run() {
        String dict = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        while (true) {
            int index = (int)(Math.random() * 26);
            s.push(dict.charAt(index));
            System.out.println(this.getName() + " Ñ¹Èë: " + dict.charAt(index));
        }
    }
}

class Consumer extends Thread {
    MyStack<Character> s;

    public Consumer(MyStack<Character> s) {
        this.s = s;
    }

    public Consumer(MyStack<Character> s, String name) {
        super(name);
        this.s = s;
    }

    @Override
    public void run() {
        while(true) {
            char c = s.pull();
            System.out.println(this.getName() + " µ¯³ö£º" + c);
        }
    }
}
public class ProducerConsumer {
    public static void main(String[] args) {
        MyStack<Character> s = new MyStack<>();
        Producer[] prods = new Producer[3];
        Consumer[] cons = new Consumer[3];
        for (int i = 0; i < prods.length; i++) {
            prods[i] = new Producer(s, "producer" + (i + 1));
            prods[i].start();
        }
        for (int i = 0; i < cons.length; i++) {
            cons[i] = new Consumer(s, "consumer" + (i + 1));
            cons[i].start();
        }
    }
}
