package collection;

import hero.Hero;
import java.util.LinkedList;
import java.util.Deque;

public class MyStack implements Stack {
    
    Deque<Hero> q = new LinkedList<>();
    
    public MyStack() {

    }

    public MyStack(Hero h) {
        q.add(h);
    }

    public MyStack(Hero[] hs) {
        for (Hero h : hs) {
            q.add(h);
        }
    }

    @Override
    public void push(Hero h) {
        q.addLast(h);
    }

    @Override
    public Hero pull() {
        Hero h = peek();
        q.removeLast();
        return h;
    }

    @Override
    public Hero peek() {
        return q.getLast(); 
    }

    public static void main(String[] args) {
        Hero h1 = new Hero("One");
        Hero h2 = new Hero("Two");
        Hero h3 = new Hero("Three");
        MyStack s = new MyStack();
        s.push(h1);
        s.push(h2);
        s.push(h3);
        System.out.println(s.peek());
        System.out.println(s.pull());
        System.out.println(s.peek());
    }
}
