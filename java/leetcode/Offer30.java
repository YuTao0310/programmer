import java.util.LinkedList;

public class Offer30 {
    class MinStack {
        private LinkedList<Integer> list;
        private LinkedList<Integer> minList;
        public MinStack() {
            list = new LinkedList<>();
            minList = new LinkedList<>();
        }
        
        public void push(int x) {
            list.addFirst(x);
            if (minList.isEmpty()) {
                minList.addFirst(x);
            } else {
                int pre = minList.getFirst();
                minList.addFirst(x > pre ? pre : x);
            }
        }
         
        public void pop() {
            list.removeFirst();
            minList.removeFirst();
        }
        
        public int top() {
            return list.getFirst();
        }
        
        public int min() {
            return minList.getFirst();
        }
    }
}
