import java.util.LinkedList;
import java.util.Deque;

public class Test {
    public static void main(String[] args) {
        Deque<Integer> deque = new LinkedList<>();
        deque.offerFirst(1);
        deque.offerLast(2);
        System.out.println(deque.pollLast());
        System.out.println(deque.pollFirst());
    }
}
