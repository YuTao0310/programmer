public class Offer10I {
    
}

class Solution {
    /**
     * 超出时间限制
     */
    /*
    public int fib(int n) {
        if (n ==0 || n == 1) {
            return n;
        }
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }
    */
    /**
     * 新方法1: while循环
     */
    /*
        static int mod = (int)(1e9 + 7);
        public int fib(int n) {
            int pre2 = 0;
            int pre1 = 1;
            int pre  = 1;
            if (n == 0) {
                return n;
            }
            for (int i = 1; i < n; i++) {
                pre = (pre2 + pre1) % mod;
                pre2 = pre1;
                pre1 = pre;
            }
            return pre;
        }
    */
    /**
     * 新方法2：递归实现
     */
    private static final int mod = (int)(1e9 + 7);
    public int fib(int n) {
        return fib_recurse(n, 0, 0, 1);
    }
    private int fib_recurse(int n, int start, int previous2, int previous1) {
        if (n == start) {
            return previous2;
        }
        return fib_recurse(n, start + 1, previous1, (previous1 + previous2) % mod);
    }
}
