# 剑指offer09-用两个栈实现队列

用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

```java
import java.util.Stack
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
```

# 剑指offer10-I.斐波那契数列

写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：

```java
F(0) = 0,   F(1) = 1
F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
```

斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

```java
    public int fib(int n) {
        if (n ==0 || n == 1) {
            return n;
        }
        else {
            return fib(n - 1) + fib(n - 2);
        }
    }
```

`时间复杂度：O(2^n) 空间复杂度：O(n)`

上述方法运行过程中会重复计算内容，从而超出时间限制，因此改为采用下述方法。

**法1：while循环实现（动态规划法）**

`时间复杂度：O(n) 空间复杂度O(1)`

<img src="./picture/fibonacci.drawio.png" style="zoom:100%;" />

用pre表示需要返回的值。

```java
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
```

或者

用pre2表示需要返回的值。

```java
static int mod = (int)(1e9 + 7);
public int fib(int n) {
    int pre2 = 0;
    int pre1 = 1;
    int pre  = 1;
    for (int i = 0; i < n; i++) {
        pre = (pre2 + pre1) % mod;
        pre2 = pre1;
        pre1 = pre;
    }
    return pre2;
}
```
**法2：优化递归实现**

`时间复杂度：O(n) 空间复杂度：O(n)  `

```java
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
```

**法3：记忆化递归方法**

`时间复杂度：O(n) 空间复杂度：O(n)`

递归法的基础上，新建一个长度为 n 的数组，用于在递归时存储 f(0)f(0) 至 f(n)f(n) 的数字值，重复遇到某数字则直接从数组取用，避免了重复的递归计算。

**法4：矩阵求幂法**

`时间复杂度：O(logn) 空间复杂度：O(1)`

使用矩阵快速幂的方法可以降低时间复杂度。

首先我们可以构建这样一个递推关系：
$$
\left[
\begin{matrix}
1 & 1 \\
0 & 1 
\end{matrix}
\right]
\left[
\begin{matrix}
F(n) \\
F(n-1)
\end{matrix}
\right]
=\left[
\begin{matrix}
F(n) + F(n-1) \\
F(n)
\end{matrix}
\right]
=\left[
\begin{matrix}
F(n+1) \\
F(n)
\end{matrix}
\right]
$$
因此：
$$
\left[
\begin{matrix}
F(n+1) \\
F(n)
\end{matrix}
\right]=\left[
\begin{matrix}
1 & 1 \\
1 & 0 
\end{matrix}
\right]^n
\left[
\begin{matrix}
F(1) \\
F(0)
\end{matrix}
\right]
$$
令：
$$
M = \left[\begin{matrix} 1 & 1 \\ 1 & 0 \end{matrix}\right]
$$
因此只要我们能快速计算矩阵 M 的 n 次幂，就可以得到 F(n)的值。如果直接求取 $M^n$，时间复杂度是 O(n)，可以定义矩阵乘法，然后用快速幂算法来加速这里 $M^n$  的求取。

```java
static final int MOD = 1000000007;

    public int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n - 1);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (int) (((long) a[i][0] * b[0][j] + (long) a[i][1] * b[1][j]) % MOD);
            }
        }
        return c;
    }
```

# 剑指offer03-数组中重复的数字

找出数组中重复的数字。


在一个长度为 n 的数组 nums 里的所有数字都在 0～n-1 的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。

**方法1：暴力搜索**

`时间复杂度：O(n)~O(n^2) 空间复杂度：O(1)`

**方法2：使用集合的思想**

`时间复杂度：O(n) 空间复杂度：O(n)`

