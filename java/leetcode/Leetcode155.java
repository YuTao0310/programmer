import java.util.ArrayDeque;
import java.util.Deque;

public class Leetcode155 {
    class Node {
        int val;
        int min;
        Node() {}
        Node(int val, int min) {this.val = val; this.min = min;}
    }
    class MinStack {
        Deque<Node> deque;
        public MinStack() {
            deque = new ArrayDeque<>();
        }
        
        public void push(int val) {
            if (deque.isEmpty()) {
                deque.push(new Node(val, val));
            } else {
                int m = Math.min(deque.peek().min, val);
                deque.push(new Node(val, m));
            }
        }
        
        public void pop() {
            deque.pop();
        }
        
        public int top() {
            return deque.peek().val;
        }
        
        public int getMin() {
            return deque.peek().min;
        }
    } 
}
