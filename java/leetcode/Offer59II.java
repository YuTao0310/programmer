import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Offer59II {
    class MaxQueue {
        List<Integer> data = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();
        public MaxQueue() {

        }

        public int max_value() {
            return deque.peekFirst();
        }

        public void push_back(int value) {
            data.add(value);
            while (!deque.isEmpty() && deque.peekLast() < value) {
                deque.pollLast();
            }
            deque.add(value);
        }

        public int pop_front() {
            if (data.isEmpty()) {
                return -1;
            }
            int ans = data.remove(0);
            if (ans == deque.peekFirst()) {
                deque.pollFirst();
            }
            return ans;
        }
    }

    /**
     * Your MaxQueue object will be instantiated and called as such:
     * MaxQueue obj = new MaxQueue();
     * int param_1 = obj.max_value();
     * obj.push_back(value);
     * int param_3 = obj.pop_front();
     */
}