时间复杂度：O(n)。
遍历数组一遍。使用哈希集合（HashSet），添加元素的时间复杂度为 O(1)，故总的时间复杂度是 O(n)。
空间复杂度：O(n)。不重复的每个元素都可能存入集合，因此占用 O(n)额外空间。

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int repeat = -1;
        for (int num : nums) {
            if (!set.add(num)) {
                repeat = num;
                break;
            }
        }
        return repeat;
    }
}
```

>hashset添加元素的时间复杂度为O(1)的原因：
>
>hashset通过内置的hashmap对象的put方法来添加元素，
>
>  public boolean add(E e) {
>
>​    return map.put(e, PRESENT)==null;
>
>  }
>
>而hashmap的put方法中，同样的key，后者的value会覆盖前者的value，从而实现key的不重合；而在寻找key的过程，根据key的hash值能够快速定位其位置，整个put过程的时间复杂度是O(1)。

**方法3：原地交换**

`时间复杂度：O(n) 空间复杂度O(1)`

题目说明尚未被充分使用，即 在一个长度为 n 的数组 nums 里的所有数字都在 0 ~ n-1 的范围内 。 此说明含义：数组元素的 索引 和 值 是 一对多 的关系。
因此，可遍历数组并通过交换操作，使元素的 索引 与 值 一一对应（即 nums[i] = inums[i]=i ）。因而，就能通过索引映射对应的值，起到与字典等价的作用。

![Picture0.png](https://pic.leetcode-cn.com/1618146573-bOieFQ-Picture0.png)

遍历中，第一次遇到数字 x 时，将其交换至索引 x 处；而当第二次遇到数字 x 时，一定有 nums[x] = x，此时即可得到一组重复数字。

**算法流程**：

1. 遍历数组 nums ，设索引初始值为 i = 0;
   1. 若 nums[i] = i： 说明此数字已在对应索引位置，无需交换，因此跳过；
   2. 若 nums[nums[i]] = nums[i] ： 代表索引 nums[i]处和索引 ii处的元素值都为 nums[i]，即找到一组重复值，返回此值 nums[i]；
   3. 否则： 交换索引为 i和 nums[i]的元素值，将此数字交换至对应索引位置。
2. 若遍历完毕尚未返回，则返回 -1。

```java
class Solution {
    public int findRepeatNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] == i) {
                i++;
                continue;
            }
            if(nums[nums[i]] == nums[i]) return nums[i];
            int tmp = nums[i];
            nums[i] = nums[tmp];
            nums[tmp] = tmp;
        }
        return -1;
    }
}
```

# 剑指offer04-二维数组中的查找

在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。

示例:

现有矩阵 matrix 如下：

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
给定 target = 5，返回 true。
给定 target = 20，返回 false。

**方法1：暴力搜索**

果不考虑二维数组排好序的特点，则直接遍历整个二维数组的每一个元素，判断目标值是否在二维数组中存在。

依次遍历二维数组的每一行和每一列。如果找到一个元素等于目标值，则返回 true。如果遍历完毕仍未找到等于目标值的元素，则返回 false

`时间复杂度：O(n·m) 空间复杂度：O(1)`

**方法2：类似于一维分块搜索**

当每一行对应的子数组的第一个元素值小于`target`时，对字数组进行二分搜索。

`时间复杂度：O(n·log2(m)) 空间复杂度：O(1)或者O(log2(n))[递归实现二分法]`

```java
class Solution {
 
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            
            for (int i = 0; i < matrix.length; i++) {
                if (matrix[i][0] > target) {
                    break;
                }
                if (findNumberIn1DArray(matrix[i], target) == true) {
                    return true;
                }
            }
            return false;
        }
        public boolean findNumberIn1DArray(int[] matrix, int target) {
            int start = 0;
            int end = matrix.length - 1;
            while (start <= end) {
                int mid = (start + end) / 2;
                if (matrix[mid] < target) {
                    start = mid + 1;
                } else if (matrix[mid] > target) {
                    end = mid - 1;
                } else {
                    return true;
                }
            }
            return false;
        }
    } 
```

**方法3：类似于二叉树查找法**

二维数组的右上角开始查找。如果当前元素等于目标值，则返回 true。如果当前元素大于目标值，则移到左边一列。如果当前元素小于目标值，则移到下边一行。

可以证明这种方法不会错过目标值。如果当前元素大于目标值，说明当前元素的下边的所有元素都一定大于目标值，因此往下查找不可能找到目标值，往左查找可能找到目标值。如果当前元素小于目标值，说明当前元素的左边的所有元素都一定小于目标值，因此往左查找不可能找到目标值，往下查找可能找到目标值。

`时间复杂度：O(n+m) 空间复杂度：O(1)`

```java
class Solution {
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if (matrix.length == 0 || matrix[0].length == 0) {
                return false;
            }
            int x = 0;
            int y = matrix[0].length - 1;
            while (!(x == matrix.length || y == -1)) {
                if (matrix[x][y] == target) {
                    return true;
                } else if (matrix[x][y] < target) {
                    x++;
                } else {
                    y--;
                }
            }
            return false;
        }
    }
