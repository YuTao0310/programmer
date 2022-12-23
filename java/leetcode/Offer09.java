import java.util.Stack;


/**
 * 用两个栈实现一个队列。队列的声明如下，
 * 请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。
 * (若队列中没有元素，deleteHead 操作返回 -1 )
 */

/**
 * Your CQueue object will be instantiated and called as such:
 * CQueue obj = new CQueue();
 * obj.appendTail(value);
 * int param_2 = obj.deleteHead();
 */

public class Offer09 {
    public static void main(String[] args) {
        CQueue obj = new CQueue();
        for (int i = 0; i < 10; i++) {
            obj.appendTail(i);
        }
        for (int j = 0; j < 10; j++) {
            System.out.println(obj.deleteHead());
        }
    }
}

class CQueue {

    private Stack<Integer> firstStack;
    private Stack<Integer> lastStack;
    public CQueue() {
        this.firstStack = new Stack<>();
        this.lastStack = new Stack<>();
    }
    
    public void appendTail(int value) {
        lastStack.push(value);
    }
    
    public int deleteHead() {
        if (firstStack.empty()) {
            if (lastStack.empty()) {
                return -1;
            } else {
                while (!lastStack.empty()) {
                    firstStack.push(lastStack.pop());
                }
            }
        }
        return firstStack.pop();
    }
}