package multiplethread;

import java.util.LinkedList;
import java.util.Deque;

public class MyStack<T> implements Stack<T> {
    
    Deque<T> q = new LinkedList<>();
    
    public MyStack() {

    }

    public MyStack(T h) {
        q.add(h);
    }

    public MyStack(T[] hs) {
        for (T h : hs) {
            q.add(h);
        }
    }

    @Override
    public synchronized void push(T h) {
        while (q.size() >= 200) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        q.addLast(h);
        this.notifyAll();
    }

    @Override
    public synchronized T pull() {
        while (q.size() <= 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T h = peek();
        q.removeLast();
        this.notifyAll();
        return h;
    }

    @Override
    public synchronized T peek() {
        return q.getLast(); 
    }
}