```

# 剑指Offer10II-青蛙跳台阶

一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。

答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。

设跳上 nn 级台阶有 f(n)种跳法。在所有跳法中，青蛙的最后一步只有两种情况： 跳上 1级或 2级台阶。
当为 1级台阶： 剩 n-1个台阶，此情况共有 f(n-1) 种跳法；
当为 2 级台阶： 剩 n-2 个台阶，此情况共有 f(n-2) 种跳法。
f(n)为以上两种情况之和，即 f(n)=f(n-1)+f(n-2)，以上递推性质为斐波那契数列。本题可转化为 求斐波那契数列第 nn项的值 ，与 面试题10- I. 斐波那契数列 等价，唯一的不同在于起始数字不同。
青蛙跳台阶问题： $f(0)=1, f(1)=1 , f(2)=2$；
斐波那契数列问题： $f(0)=0, f(1)=1 , f(2)=1$ 。

方法思路与剑指Offer10I中相同，在这里贴一个采用使用缓存的递归法解决思路。

```java
class Solution {
        HashMap<Integer, Integer> cache = new HashMap<>();
        public int numWays(int n) {
            switch (n) {
                case 0 :
                case 1 :
                    return 1;
                case 2 :
                    return 2;
                default :
                    if (!cache.containsKey(n - 1)) {
                        cache.put(n - 1, numWays(n - 1));
                    }  
                
                    if (!cache.containsKey(n - 2)) {
                        cache.put(n - 2, numWays(n - 2));
                    } 
    
                    return (cache.get(n - 1) + cache.get(n - 2)) % 1000000007;
            }
        }
    }
```

# 剑指Offer11-旋转数组的最小数字

把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。

给你一个可能存在 重复 元素值的数组 numbers ，它原来是一个升序排列的数组，并按上述情形进行了一次旋转。请返回旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一次旋转，该数组的最小值为1。  

**法1：从数组后端采用顺序法**

`时间复杂度:O(n) 空间复杂度:O(1)`

```java
	class Solution1 {
        public int minArray(int[] numbers) {
            int max = numbers.length - 1;
            for (int i = max; i > 0; i--) {
                if (numbers[i - 1] > numbers[i]) {
                    return numbers[i];
                }
            }
            return numbers[0];
        }
    }
```

**法2：二分法**

`时间复杂度:O(logn) 空间复杂度:O(1)`

与二分查找法的区别，二分查找法比较的值是目标值`target`，此二分法比较的值是右端点值。

```java
	class Solution2 {
        public int minArray(int[] numbers) {
            int low = 0;
            int high = numbers.length - 1;
            while (low < high) {
                int pivot = low + (high - low) / 2;
                if (numbers[pivot] < numbers[high]) {
                    high = pivot;
                } else if (numbers[pivot] > numbers[high]) {
                    low = pivot + 1;
                } else {
                    high -= 1;
                }
            }
            return numbers[low];
        }
    }
```

# 剑指Offer12-矩阵中的路径

给定一个 $m * n$ 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

==**整体思路**==

深度优先搜索(DFS)+回溯思想+剪枝

**整体思路**

* 深度优先搜索： 可以理解为暴力法遍历矩阵中所有字符串可能性。DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。
* 回溯思想：标记已经走过的方格
* 剪枝： 在搜索中，遇到 这条路不可能和目标字符串匹配成功 的情况（例如：此矩阵元素和目标字符不同、此元素已被访问），则应立即返回，称之为 可行性剪枝 。

**DFS搜索**

* 递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。

* 终止条件：

  返回 false ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ） 。

  返回 true： k = len(word) - 1 ，即字符串 word 已全部匹配。

* 递推工作：
  标记当前矩阵元素： 将 board[i)[j)修改为 空字符 '' ，代表此元素已访问过，防止之后搜索时重复访问。 搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），并记录结果至 res 。 还原当前矩阵元素： 将 board[i)[j)元素还原至初始值，即 word[k] 。[1][2

* 返回值： 返回布尔量 res ，代表是否搜索到目标字符串。

> 使用空字符（Python: '' , Java/C++: '\0' ）做标记是为了防止标记字符与矩阵原有字符重复。当存在重复时，此算法会将矩阵原有字符认作标记字符，从而出现错误。。 

**复杂度分析**

> M,N 分别为矩阵行列大小， KK为字 符串 word 长度。

* 时间复杂度 $O(3^KMN)$： 最差情况下，需要遍历矩阵中长度为 K 字符串的所有方案，时间复杂度为 $O(3^K)$；矩阵中共有 MNM个起点，时间复杂度为 $O(MN)$ 。方案数计算： 设字符串长度为 KK，搜索中每个字符有上、下、左、右四个方向可以选择，舍弃回头（上个字符）的方向，剩下 3种选择，因此方案数的复杂度为 $O(3^K)$。

* 空间复杂度 $O(K)$ ： 搜索过程中的递归深度不超过 K ，因此系统因函数调用累计使用的栈空间占用 $O(K)$（因为函数返回后，系统调用的栈空间会释放）。最坏情况下 $K = MN$ ，递归深度为 $MN$，此时系统栈使用 $O(MN)$的额外空间。

  ==如果递归中使用全局变量，则为O(K);如果传递broad参数，则为O(KMN)==

