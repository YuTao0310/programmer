ACM模式注意点

## 1 Scanner相关说明

需要注意的是，如果在通过nextInt()读取了整数后，再接着读取字符串，读出来的是回车换行:"\r\n",因为nextInt仅仅读取数字信息，而不会**读取**回车换行"\r\n".

所以，如果在业务上需要读取了整数后，接着读取字符串，那么就应该连续执行两次nextLine()，第一次是取走回车换行，第二次才是读取真正的字符串。或者可以直接使用next可以避免这类问题。

[【ACM模式】牛客网ACM机试模式Python&Java&C++主流语言OJ输入输出案例代码总结](https://blog.csdn.net/qq_39295220/article/details/116785551)
[【Java学习笔记】Scanner类中next系列方法的总结](https://blog.csdn.net/cy973071263/article/details/88908810)

## 2 数组拷贝方法

```java
Arrays.copyOf(dataType[] srcArray,int length);
Arrays.copyOfRange(dataType[] srcArray,int startIndex,int endIndex)
System.arraycopy(dataType[] srcArray,int srcIndex,int destArray,int destIndex,int lengt)
```

## 3  随机数生成

```java
# 生成整数
new Random().nextInt(10) //生成0-9的数字
int nextInt()            //随机返回一个int型整数
int nextInt(int num)         //随机返回一个值在[0,num)的int类型的整数,包括0不包括num
# 生成浮点数 0-1之间
new Random().nextDouble()
```

## 4 日志输出方法

```java
# 1
System.out.printf("%d\n", i)
# 2
System.out.format("%d\n", i)
# 3
System.out.print(String.format("%d\n", i))
# 4
Formatter f = new Formatter(System.out)
f.format("%d\n", i)
```

## 5 比较器

Arrays.sort、Collections.sort默认升序 PriorityQueue默认最小堆。
Arrays.sort如果需要自定义比较器，不能针对基本数据类型的数组。

方式一：

使用Comparator。

```java
        //引入Comparator，指定比较的算法
        Comparator<Hero> c = new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                //按照hp进行排序
                if(h1.hp>=h2.hp)
                    return 1;  //正数表示h1比h2要大
                else
                    return -1;
            }
        };
        Collections.sort(heros,c);
		Collections.sort(heros, (h1, h2) -> h1.hp - h2.hp);
```

方式二：

实现Comparable接口。

```java
public class Hero implements Comparable<Hero>{
    public String name;
    public float hp;
       
    public int damage;
  
    @Override
    public int compareTo(Hero anotherHero) {
        if(damage<anotherHero.damage)
            return 1; 
        else
            return -1;
    }
}
```

# 小红书8.6笔试

第二题：类似于01背包；有n件事情，每件事情都有时间ti,精力hi,快乐值ai，如果小红做某件事情就会消耗对应的时间tj,精力hj,从而获得快乐值aj；求在消耗时间不超过 t,且精力不超过 h的情况下，小红所能获得的最大快乐值是多少；

输入示例

第一行输入表示事件数量n;

第二行输入表示小红的时间 t ,精力 h;

后面连续输入n行，每一行表示每件事情所对应的时间，精力，快乐值；

3

5  4

1 2 2

2 1 3

4 1 5

输出结果：7

==动态规划 迭代 01背包==

```java
public class RedBookTest2 {
    static int n;
    static int[] t , h , a;

    public static void main(String[] args) {
        n = 3;
        t = new int[]{1, 2, 4};
        h = new int[]{2, 3, 3};
        a = new int[]{2, 3, 5};
        int Hmax = 5, Tmax = 5;
        System.out.println(findBestHappiness1(0, Hmax, Tmax));
         System.out.println(findBestHappiness2(0, Hmax, Tmax));

    }

    /* DFS 迭代实现 */
    private static long findBestHappiness1(int index, int Hmax, int Tmax) {
        if (index >= n) return 0;
        if (h[index] > Hmax || t[index] > Tmax) return findBestHappiness1(index + 1, Hmax, Tmax);
        else return Math.max(findBestHappiness1(index + 1, Hmax, Tmax), a[index] + findBestHappiness1(index + 1, Hmax - h[index], Tmax - t[index]));
    }

    /* 动态规划实现 */
    private static long findBestHappiness2(int index, int Hmax, int Tmax) {
        long [][][] dp = new long[n + 1][Hmax + 1][Tmax + 1];
        for (int i = 1; i <= n; i++)
            for (int j = 1; j <= Hmax; j++)
                for (int k = 1; k <= Tmax; k++) {
                    if (h[i - 1] > j || t[i - 1] > k) dp[i][j][k] = dp[i - 1][j][k];
                    else dp[i][j][k] = Math.max(dp[i - 1][j][k], a[i - 1] + dp[i - 1][j - h[i - 1]][k  - t[i - 1]]);
                }
        return dp[n][Hmax][Tmax];
    }
}
```

第三题 一个树，每个节点有一个权重，初始每个节点都是白色，每次操作可以选择两个节点，如果这俩节点权重和为质数，则可以选一个染红。问最多能染红多少节点？
    思路：dp dfs, 类似leetcode打家劫舍3。二叉树=>n叉树，相邻只能抢一个=>相邻且权重和为质数 才能抢一个

==DFS 贪心==

其实就是先素数打表，然后建图，dfs递归，从叶子节点开始判断，如果叶子节点和父节点都是白的，权值和是素数，就把叶子结点染红，结果+1，标记红色，然后向上归，这样也是有点贪心的思想，因为叶子结点染红了以后不影响他的父节点和父节点的父节点满足条件和染红

# 美团8.12笔试

第五题 一个树，每个节点有一个权重，初始每个节点都是白色，每次操作可以选择两个节点，如果这俩节点权重和为整数的平方，则可以选两个染红。问最多能染红多少节点？

==DFS 贪心==

```java
public class MeiTuanTest5 {
    static int n;
    static Map<Integer, List<Integer>> map = new HashMap<>();
    static int[] vals;

    static class Result {
        int count;
        boolean isRed;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        vals = new int[n];
        for (int i = 0; i < n; i++) vals[i] = sc.nextInt();
        for (int i = 0; i < n - 1; i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            if (x < y) map.computeIfAbsent(x, t -> new LinkedList<>()).add(y);
            else map.computeIfAbsent(y, t -> new LinkedList<>()).add(x);
        }
        Result res = findMaxNodes(1);
        System.out.println(res.count);
    }

    public static Result findMaxNodes(int root) {
        // notselected, selected
        Result res = new Result();
        res.count = 0;
        res.isRed = false;
        List<Integer> children = map.get(root);
        if (children == null) return res;
        for (Integer i : children) {
            Result temp = findMaxNodes(i);
            res.count += temp.count;
            if (isPow(vals[root - 1], vals[i - 1]) && temp.isRed == false) {
                temp.isRed = true;
                res.isRed = true;
                res.count += 2;
            }
        }
        return res;
    }

    private static boolean isPow(int x, int y) {
        long temp1 = x * 1L * y;
        long temp2 = (long)Math.pow(temp1, 0.5);
        return temp1 == temp2 * temp2;

    }
}
```

# 美团第二次笔试8.19

**第三题**

01串翻转

小美定义一个 01 串的权值为：每次操作选择一位取反，使得相邻字符都不相等的最小操作次数。

例如，"10001"的权值是 1，因为只需要修改一次：对第三个字符取反即可。

现在小美拿到了一个 01 串，她希望你求出所有非空连续子串的权值之和，你能帮帮她吗？

**输入描述**

一个仅包含'0'和'1'的字符串，长度不超过 2000。

**输出描述**

所有非空子串的权值和。

**示例1**

**输入**

10001

**输出**

8

**说明**

长度为 2 的子串中，有 2 个"00"的权值是 1。

长度为 3 的 3 个子串权值都是 1。

长度为 4 的 2 个子串权值都是 1。

长度为 5 的 1 个子串权值是 1。

总权值之和为 2+3+2+1=8

==前缀和==

```java
s = [int(c) for c in input()]
n = len(s)

# 枚举第一位就好了
s1 = list(s)
s2 = list(s)

s2[0] ^= 1

for i in range(1,n):
    s1[i] = s1[i-1] ^ 1
    s2[i] = s2[i-1] ^ 1

pres1, pres2 = [0]*(n+1),[0]*(n+1)

for i in range(1, n+1):
    pres1[i], pres2[i] = pres1[i-1], pres2[i-1]
    if s[i-1] != s1[i-1]: pres1[i] += 1
    if s[i-1] != s2[i-1]: pres2[i] += 1


res = 0
for i in range(n):
    for j in range(i+1,n):
       res += min(pres1[j+1]-pres1[i], pres2[j+1]-pres2[i])

print(res)
```

**第四题**

小美的数组构造。

小美拿到了一个数组a，她准备构造一个数组b满足：

1.b的每一位都和a对应位置不同，即bi≠ai。

2.b的所有元素之和都和a相同。

3.b的数组均为正整数。

请你告诉小美有多少种构造方式。由于答案过大，请对10^9+7取模。

**输入描述**

第一行输入一个正整数n，代表数组的大小。

第二行输入n个正整数ai，代表小美拿到的数组。

![图片](https://mmbiz.qpic.cn/sz_mmbiz_png/yUlgRBVNujIqIqcGWKicTb8ajhLln4rjQBQY0UkcLddAko6ibVdc4j8Qw8MMMqrgl81LVWfQlGicibHicAzdysuyUvw/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

**输出描述**

一个整数，代表构造方式对10^9+7)取模的值。

==动态规划==

记忆化搜索。

对于每一个位置来说，可以填充的数据是 **当前剩余的数字**且不等于原数组的数字。（这个数据规模很明显都指向这个角度的DP）。

```python
n = int(input())

a = [int(c) for c in input().split()]
dp = {}
def dfs(i,j):
    if i >= n - 1:
        return 1 if j > 0 and j != a[-1] else 0
    if (i,j) in dp: return dp[(i,j)]
    cnt = 0
    for c in range(1,j+1):
        if c != a[i]:
            cnt += dfs(i+1, j-c)
            cnt %= 10**9 + 7
    dp[(i, j)] = cnt % (10**9 + 7)
    return cnt

print(dfs(0, sum(a)))
```

**第五题**

小美的数组操作

小美拿到了一个数组，她每次可以进行如下操作：选择两个元素，一个加 1，另一个减 1。

小美希望若干次操作后，众数的出现次数尽可能多。你能帮她求出最小的操作次数吗？

众数定义：在一组数据中，出现次数最多的数据，是一组数据中的原数据，而不是相应的次数。一组数据中的众数不止一个，如数据2、3、-1、2、1、3中，2、3都出现了两次，它们都是这组数据中的众数。

**输入描述**

第一行为一个正整数n，代表数组的大小。

第二行输入n个正整数ai，代表小美拿到的数组。

![图片](https://mmbiz.qpic.cn/sz_mmbiz_png/yUlgRBVNujIqIqcGWKicTb8ajhLln4rjQOtreZSoToNp82picvicXhjD5XHz7WlmpVmgj82ldJkDibKMNgmPfdiazeA/640?wx_fmt=png&wxfrom=5&wx_lazy=1&wx_co=1)

**输出描述**

一个整数，代表最小的操作次数。



思维题。

不难发现，如果所有的数字加起来切好是n的倍数，那么是肯定可以把所有数字归为同一个数字的。

否则，我们是一定可以产出n-1个众数的，关键就是排除哪个元素呢？排除的元素要使得最后的操作次数尽可能少，显然是应该排除最大或者最小（与平均数偏移最多。）枚举这两种情况即可。

```java
from itertools import accumulate

n = int(input())
a = [int(c) for c in input().split()]

a.sort()
sum_ = sum(a)
if sum_ % n == 0:
    res = 0
    avg = sum_ // n
    for i in a:
        res += abs(avg - i)
    res //= 2
    print(res)
else:
    nums1 = a[1:]
    nums2 = a[:-1]

    def cal(nums):
        sum_ = sum(nums)
        avg = sum_ // len(nums) + (0 if sum_ % len(nums) == 0 else 1)
        x,y = 0, 0
        for num in nums:
            if num >= avg: x+= num -avg
            else: y+= avg - num
        return max(x,y)

    print(min(cal(nums1), cal(nums2)))
```



# 字节8.10面试

不超过n的最大值

```
num=500
arr=[5, 6, 8, 7]
res=88
```

==贪心 二分==

```java
import java.util.Arrays;

public class ZijieTest {
    public static void main(String[] args) {
        System.out.println(build(500, new int[]{5, 6, 8, 7}));
    }
    public static int build(int num, int[] arr) {
        //初始化参数
        String str = String.valueOf(num - 1);
        str = str + str.charAt(str.length() - 1);
        char[] res = new char[str.length() - 1];
        Arrays.fill(res, '0');
        Arrays.sort(arr);
        int len = res.length;
        boolean flag = false;
        for (int i = 0; i < len; i++) {//从高位到低位遍历原数字
            int index = flag ? arr.length - 1 : search(str, i, arr);
            res[i] = index != -1 ? (char) (arr[index] + '0') : '0';
            if (res[i] < str.charAt(i)) {
                flag = true;
            }
        }
        return Integer.parseInt(new String(res).substring(0,res.length));
    }
    public static int search(String str, int i, int[] arr) {
        int find = str.charAt(i + 1) - '0' >= arr[0] ? str.charAt(i) - '0' : str.charAt(i) - '0' - 1;
        int left = 0, right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (find > arr[mid]) {
                left = mid + 1;
            } else if (find < arr[mid]) {
                right = mid - 1;
            } else {
                index = mid;
                break;
            }
        }
        return index == left + ((right - left) >> 1) ? index : right;
    }
}
```

# 京东 8.12

https://mp.weixin.qq.com/s/WgcGqoDsGz8L_aiFgYvUDg

**第一题**

https://codefun2000.com/p/P1446

塔子哥有一个长度为n字符串，他想把这个字符串转换为回文串。塔子哥有两种魔法，使用一次魔法需要花费 1 法力值。

第一种魔法可以拿出字符串的第一个字母，并将其插在字符串的末尾。例如，对于字符串abbc ，通过第一种魔法后后变成了 bbca

第二种魔法是将字符串中的一个字符变成任意小写字母。

塔子哥现在问你，需要至少多少法力值才能将这个字符串转换为回文串。

==枚举==

```java
import java.util.Scanner;

public class Main {
    public static int op1(String s) {
        int res = 0;
        int l = 0;
        int r = s.length() - 1;
        while (l < r) {
            if (s.charAt(l) != s.charAt(r)) {
                res++;
            }
            l++;
            r--;
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        String s = scanner.next();
        int ans = op1(s);

        for (int i = 0; i < n; i++) {
            ans = Math.min(ans, op1(s) + i);
            s = s.substring(1) + s.charAt(0);
        }

        System.out.println(ans);
    }
}

```

**第二题**

https://codefun2000.com/p/P1447

塔子哥有一个特异数组，初始这些数组都是正整数。如果这个数组长度大于 1 ，就需要将最后两个数取出进行运算，并将运算结果再加到数组末尾。

运算有加法和乘法两种。不同于正常的运算，运算得到的结果只会有一位，就是正常运算结果的最低位（个位）。

例如，对于数组[1,3,1,4][1,3,1,4]，选择加法运算后，数组变为[1,3,5][1,3,5]，再接着选择第二种操作后，数组变为[1,5][1,5]。

现在，塔子哥想问你，当特异数组只剩下一个数时，这个数为 0,1,2,...,90,1,2,...,9 的可能有多少，换句话说，让你输出这 1010 个数为最终剩余的一个数的方案数，答案对 109+7109+7 取模。

==动态规划==

```java
import java.util.Scanner;

public class Main {
    static final int MOD = (int) 1e9 + 7;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] stk = new int[n];
        for (int i = 0; i < n; i++) {
            stk[i] = scanner.nextInt();
        }

        int[] dp = new int[10];

        // 特判 n=1 的情况
        if (n == 1) {
            if (stk[n - 1] < 10) {
                dp[stk[n - 1]] = 1;
            }
            for (int i = 0; i < 10; i++) {
                System.out.print(dp[i] + " ");
            }
            return;
        }

        dp[stk[n - 1] % 10] = 1;
        n--;

        while (n > 0) {
            int t = stk[n - 1] % 10;
            n--;
            // 滚动数组优化，ndp 此时表示 dp[i][0~9] 状态
            // dp数组 表示 dp[i+1][0~9] 状态
            int[] ndp = new int[10];

            for (int i = 0; i < 10; i++) {
                ndp[(i + t) % 10] += dp[i];
                ndp[(i + t) % 10] %= MOD;
                ndp[i * t % 10] += dp[i];
                ndp[i * t % 10] %= MOD;
            }

            // 最后更新 dp ，使得其由 dp[i+1] 状态转换为最新的 dp[i] 
            dp = ndp;
        }

        for (int i = 0; i < 10; i++) {
            System.out.print(dp[i] + " ");
        }
    }
}

```

**第三题**

https://codefun2000.com/p/P1448

塔子哥喜欢玩王者，尤其擅长奕星，并且时常沉迷于用奕星的大招困住敌人。

所以他设计了一个巨大棋局，棋盘中的格子只有两种可能，如果是 ′*X*′ 则表示这个格子可以作为棋盘的一个边角，如果是 ′.′′.′ 则表示不可以。

塔子哥要按这个棋局的情况去进行奕星的大招练习，即选择四个为 ′*X*′ 的不同格子，这四个格子可以构成一个正方形棋盘。

现在塔子哥想问你，可以获得多少个不同的正方形棋盘。

两个棋盘相同当且仅当四个边角都可以完全对应上，否则就是不同的正方形棋盘。

==枚举 + trick==

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        char[][] mp = new char[n][m];
        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < m; j++) {
                mp[i][j] = line.charAt(j);
            }
        }

        int ans = 0;
        for (int x1 = 0; x1 < n; x1++) {
            for (int y1 = 0; y1 < m; y1++) {
                if (mp[x1][y1] == 'X') {
                    for (int x2 = 0; x2 < n; x2++) {
                        for (int y2 = 0; y2 < m; y2++) {
                            if (x2 == x1 && y2 == y1) {
                                continue;
                            }
                            if (mp[x2][y2] == 'X') {
                                int dx = x2 - x1;
                                int dy = y2 - y1; // 按照向量顺时针或者逆时针
                                int x3 = x1 - dy; // 求到第三第四个点
                                int y3 = y1 + dx;
                                int x4 = x3 + dx;
                                int y4 = y3 + dy;
                                if (0 <= x3 && x3 < n && 0 <= y3 && y3 < m && 0 <= x4 && x4 < n && 0 <= y4 && y4 < m && mp[x3][y3] == 'X' && mp[x4][y4] == 'X') {
                                    ans++; // 符合要求就计数
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println(ans / 4); // 答案数除4
    }
}

```



# 大疆 8.13

**第一题**

解决下面无人机飞行规划问题：

给定一个环形路线实施无人机作业，在路线上有n个充电站，编号0... n-1，i号充电桩可以给无人机提供charge[i] 单位的电量，无人机从i号充电桩出发完 成作业，并飞往i+1号充电的需要消耗 cost[i] 单位的的电量。

假没无人机可以无限量充电。无人机从其中一个充电站完成充电出发(充电前初始电量为空)，给定正整数数组charge和cost，如果无人机可以顺时针绕环形路线一周完成作业并返回出发充电站，则返回出发时充电站的编号i，否则返回-1。

注，为简化问题，如果存在解，输入数据可保证解唯一。测试用例输入格式为 (length of charge,charge,length of cost,cost).

示例1. 输入: length of charge = 5, charge = [1,2,3,4,5], length of cost = 5, cost = [3,4,5,1,2] 输出: 3 解析:从3号充电站出发，电量为4;飞到4号充电站，电量为4-1+5=8;飞到0号充电站，电量为8-2+1=7;飞到1号充电站，电量为7-3+2=6;飞到2号充 电站，电量为6-4+3=5;飞回到3号充电站，电量5正好满足cost[2]

示例2. 输入: length of charge = 3,charge = [2,3.4], length of cost = 3,cost = [3,4,3] 输出:-1 解析: 从0号或者号充电站出发，得到的电量都不能满定经下一站的电量。从2号充电站出发，电量为4:飞到0号充电站，电量为4-3+2=3;飞到1号充电站，电量为3-3+3=3;但是无法返回2号充电站， cost[1]>3.

**思路与代码**

贪心。

此题为LC原题，134. 加油站

大体的思想就是求出diff数组：diff[i] = gas[i] - cost[i]。

1. 如果Σdiff < 0，则说明无论如何都可以环岛一周。
2. 否则，求出diff的前缀和 pres，则最优点必然是  pres中的最小值的位置。【说明这个点出发后面可以先加油的量是最多的】

```python
class Solution:
    def canCompleteCircuit(self, gas: List[int], cost: List[int]) -> int:
        n = len(gas)
        diff = [gas[i] - cost[i]  for i in range(n)]
        if sum(diff) < 0: return -1
        sum_ = list(accumulate(diff,initial=0))
        return sum_.index(min(sum_)) 
```

**第二题**

周末放假了，小疆计划骑着自己的电动车去航拍。小疆打开DiiFly上的去哪拍，找到了多个航拍点。为了规划路线，小疆提前了解了不同的航拍点之间的连通性和距离，同时还确认了每个航拍点充电桩的充电速率。小疆的电动车满电电量可行驶的距离为dis，单位km，该电动车每行驶1km消耗1单位电量，且耗费1min，一共N个航拍点，每个航拍点都有充电桩，使用二维数组paths标识两个航拍点的双向连通性和距离:[[航拍点编号，相邻航拍点编号，两个航拍点的距离]]，示例: [[0,1,3],[0,2,2]...],其中[0,1.3]就表示编号为0的航拍点和编号为1的航拍点相距3km，所有航拍点间的距离都小于dis，数组charge表示在每个航拍点充1单位电的花费的分钟数，示例: [2,10]，2表示编号为0的航拍点充电1单位耗时2分钟，10表示编号为1的航拍点充电1单位耗时10分钟。小疆当前在航拍点a，且电动车无电，请问小疆最少花费多少分钟数从所在的航拍点a抵达航拍点b。

**输入描述**

1、paths: 标识两个航拍点的双向连通性和距离 (先输入航拍点距离的二维数组长度，比如6 3，表示6组数据，每 组3个数值。再跟具体的数组内容) 2、dis: 电动车满电行驶里程 3、a: 航拍点a的编号 4、b: 航拍点b的编号 5、charge: 标识每个航拍点充1单位电的花费的分钟数 (先输入充电的数组长度，比如5。再跟数组内容，比如4 1 1 3

**输出描述**

最少花费的分钟数

首先看题目不难发现是一个最短路问题，但是难点在于 **每个点可以冲若干格电**，那么问题可以转换为：

**每个点不仅可以走到相邻的点，而且可以留在原点充电**

那么对于每个状态我们只需要去枚举他的所有可能即可，当然有几个细节需要注意：

1. 走到相邻的点要满足 **当前剩余电量是足够支撑的**
2. 充电不能超过限制。

==Dijkstra==

```java
import java.util.*;

public class Main {

    int[][] paths;
    int n;
    int dis, a, b;

    int[] times;

    List<List<int[]>> graph = new ArrayList<>();


    void solve(){
        Scanner sc = new Scanner(System.in);
        int y = sc.nextInt();
        int m = sc.nextInt();
        paths = new int[y][m];
        for (int i = 0; i < y; i++) {
            paths[i][0] = sc.nextInt();
            paths[i][1] = sc.nextInt();
            paths[i][2] = sc.nextInt();
        }

        dis = sc.nextInt();
        a = sc.nextInt();
        b = sc.nextInt();

        n = sc.nextInt();
        times = new int[n];
        for (int i = 0; i < n; i++) {
            times[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) graph.add(new LinkedList<>());

        for (int[] path : paths) {
            graph.get(path[0]).add(new int[]{path[1], path[2]});
            graph.get(path[1]).add(new int[]{path[0], path[2]});
        }

        PriorityQueue<int[]> q = new PriorityQueue<>((a,b)->a[0]-b[0]);
        q.add(new int[]{0, a, 0});
        boolean[][] vst = new boolean[n+1][dis+1];

        while (!q.isEmpty()) {
            int[] arr = q.poll();
            int d = arr[0], node = arr[1], pw = arr[2];
            if (node == b) {
                System.out.println(d);
                return;
            }
            if (vst[node][pw]) continue;
            vst[node][pw] = true;
            for (int[] ints : graph.get(node)) {
                int nextnode = ints[0], nextDis = ints[1];
                if (nextDis <= pw && !vst[nextnode][pw - nextDis] ) {
                    q.add(new int[]{d+nextDis, nextnode, pw - nextDis});
                }
            }

            for (int i = 1 ; pw + i <= dis; i++) {
                if (!vst[node][pw + i]) {
                    q.add(new int[]{d+times[node] * i, node, pw+i});
                }
            }

        }

        System.out.println(-1);

    }



    public static void main(String[] args) {
        new Main().solve();
    }


}
```



# 米哈游 8.13

**第一题**一个n*m的矩阵，左下角坐标为（1,1），右上角坐标为（n,m）,主角每次可以移动一个格子，同时该矩阵有一个特性，上边界下边界相邻，左边界右边界相邻，即（x,1）与（x,m）相邻，现在给定主角坐标，角色A坐标，角色B坐标，判断主角最少需要走几步才能先到A再到B

```java
import java.util.Scanner;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int x1 = in.nextInt(), y1 = in.nextInt();
        int x2 = in.nextInt(), y2 = in.nextInt();
        int x3 = in.nextInt(), y3 = in.nextInt();
        System.out.println(f(x1, x2, m) + f(x2, x3, m) + f(y1, y2, n) + f(y2, y3, n));
    }

    public static int f(int i, int j, int len) {
        return Math.min(Math.abs(i - j), Math.min(i, j) + len - Math.max(i, j));
    }
}
```



**第二题** 一颗有根树，根节点编号为1，边长度均为1，每次操作可以将一个新叶子节点接在原来的一个叶子上，新加的边长度也是1，可以进行任意多次操作。求操作结束后，与根节点距离不超过k的节点数最大值是多少。 输入：第一行输入两个整数n和k，接下来n-1行，每行输入两个整数代表这俩个节点之间有一条边； 输出一个整数代表答案

==DFS==

```java
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Test2 {
    static List<List<Integer>> tree = new ArrayList<>();
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        
        for (int i = 0; i < n; i++) {
            tree.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            int x = in.nextInt(), y = in.nextInt();
            tree.get(x).add(y);
        }

        System.out.println(dfs(0, 0, k));
    }

    public static int dfs(int root, int depth, int k) {
        if (depth - k > 0) return 0;
        int ans = 1;
        if (!tree.get(root).isEmpty()) {
            for (Integer child : tree.get(root)) {
                ans += dfs(child, depth + 1, k);
            }
        } else {
            ans += Math.max(0, k - depth);
        }
        return ans;
    }
}
```



**第三题**没触发大保底，p/2概率当期五星，p/2概率常驻五星，1-p概率不是五星

90次小保底，必中五星，一半概率是当期五星

触发小保底歪了后，p概率当期五星，1-p概率不是五星

180次必是当期五星

抽中当期五星的次数期望值是？

```java
import java.util.Scanner;
// 104.5497057
public class Test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double p = in.nextDouble();
        System.out.println(calExpect(p));
    }

    public static double calExpect(double p) {
        double res = 0.0;
        double[] probs = new double[89 * 2 + 2];
        double temp = 1.0;
        double dangqiAfterChangzhu = 0;


        
        for (int i = 1; i <= 89; i++) {
            dangqiAfterChangzhu += p * temp * i;
            temp *= (1 - p);
        }
        dangqiAfterChangzhu += (89 + 1) * temp;

        temp = 1.0;
        for (int i = 1; i <= 89 + 1; i++) {
            if (i <= 89) {
                probs[i] = temp * p / 2;
                res += probs[i] * i + (dangqiAfterChangzhu + i) * temp * p / 2;
                temp *= (1 - p);
                
            } else if (i == 89 + 1){
                probs[i] = temp * 0.5;
                temp *= 0.5;
                res += probs[i] * i;
            } 
        }
        res += (dangqiAfterChangzhu + 89 + 1) * temp;
        return res;
    }
}
```

==动态规划==

https://codefun2000.com/p/471/solution?sid=64d9b192b2a2f87ec9bda2d5

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double p = sc.nextDouble();
        double[] dp = new double[91];
        double cnt = 0;  //记录总期望次数
        for(int i = 1 ; i < 90 ; i ++) {
            dp[i] = (1-dp[i-1]) * p;
            cnt += dp[i] * i;
            dp[i] += dp[i-1];  //前缀和
        }
        cnt += 90 * (1-dp[89]);  //i=90时特殊处理
        System.out.printf("%.7f\n", cnt *1.5);
    }
}
```



# 阿里饿了么 8.17

**第一题**

小红在饿了么上面点了一个外卖，饥肠辘辘的她等骑手等得望眼欲穿。

已知小红在时刻t1点了外卖，饿了么上面显示预计送达时间为t2，实际送达时间为t3。请你判断外卖是否超时？

**输入描述**

第一行输入一个正整数t，代表询问次数。接下来的3*t行，每 3 行代表一次询问：第一行为点外卖的时刻t1，第二行为预计送达时间t2，第三行为实际送达时间t3。

保证骑手送外卖的预期花费时间和实际花费时间均不超过 2 小时。

1<=t<=100

**输出描述**

输出t行。如果超时则输出"Yes"，否则输出"No"。

```java
import java.time.Duration;
import java.util.Scanner;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
     public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for (int i = 0; i < t; i++) {
            LocalTime t1 = LocalTime.parse(sc.nextLine(), formatter);
            LocalTime t2 = LocalTime.parse(sc.nextLine(), formatter);
            LocalTime t3 = LocalTime.parse(sc.nextLine(), formatter);
            if (Math.abs(Duration.between(t2,t3).toHours()) <= 2) {
                if (t3.isAfter(t2)) System.out.println("Yes");
                else System.out.println("No");
            }
            else {
                if (Math.abs(Duration.between(t1,t3).toHours()) >= Math.abs(Duration.between(t1,t2).toHours()))
                    System.out.println("Yes");
                else System.out.println("No");
            }
        }
    }
}
```

**第二题**

小红的字符串构造

小红拿到了一个字符串s，她准备构造一个和t长度相同的字符串t：满足以下条件：

1.t的字符集和s的相同（去重后的，也就是说不考虑数量）

2.t的每个位置的字符都和s不同。

例如若 s="aabbc"，那么t可以构造为"cbaca"。

你能帮帮小红吗？

**输入描述**

输入一个仅由小写字母组成的字符串s，长度不超过 200000。

**输出描述**

如果无解，请输出 -1。否则输出任意合法的字符串。

==哈希表==

```java
import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.nextLine().toCharArray();

        Set<Character> set = new HashSet<>();

        for (char c : s) {
            set.add(c);
        }

        char[] t = new char[s.length];

        int pos = 0;
        Set<Character> usd = new HashSet<>();
        for (int j = 0 ; j < s.length; j++) {

            if (usd.size() != set.size()) {
                for (char c : set) {
                    if (set.contains(c) && !usd.contains(c) && c != s[j]) {
                        t[j] = c;
                        usd.add(c);
                        break;
                    }
                }
            }
            else {
                for (char c : set) {
                    if (set.contains(c) && c != s[j]) {
                        t[j] = c;
                        break;
                    }
                }
            }

        }
        System.out.println(new String(t));
    }
}
```

**第三题**

小红在第三新北林市的学园城送外卖，学园城中有非常多的学校，学园城里有一个美食街。小红每次会接一些同一个学校的订单，然后从美食街取餐出发，再骑车将外卖送到学校，最后回到美食街，以此往复。

学园城有n个结点，m 条道路，美食街为1号结点，剩下的结点都是学校，保证学园城中所有结点连通。给出小红每次要送外卖的学校，请计算小红最少需要骑行的距离。

**输入描述**

第一行输入三个整数n,m,p(1<=n,m,p<=10^5)，分别表示结点数、道路数、送外卖次数。

接下来 m行，每行输入三个整数u,v(1<=u,v<=n,u≠v) ,w(1<=w<=10^4) 表示结点u和v之间有一条长度为w的道路。

最后一行输入q个整数表示每次送外卖的学校a(2<=ai<=n) 。

**输出描述**

输出一个整数表示答案。

==Dijkstra算法==

```java
import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();

        List<List<int[]>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) graph.add(new LinkedList<>());

        for (int i = 0; i < m; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int w = sc.nextInt();
            graph.get(a).add(new int[]{b,w});
            graph.get(b).add(new int[]{a,w});
        }

        int[] Q = new int[q];

        for (int i = 0; i < q; i++) {
            Q[i] = sc.nextInt();
        }

        int[] dis = new int[n+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] vst = new boolean[n+1];
        dis[1] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[0]-b[0]);
        pq.add(new int[]{0, 1});
        while (!pq.isEmpty()) {
            int[] arr = pq.poll();
            int d = arr[0], u = arr[1];
            if (vst[u]) continue;
            vst[u] = true;
            for (int[] A: graph.get(u)) {
                int v = A[0], w = A[1];
                if (dis[v] > dis[u] + w) {
                    dis[v] = dis[u] + w;
                    pq.add(new int[]{dis[v], v});
                }
            }
        }

        long ans = 0;
        for (int i : Q) {
            ans += dis[i] *  2;
        }
        System.out.println(ans);
    }
}
```



# 阿里控股 8.26 

**第一题**

小红中心点，一个点水平方向上左右都存在其他点且垂直方向上上下都存在其他点，则该点为中心点。求中心点个数。

思路一、直接暴力判断

```c++
#include <iostream>
#include <vector>
using namespace std;

int main() {
    int n;
    cin >> n;
    vector<vector<int>> vec(n, vector<int>(2));
    for (int i = 0; i < n; i++) {
        cin >> vec[i][0];
        cin >> vec[i][1];
    }
    int res = 0;
    for (int i = 0; i < n; i++) {
        int tmp = 0;
        for (int j = 0; j < n; j++) {
            if (j != i) {
                if (vec[j][0] == vec[i][0] && vec[j][1] > vec[i][1]) { // 上边
                    tmp = tmp | 1;
                }
                else if (vec[j][0] == vec[i][0] && vec[j][1] < vec[i][1]) { // 下边
                    tmp = tmp | 2;
                }
                else if (vec[j][1] == vec[i][1] && vec[j][0] < vec[i][0]) { // 左边
                    tmp = tmp | 4;
                }
                else if (vec[j][1] == vec[i][1] && vec[j][0] > vec[i][0]) { // 右边
                    tmp = tmp | 8;
                }

                if (tmp == 15) {
                    res++;
                    break;
                }
            }
        }
    }
    cout << res;
}


```

思路二、建两个哈希表，一个哈希表key为x坐标，value为横坐标为key的所有纵坐标list；另一个哈希表key为y坐标，value为纵坐标为key的所有横坐标list。

**第二题**

小红打字

```c++
#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main() {
    int n, k;
    cin >> n >> k;
    string str;
    string keys;
    cin >> str >> keys;
    vector<bool> isHave(26);
    for (auto key : keys) {
        isHave[key - 'a'] = true;
    }
    int res = 0;
    int curLen = 0;
    for (int i = 0; i < str.size(); i++) {
        if (isHave[str[i] - 'a'] == true) {
            curLen++;
        }
        else {
            res += curLen * (curLen + 1) / 2;
            curLen = 0;
        }
    }
    if (curLen > 0) {
        res += curLen * (curLen + 1) / 2;
    }
    cout << res;
}
```

**第三题**

一个字符串只含有a b c字符，如果一个字符串是和谐的，则该字符串不含长度超过1的回文连续子串。字符串的不和谐度定义为将该串变为和谐的字符串需要的最小操作个数（每一次操作可以将一个字符变成另外一个字符）

思路一、暴力解决。和谐子串形式只能是abcabc acbacb bacbac bcabca cabcab cbacba 只能过60%

```c++
#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main() {
    int n, q;
    cin >> n >> q;
    string str;
    cin >> str;
    int l, r;
    char ch;

    vector<string> vec = { "abc", "acb", "bac", "bca", "cab", "cba" };

    for (int i = 0; i < q; i++) {
        cin >> l >> r >> ch;
        for (int j = l - 1; j <= r - 1; j++) {
            str[j] = ch;
        }
        int res = 0x3f3f3f3f;
        for (int j = 0; j < vec.size(); j++) {
            int curRes = 0;
            for (int k = 0; k < n; k++) {
                if (str[k] != vec[j][k % 3]) {
                    curRes++;
                }
            }
            res = min(res, curRes);
        }
        cout << res << "\n";
    }
    return 0;
}
```



# 美团笔试 8.26（本人未参加）

**第一题**

小美种果树

小美在手机上种果树，只要成熟了就可以领到免费的水果了。

小美每天可以给果树浇水，果树的成长值加x。同时也可以给果树施肥，两次施肥至少需要间隔 2 天，果树的成长值加 y。果树成长值达到z就成熟了。

小红想知道，最少需要多少天可以领到免费的水果。

**输入描述**

一行三个整数x,y,z，分别表示浇水的成长值，施肥的成长值，果树成熟的成长值。1<=x,y,z<=10^9

**输出描述**

一行一个整数，表示最少需要多少天可以领到免费的水果。

直接求解即可。也可以考虑用二分答案方法。

```python
x,y,z = map(int, input().split())

yu = z % (3*x + y)
if yu == 0:
    print(z //(3*x + y) * 3)
else:
    if yu < x + y:
        print(z //(3*x + y) * 3 + 1)
    elif yu < 2*x + y:
        print(z // (3 * x + y) * 3 + 2)
    else:
        print(z // (3 * x + y) * 3 + 3)
```

**第二题**

小美结账

大家一起吃饭的时候，总是小红先付钱，然后大家再把钱转给小红。

现在小红有n张账单，每张账单记录了有k个人一起吃饭，以及吃饭的消费c，现在小红需要计算每个人需要转给小红多少钱。

由于大家都比较喜欢整数，所以大家每张账单会转给小红[c/k],[x]表示对x进行上取整。

直接求解即可。

```python
import math
n,m = map(int, input().split())

res = [0]*(m + 1)

for _ in range(n):
    k,c = map(int, input().split())
    people = [int(c) for c in input().split()]

    for p in people:
        res[p] += math.ceil(c/k)

for i in range(1, m+1):
    print(res[i], end=" ")
```

**第三题**

小美的游戏

==堆排序==

```python
import heapq

n,k = map(int, input().split())
a = [int(c) for c in input().split()]
h = []
for i in a:
    heapq.heappush(h, -i)
mod = (10**9) + 7
sum_ = sum(a) % mod
for _ in range(k):
    x,y = -heapq.heappop(h), -heapq.heappop(h)
    sum_ -= (x + y)
    heapq.heappush(h, -x*y)
    heapq.heappush(h, -1)
    sum_ += x*y + 1
    sum_ %= mod

print(sum_)
```

**第四题**

小美的数组重排

小美有两个长度为n的数组a和b。

小美想知道，能不能通过重排a数组使得对于任意1<=i<=n,1<=ai+bi<=m？

将会有q次询问。

输入描述

第一行一个整数q(1<=q<=30)。表示询问次数。

对于每一个询问：

第一行输入两个整数n,m(1<=n,m<=500)

第二行输入n个整数ai(-500<=ai<=500)。

第三行输入n个整数bi(-500<=bi<=500)。

**输出描述**

q行，每行输出一个字符串，如果能通过重排满足条件则输出"Yes"(不含引号),否则输出"No"。

思维题

要想满足题目的要求，对于数组a的最小值，如果跟数组b的最大值进行合并，假设结果不满足，那就肯定是不可能的了，否则可以考虑次小值和次大值进行分配，以此类推，只需要将a数组正向排序，b数组逆向排序即可。

```python

q = int(input())

def func(n, m, a, b):
    for i in range(n):
        if 1 <= a[i] + b[i] <= m: continue
        else: return False
    return True

for _ in range(q):
    n, m = map(int, input().split())
    a = [int(c) for c in input().split()]
    b = [int(c) for c in input().split()]

    a.sort()
    b.sort(reverse=True)

    if func(n, m, a, b): print("Yes")
    else: print("No")
```

**第五题**

平均数为k的最长连续子数组

给定n个正整数组成的数组，求平均数正好等于k 的最长连续子数组的长度。

**输入描述**

第一行输入两个正整数n和k，用空格隔开。

第二行输入n个正整数ai，用来表示数组。

1<=n<=200000

1<=k,ai<=10^9

**输出描述**

如果不存在任何一个连续子数组的平均数等于k，则输出-1。

否则输出平均数正好等于k的最长连续子数组的长度。

==前缀和 哈希表==

思维题+哈希表模拟。

平均数比较难处理，我们不妨将原数组中每个元素都-k，这样问题转换成找到和为0的最长子数组。

```python
n,k = map(int, input().split())
a = [int(c) - k for c in input().split()]

res = 0
occ = {}
occ[0] = -1

tmp = 0

for i in range(n):
    tmp += a[i]
    if tmp in occ:
        res = max(res, i - occ[tmp])
    if tmp not in occ: occ[tmp] = i

print(res)
```



# 阿里国际 8.28

**第一题**

给定一个只包含小写字母的字符串，可以删除特定字符，使得偶数位置的字符等于前一个位置的字符，求保留的最大长度。例如abacc，可以得到aacc，最大长度为4。

可以用哈希表和集合思想来做。

以按照段划分，abcedfa | xyznpx |   最后答案就是 n - (2 * x)，n = len(str), x表示有多少对(类似aa/bb)这样的形式，最后想到了，用一个set双指针维护下过了

**第二题**

二分查找

**第三题**

一个有向图，其无向图构成环，可以将边的方向进行反转，其操作量为权值大小，求解让有向图构成一个环的最小操作数。

正向遍历有向图以及反向遍历有向图，求两者最小值即可。

# 得物 8.29

**第一题**

给定字符串，求解该字符串中长度为k的连续字符串为回文串的个数。

暴力即可。

**第二题**

一排街道需要建造房子，每个房子高度最小为1，且相邻两个房子的高度差不超过1，在给定最大房子高度和的前提下，求解所有房子中可能的最大高度是多少。

二分答案即可。

```java
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), x = in.nextInt();
        System.out.println(findMaxBuilding(n, m, x));   
    }

    public static int findMaxBuilding(int n, int m, int x) {
        int left = 1, right = m - n + 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (check(mid, n, m, x)) right--;
            else left++;
        }
        return right;
    }
    //private static boolean check(long h, int n, int m, int x) 
    private static boolean check(long h, long n, long m, long x) {
        long end1 = h - (x - 1), end2 = h - (n - x);
        long res = 0;
        if (end1 >= 1) {
            res += (h + end1)  * x / 2;
        } else {
            res += (h + 1)  * h / 2 + (x - h);
        }

        if (end2 >= 1) {
            res += (h + end2) * (n - x + 1) / 2;
        } else {
            res += (h + 1)  * h / 2 + (n - x + 1 - h);
        }

        res -= h;
        return (int)res > m;
    }
}
```



# 阿里淘天1688 8.29

**第一题**

给定相同长度的数组a和b，对于i < j < k, a[i] < a[j] <a[k]，求b[i] + b[j] +b[k]的最大值

无脑暴力即可，但有些用例会超时，此时可以做些优化，可以先保留b数组中的最大值，如果b[i]+b[j] +max(b)小于之前求解的最大值，那么直接跳过下一次循环就行。

**第二题**

忘了，记得用动态规划就行

**第三题**

树打挂，挂有树形，水或火，水和火直接相连可以打掉，求最大消灭的挂个数。

树形DP和贪心，和美团笔试贪心题类似，也和打家劫舍III类似。

# 华为机试 8.30（本人未参加）

**第二题**

在无线通信设备中通常使用超外差接收机，超外差接收机是利用本地产生的振荡波与输入信号混频，将输入信号频率变换为某个预先确定的频率的方法。也就是说，信号通过一个混频器后，频率就会搬移一个数值。在项目中由于要节省器件，混频器需要尽可能的共享，我们设计了二又树型的混频器组，可以同时把信号搬移到不同的频率上。

二叉树为完全二叉树。我们给定二叉树的层数和从根节点开始到每个子节点的频率搬移总和，输出二叉树。

规则：节点的值为它的所有叶子结点的目标频率最大最小值的平均值（非整数向下取整）减去它所有父节点的总和。

==DFS 二叉树遍历==

```java
n = int(input())

leaves = [int(c) for c in input().split()]

tree = [0] * (n*2)

vals = [0] * (n*2)

target = {}

def dfs(node):
    if node >= n - 1:
        target[node] = [leaves[node-n+1],leaves[node-n+1]]
        return target[node]

    l,r = dfs(2*node + 1), dfs(2*node + 2)
    target[node] = [max(l+r), min(l+r)]
    return target[node]

dfs(0)

def dfs2(node, sum):
    if node >= 2 * n - 1: return
    vals[node] = (target[node][0] + target[node][1]) // 2 - sum
    dfs2(node*2+1, sum + vals[node])
    dfs2(node*2+2, sum+ vals[node])

dfs2(0, 0)


for i in range(2*n-1):
    print(vals[i], end=" ")
```

**第三题**

系统由n个任务组成，任务运行有依赖关系，前序任务执行完毕才可以启动后续任务。任务在启动前申请内存，执行完毕后释放，内存释放后可用于其他任务使用。每个任务的运行时间相等。请计算系统所有任务执行所需要的最小内存。

类似于leetcode207课程表

==拓扑排序==

```python
from collections import deque, defaultdict

n = int(input())
mem = [int(c) for c in input().split()]

graph = defaultdict(list)
indegre = [0]*n

for i in range(n):
    tmp = [int(c) for c in input().split()]
    for j, s in enumerate(tmp):
        if s == 1:
            graph[j].append(i)
            indegre[i] += 1


q = deque()

for i in range(n):
    if indegre[i] == 0:
        q.append(i)

res = 0

while q:
    size = len(q)
    cur = 0
    for _ in range(size):
        node = q.popleft()
        cur += mem[node]
        for next in graph[node]:
            indegre[next] -= 1
            if indegre[next] == 0:
                q.append(next)
    res = max(res, cur)

print(res)
```

# 微众笔试 9.3

**第一题**

切糖果，从头开始切，切的部分不含重复元素，求最大长度。(注意从头开始切，不是从中间切)

==哈希表==

```java
import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] taste = new int[n];
        for (int i = 0; i < n; i++) taste[i] = in.nextInt();
        System.out.println(getRes(taste));
    }

    public static int getRes(int[] taste) {
        Set<Integer> visit = new HashSet<>();
        for (int i = 0; i < taste.length; i++) {
            if (visit.contains(taste[i])) return i;
            visit.add(taste[i]);
        }
        return taste.length;
    }
}
```

**第二题**

输入一个n，代表数组长度
接下来输入一个数组nums，然后你可以任意给数组元素加值，加多少都行
问：最少加多少（总和），能够让数组没有重复元素

==哈希表==

```java
import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n;  i++) a[i] = in.nextInt();
        System.out.println(getRes(a));
    }

    public static int getRes(int[] a) {
        int res = 0, left = 0;
        Arrays.sort(a);
        Set<Integer> set = new HashSet<>();
        for (int num : a) set.add(num);
        if (a[a.length - 1] - a[0] + 1 <= a.length) left = a[a.length - 1];
        for (int i = 1; i < a.length; i++) {
            if (a[i] == a[i - 1]) {
                left = Math.max(a[i], left) + 1;
                while (set.contains(left)) {
                    left++;
                }
                res += left - a[i];
                set.add(left);
            }
        }
        return res;
    }
}
```

==贪心==

先排序

然后从第2个元素开始遍历到最后，只要这个元素小于等于上一个元素，就让它等于上一个元素+1（贪心），然后res+=(更新值减去原值）

==其他==

```c++
#include <bits/stdc++.h>

using namespace std;


int minn(vector<int>& nums) {
    sort(nums.begin(), nums.end());
    int ans = 0, taken = 0;
    for (int i = 1; i < (nums.size(); ++i) {
        if (nums[i - 1] == nums[i]) {
            taken++;
                ans -= nums[i];
        }
        else {
            int re = min(taken, nums[i] - nums[i - 1] - 1);
                ans += re * (re + 1) / 2 + re * nums[i - 1];
                taken -= re;
        }
    }

    if (nums.size() > 0) {
        ans += taken * (taken + 1) / 2 + taken * nums.back();
    }

    return ans;
}

int main()
{
 int n;
    scanf("%d", &n);
    vector<int> arr(n);
    for (int i = 0; i < n; i++) {
        scanf("%d", &arr[i]);
    }
    printf("%d", minn(arr));
}
```



**第三题**

u v的最大公约数为1，给定长度为n的数组nums，求nums中满足平均值为u/v的连续子数组的个数

==暴力遍历==

```java
import java.util.*;

public class Test3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), u = in.nextInt(), v = in.nextInt();
        int[] nums = new int[n];
        long sum = 0, res = 0;
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();
        for (int len = 1; len <= n; len++) {
            if (len % v != 0) continue;
            sum = len / v * u;
            long temp = 0;
            for (int i = 0; i < len; i++) temp += nums[i];
            if (temp == sum) res++;
            for (int i = len; i < n; i++) {
                temp += nums[i] - nums[i - len];
                if (temp == sum) res++;
            }
        }
        System.out.println(res);
    }
}

```

在求给定长度满足条件的子数组个数时，可以用前缀和的方式

==前缀和==

```c++
#include<bits/stdc++.h>

using namespace std;
typedef long long ll;
map<int,int> cnt;
int n,u,v;
void solve(vector<int> arr){
    cnt[u]=1;
    int res=0,s=0;
    for(int i=0;i<n;i++){
        s+=arr[i];
        res+=cnt[v*s-u*i]++;
    }
    cout<<res<<endl;
}

int main(){
    cin>>n>>u>>v;
    vector<int> a(n);
    for(int &t:a) cin>>t;
    solve(a);
}
```

# 招商 9.4

驾校教练可分配时间是5-19点，有n个同学预约时间，包括开始时间和结束时间，求解驾校教练最多能给驾校同学安排训练时间

==贪心 排序==

```java
import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int start = in.nextInt(), end = in.nextInt();
            map.computeIfAbsent(start, key -> new ArrayList<>()).add(end);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            Collections.sort(entry.getValue());
        }
        
        int res = 0, end = 19, start = 5;
        while (start <= end) {
            if (!map.containsKey(start)) {
                start++;
                continue;
            }
            end = Math.min(end, map.get(start).get(0));
            int temp = 0, i = start;
            while (i <= 19) {
                if (!map.containsKey(i)) {
                    i++;
                    continue;
                }
                temp++;
                i = map.get(i).get(0);
            }
            res = Math.max(res, temp);
            start++;
        }
        System.out.println(res);
    }
}
```

# 腾讯笔试 9.15

**第四题**

给定查询次数T和每次查询的字符串数组的长度n，判断字符串数组中两个字符串是否存在匹配的选择字符串。

==集合==

```java
import java.util.*;

public class Test4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            in.nextLine();
            String[] strs = new String[n];
            for (int j = 0; j < n; j++) 
                strs[j] = in.nextLine();
            System.out.println(getRes(strs) ? "YES" : "NO");
        }
    }

    public static boolean getRes(String[] strs) {
        if (strs.length == 0) return false;
        Set<String> set = new HashSet<>();
        for (String str : strs) {
            Set<String> tmpSet = new HashSet<>();
            for (int i = 1; i <= str.length(); i++) {
                String tmp = str.substring(i, str.length()) + str.substring(0, i);
                if (set.contains(tmp)) return true;
                tmpSet.add(tmp);
            }
            set.addAll(tmpSet);
        }
        return false;
    }
}

```

**第五题**

从左至右有n个点，n个点在不同的位置上且1表示蓝点、0表示红点，可以选择一下操作。

操作1、选择红点，对其进行加一或者减一；
操作2、选择蓝点，将其变为红点。

给定k，最多进行操作2。问最少进行多少个操作1让任意红点之间不包括蓝点。



我们先不考虑蓝变红的情况，那么所有蓝点都不动。这样要让红点指间无蓝点的话必须把红点都挪到一个区间里，区间左右端点必须是蓝点或者正负无穷，且区间内没有蓝点。解这个简化的问题直接枚举每一个区间即可，用前缀和优化一下不然会tle。那么我们接着考虑k次蓝变红的操作，与前面不同的是，这里的区间中可以包含k个蓝点，因为我们可以把这k个都变成红，所以你只需要以k为间隔枚举区间即可。

# Acwing 165 小猫爬山

==DFS 回溯 剪枝==

```java
import java.util.*;

public class Main {
    static int n, maxW;
    static Integer[] w;
    static int[] sum;
    static int ans;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        maxW = sc.nextInt();
        w = new Integer[n];
        sum = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        Arrays.sort(w, (n1, n2) -> (n2 - n1));
        ans = n;
        dfs(0, 0);
        System.out.println(ans);
    }
    
    public static void dfs(int m, int k) {
        if (k >= ans) return ;
        if (m == n) {
            ans = k;
            return;
        }
        for (int i = 0; i < k; i++) {
            if (sum[i] + w[m] <= maxW) {
                sum[i] += w[m];
                dfs(m + 1, k);
                sum[i] -= w[m];
            }
        }
        sum[k] = w[m];
        dfs(m + 1, k + 1);
        sum[k] = 0;
    }
}
```

==贪心不行==

反例：
请尝试在maxV=16时，重量为9 5 5 5 4 3
贪心结果是9+5 5+5+4 3，结果为3
但正确结果为9+4+3 5+5+5，结果为2
举出反例，则贪心错误

```java
import java.util.*;

public class Main {
    static int n, maxW;
    static Integer[] w;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        maxW = sc.nextInt();
        w = new Integer[n];
        for (int i = 0; i < n; i++) {
            w[i] = sc.nextInt();
        }
        
        System.out.println(findMin());
    }
    
    public static int findMin() {
        Arrays.sort(w, (n1, n2) -> (n2 - n1));
        int[] rev = new int[n];
        Arrays.fill(rev, maxW);
        int ans = -1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (rev[j] >= w[i]) {
                    rev[j] -= w[i];
                    ans = Math.max(ans, j + 1);
                    break;
                }
            }
        }
        return ans;
    }
}
```

# 华为笔试9.20

**第二题*

==BFS==

```java
import java.util.*;

public class Test2 {
    static boolean[][] used;
    static int res = Integer.MAX_VALUE;
    static int temp = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) 
                nums[i][j] = in.nextInt();
        
        for (int i = 0; i < m; i++) {
            temp = 0;
            used = new boolean[m][n];
            if (nums[i][0] == 1) bfs(nums, i, 0);
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void bfs(int[][] nums, int row, int column) {
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        Deque<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, row, column});
        used[row][column] = true;
        while (!queue.isEmpty()) {
            int[] a = queue.poll();
            int d = a[0], x = a[1], y = a[2];
            if (y == nums[0].length - 1) res = Math.min(res, d);
            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1];
                if (!check(nums, nx, ny)) continue;
                used[nx][ny] = true;
                queue.offer(new int[]{d + 1, nx, ny});
            }
        }
    }

    private static boolean check(int[][] nums, int row, int column) {
        if (row < 0 || column < 0 || row == nums.length || column == nums[0].length || used[row][column] || nums[row][column] == 0 ) return false;
        return true;
    }
}

```

==DFS 记忆化==

```java
import java.util.*;

public class Test2_1 {
    static int[][] visited;
    static int res = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), n = in.nextInt();
        int[][] nums = new int[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) 
                nums[i][j] = in.nextInt();
        
        visited = new int[m][n];
        for (int i = 0; i < m; i++) {
            visited[i][0] = dfs(nums, i, 0, 0);
        }
        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static int  dfs(int[][] nums, int row, int column, int temp) {
        if (!check(nums, row, column)) return Integer.MAX_VALUE;
        if (visited[row][column] != 0) return visited[row][column];
        if (column == nums[0].length - 1) {
            res = Math.min(res, temp);
            return temp;
        }
        int ans = Integer.MAX_VALUE;
        nums[row][column] = 0;
        ans = Math.min(ans, dfs(nums, row - 1, column, temp + 1));
        ans = Math.min(ans, dfs(nums, row + 1, column, temp + 1));
        ans = Math.min(ans, dfs(nums, row, column - 1, temp + 1));
        ans = Math.min(ans, dfs(nums, row, column + 1, temp + 1));
        nums[row][column] = 1;
        return ans;
    }

    private static boolean check(int[][] nums, int row, int column) {
        if (row < 0 || column < 0 || row == nums.length || column == nums[0].length || nums[row][column] == 0) return false;
        return true;
    }
}

```



# 背包问题

https://oi-wiki.org/dp/knapsack/#%E5%88%86%E7%BB%84%E8%83%8C%E5%8C%85
https://programmercarl.com/%E8%83%8C%E5%8C%85%E7%90%86%E8%AE%BA%E5%9F%BA%E7%A1%8001%E8%83%8C%E5%8C%85-1.html#%E7%AE%97%E6%B3%95%E5%85%AC%E5%BC%80%E8%AF%BE

## 01背包

有n件物品和一个最多能背重量为w 的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。**每件物品只能用一次**，求解将哪些物品装入背包里物品价值总和最大。

==动态规划==

```java
public class BagProblem {
    public static void main(String[] args) {
        int[] weight = {1,3,4};
        int[] value = {15,20,30};
        int bagSize = 4;
        testWeightBagProblem(weight,value,bagSize);
    }

    /**
     * 动态规划获得结果
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){

        // 创建dp数组
        int goods = weight.length;  // 获取物品的数量
        int[][] dp = new int[goods][bagSize + 1];

        // 初始化dp数组
        // 创建数组后，其中默认的值就是0
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        // 填充dp数组
        for (int i = 1; i < weight.length; i++) {
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    /**
                     * 当前背包的容量都没有当前物品i大的时候，是不放物品i的
                     * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                     */
                    dp[i][j] = dp[i-1][j];
                } else {
                    /**
                     * 当前背包的容量可以放下物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i
                     *    2、放物品i
                     * 比较这两种情况下，哪种背包中物品的最大价值最大
                     */
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }

        // 打印dp数组
        for (int i = 0; i < goods; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }
}
```

==动态规划 滚动数组==

```java
public static void main(String[] args) {
        int[] weight = {1, 3, 4};
        int[] value = {15, 20, 30};
        int bagWight = 4;
        testWeightBagProblem(weight, value, bagWight);
    }

    public static void testWeightBagProblem(int[] weight, int[] value, int bagWeight){
        int wLen = weight.length;
        //定义dp数组：dp[j]表示背包容量为j时，能获得的最大价值
        int[] dp = new int[bagWeight + 1];
        //遍历顺序：先遍历物品，再遍历背包容量
        for (int i = 0; i < wLen; i++){
            for (int j = bagWeight; j >= weight[i]; j--){
                dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
            }
        }
        //打印dp数组
        for (int j = 0; j <= bagWeight; j++){
            System.out.print(dp[j] + " ");
        }
    }
```

## 完全背包

有N件物品和一个最多能背重量为W的背包。第i件物品的重量是weight[i]，得到的价值是value[i] 。**每件物品都有无限个（也就是可以放入背包多次）**，求解将哪些物品装入背包里物品价值总和最大。

**完全背包和01背包问题唯一不同的地方就是，每种物品有无限件**。

==动态规划 01背包==

```java
//先遍历物品，再遍历背包
private static void testCompletePack(){
    int[] weight = {1, 3, 4};
    int[] value = {15, 20, 30};
    int bagWeight = 4;
    int[] dp = new int[bagWeight + 1];
    for (int i = 0; i < weight.length; i++){ // 遍历物品
        for (int j = weight[i]; j <= bagWeight; j++){ // 遍历背包容量
            dp[j] = Math.max(dp[j], dp[j - weight[i]] + value[i]);
        }
    }
    for (int maxValue : dp){
        System.out.println(maxValue + "   ");
    }
}

//先遍历背包，再遍历物品
private static void testCompletePackAnotherWay(){
    int[] weight = {1, 3, 4};
    int[] value = {15, 20, 30};
    int bagWeight = 4;
    int[] dp = new int[bagWeight + 1];
    for (int i = 1; i <= bagWeight; i++){ // 遍历背包容量
        for (int j = 0; j < weight.length; j++){ // 遍历物品
            if (i - weight[j] >= 0){
                dp[i] = Math.max(dp[i], dp[i - weight[j]] + value[j]);
            }
        }
    }
    for (int maxValue : dp){
        System.out.println(maxValue + "   ");
    }
}
```

## 多重背包

有N种物品和一个容量为V 的背包。第i种物品最多有Mi件可用，每件耗费的空间是Ci ，价值是Wi 。求解将哪些物品装入背包可使这些物品的耗费的空间 总和不超过背包容量，且价值总和最大。

==动态规划 01背包==

```java
public void testMultiPack1(){
    // 版本一：改变物品数量为01背包格式
    List<Integer> weight = new ArrayList<>(Arrays.asList(1, 3, 4));
    List<Integer> value = new ArrayList<>(Arrays.asList(15, 20, 30));
    List<Integer> nums = new ArrayList<>(Arrays.asList(2, 3, 2));
    int bagWeight = 10;

    for (int i = 0; i < nums.size(); i++) {
        while (nums.get(i) > 1) { // 把物品展开为i
            weight.add(weight.get(i));
            value.add(value.get(i));
            nums.set(i, nums.get(i) - 1);
        }
    }

    int[] dp = new int[bagWeight + 1];
    for(int i = 0; i < weight.size(); i++) { // 遍历物品
        for(int j = bagWeight; j >= weight.get(i); j--) { // 遍历背包容量
            dp[j] = Math.max(dp[j], dp[j - weight.get(i)] + value.get(i));
        }
        System.out.println(Arrays.toString(dp));
    }
}

public void testMultiPack2(){
    // 版本二：改变遍历个数
    int[] weight = new int[] {1, 3, 4};
    int[] value = new int[] {15, 20, 30};
    int[] nums = new int[] {2, 3, 2};
    int bagWeight = 10;

    int[] dp = new int[bagWeight + 1];
    for(int i = 0; i < weight.length; i++) { // 遍历物品
        for(int j = bagWeight; j >= weight[i]; j--) { // 遍历背包容量
            // 以上为01背包，然后加一个遍历个数
            for (int k = 1; k <= nums[i] && (j - k * weight[i]) >= 0; k++) { // 遍历个数
                dp[j] = Math.max(dp[j], dp[j - k * weight[i]] + k * value[i]);
            }
            System.out.println(Arrays.toString(dp));
        }
    }
}
```

## 分组背包

[2218. 从栈中取出 K 个硬币的最大面值和](https://leetcode.cn/problems/maximum-value-of-k-coins-from-piles/)

一张桌子上总共有 `n` 个硬币 **栈** 。每个栈有 **正整数** 个带面值的硬币。

每一次操作中，你可以从任意一个栈的 **顶部** 取出 1 个硬币，从栈中移除它，并放入你的钱包里。

给你一个列表 `piles` ，其中 `piles[i]` 是一个整数数组，分别表示第 `i` 个栈里 **从顶到底** 的硬币面值。同时给你一个正整数 `k` ，请你返回在 **恰好** 进行 `k` 次操作的前提下，你钱包里硬币面值之和 **最大为多少** 。

==动态规划 01背包==

```java
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        /*
        dp五部曲(转化为完全背包问题):
        这里的每一个栈都可以看成是一个分组,例如栈1:pile=[1,2,3],preSum=[1,3,6]
        也就是说组别1内有3件物品,其体积分别为:1,2,3;价值分别为1,3,6
        而每组中的物品只能取出一件,在背包容量有限的情况下最终目标要使得物品价值最大化!
        1.状态定义:dp[i][j]为考虑[0,i]组,背包容量为j时,能获取物品价值的最大值
        2.状态转移:
            参考分组背包的技巧,每次抉择范围为选第i组的哪个物品?其中包含一种特殊情况(m=0代表不选->dp[i-1][j])
            dp[i][j]=max(dp[i][j],dp[i-1][j-v[i][m]]+w[i][m]),m∈[0,pile.size()]
            这里的v[i][m]指第i组内的第k个物品的体积;w[i][m]指第i组内的第m个物品的价值
            通过3层for循环滚动求出dp[i][j]的正确转移值,就是第i组要选择的物品
        3.初始化:
            3.1 dp[i][0]=0->容量为0的情况
            3.2 dp[0][j]=preSum(piles[0][j])->只考虑第0组物品
        4.遍历顺序:显然是正序遍历
        5.返回形式:返回dp[pilesNum-1][k]
        时间复杂度:O(N*K*M),空间复杂度:O(N*K)
        */
        // 组数
        int pilesNum = piles.size();
        int[][] dp = new int[pilesNum][k + 1];
        // 初始化
        for(int j = 1; j <= piles.get(0).size() && j <= k; j++) {
            dp[0][j] = dp[0][j - 1] + piles.get(0).get(j - 1);
        }
        // 遍历每一组物品i∈[1,pilesNum-1]
        for(int i = 1; i <= pilesNum - 1; i++) {
            // 遍历每种背包容量j∈[1,k]
            for(int j = 1; j <= k; j++) {
                int sum = 0;
                // 遍历第i组的每件物品m∈[0,piles.get(i).size()]
                // 其中m=0代表不选第i组任何物品,此时sum=0,自动继承dp[i-1][j]
                // m=piles.get(i).size()表示这个组别的数字全拿了
                for(int m = 0; m <= piles.get(i).size(); m++) {
                    // 计算出第k组物品的第m个物品的价值
                    // 从m=1开始计算,因为m=0相当于不选第i组任何物品,sum=0,dp[i][j]继承自dp[i-1][j]
                    if(m >= 1) sum += piles.get(i).get(m - 1);
                    // 若当前背包容量还装得下第k组物品的第m个物品(体积为m)->滚动获取最大值
                    // 拿之前的or拿现在的->取大的值
                    if(j >= m) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - m] + sum);  
                }
            }
        }
        // 最后返回的是:考虑[0,pilesNum-1]中,背包容量为k的价值最大值(和的最大值)
        return dp[pilesNum - 1][k];
    }
}
```

==动态规划 01背包 滚动数组==

```java
class Solution {
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        var f = new int[k + 1];
        var sumN = 0;
        for (var pile : piles) {
            var n = pile.size();
            for (var i = 1; i < n; ++i)
                pile.set(i, pile.get(i) + pile.get(i - 1)); // pile 前缀和
            sumN = Math.min(sumN + n, k); // 优化：j 从前 i 个栈的大小之和开始枚举（不超过 k）
            for (var j = sumN; j > 0; --j)
                for (var w = 0; w < Math.min(n, j); ++w)
                    f[j] = Math.max(f[j], f[j - w - 1] + pile.get(w)); // w 从 0 开始，物品体积为 w+1
        }
        return f[k];
    }
}
```

# 回溯算法

需要去重 可以考虑排序比较nums[i] nums[i - 1]或者哈希表(适用于不方便使用排序的场景)
全排列 可以考虑交换或者used数组（used数组一般针对是每次backtrack中都是从头开始搜索的情况）

## 组合问题

### Leecode[77. 组合](https://leetcode.cn/problems/combinations/)

==回溯==

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, 1, k);
        return res;
    }

    private void backtrack(int n, int startIndex, int k) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return ;
        }

        for (int i = startIndex; i <= n; i++) {
            path.add(i);
            backtrack(n, i + 1, k);
            path.remove(path.size() - 1);
        }
    }
}
```

==回溯 剪枝==

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, 1, k);
        return res;
    }

    private void backtrack(int n, int startIndex, int k) {
        if (path.size() == k) {
            res.add(new ArrayList<>(path));
            return ;
        }

        for (int i = startIndex; i <= n + 1 - (k - path.size()); i++) {
            path.add(i);
            backtrack(n, i + 1, k);
            path.remove(path.size() - 1);
        }
    }
}
```

### Leetcode[216. 组合总和 III](https://leetcode.cn/problems/combination-sum-iii/)

==回溯==

```java
class Solution {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();
    private int sum;

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(k, 1, n);
        return res;
    }

    private void backtrack(int k, int startIndex, int n) {
        
        if (path.size() == k) {
            if (sum == n) res.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = startIndex; i <= 9 - (k - path.size()) + 1; i++) {
            if (sum + i > n) break;
            sum += i;
            path.add(i);
            backtrack(k, i + 1, n);
            path.remove(path.size() - 1);
            sum -= i;
        }
    }
}
```

### Leetcode[17. 电话号码的字母组合](https://leetcode.cn/problems/letter-combinations-of-a-phone-number/)

==回溯==

```java
class Solution {
    List<String> res = new ArrayList<>();
    StringBuilder sb = new StringBuilder();
    String[] strs = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        if (digits.length() == 0) {
            return res;
        }
        backtrack(digits, 0);
        return res;
    }

    private void backtrack(String digits, int startIndex) {
        if (startIndex == digits.length()) {
            res.add(sb.toString());
            return;
        }
        for (char c : strs[digits.charAt(startIndex) - '2'].toCharArray()) {
            sb.append(c);
            backtrack(digits, startIndex + 1);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
```

### Leetcode[39. 组合总和](https://leetcode.cn/problems/combination-sum/)

==回溯 剪枝 排序==

```java
class Solution {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();
    private int sum;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target);
        return res;
    }

    private void backtrack(int[] candidates, int startIndex, int target) {
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;
            sum += candidates[i];
            path.add(candidates[i]);
            backtrack(candidates, i, target);
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
}
```

### Leetcode[40. 组合总和 II](https://leetcode.cn/problems/combination-sum-ii/)

==回溯 去重 排序==

```java
class Solution {
    private List<Integer> path = new ArrayList<>();
    private List<List<Integer>> res = new ArrayList<>();
    private int sum;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target);
        return res;
    }

    private void backtrack(int[] candidates, int startIndex, int target) {
        
        if (sum == target) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for (int i = startIndex; i < candidates.length; i++) {
            if (sum + candidates[i] > target) break;
            if (i > startIndex && candidates[i] == candidates[i - 1]) continue;
            sum += candidates[i];
            path.add(candidates[i]);
            backtrack(candidates, i + 1, target);
            path.remove(path.size() - 1);
            sum -= candidates[i];
        }
    }
}
```

## 分割问题

### Leetcode[131. 分割回文串](https://leetcode.cn/problems/palindrome-partitioning/)

==回溯==

```java
class Solution {
    private List<List<String>> res = new ArrayList<>();
    private List<String> path = new ArrayList<>();

    public List<List<String>> partition(String s) {
        backtrack(s, 0);
        return res;
    }

    private void backtrack(String s, int startIndex) {
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrom(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
            } else {
                continue;
            }
            backtrack(s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrom(String s, int start, int end) {
        int i = start, j = end;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
```

==回溯 动态规划求解回文串==

```java
class Solution {
    private List<List<String>> res = new ArrayList<>();
    private List<String> path = new ArrayList<>();
    private boolean[][] status;

    public List<List<String>> partition(String s) {
        status = new boolean[s.length()][s.length()];
        computePalindrom(s);
        backtrack(s, 0);
        return res;
    }

    private void backtrack(String s, int startIndex) {
        if (startIndex == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = startIndex; i < s.length(); i++) {
            if (isPalindrom(s, startIndex, i)) {
                path.add(s.substring(startIndex, i + 1));
            } else {
                continue;
            }
            backtrack(s, i + 1);
            path.remove(path.size() - 1);
        }
    }

    private boolean isPalindrom(String s, int start, int end) {
        return status[start][end];
    }

    private void computePalindrom(String s) {
        for (int i = s.length() - 1; i >= 0; i--)
            for (int j = i; j < s.length(); j++) {
                if (j - i <= 1) status[i][j] = s.charAt(i) == s.charAt(j);
                else status[i][j] = s.charAt(i) == s.charAt(j) && status[i + 1][j - 1];
            }
    }
}
```

### Leetcode[93. 复原 IP 地址](https://leetcode.cn/problems/restore-ip-addresses/)

==回溯==

```java
class Solution {
    private List<String> res = new ArrayList<>();
    private List<String> path = new ArrayList<>();

    public List<String> restoreIpAddresses(String s) {
        backtrack(s, 0);
        return res;
    }

    private void backtrack(String s, int startIndex) {
        if (path.size() == 4) {
            if (startIndex != s.length()) return;
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 3; i++) {
                sb.append(path.get(i));
                sb.append('.');
            }
            sb.append(path.get(3));
            res.add(sb.toString());
            return;
        }

        for (int i = startIndex; i < s.length() && i < startIndex + 3; i++) {
            String tmp = s.substring(startIndex, i + 1);
            int value = Integer.valueOf(tmp);
            if (value <= 255 && tmp.equals(String.valueOf(value))) {
                path.add(tmp);
            } else {
                continue;
            }
            backtrack(s, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```

```java
//方法一：但使用stringBuilder，故优化时间、空间复杂度，因为向字符串插入字符时无需复制整个字符串，从而减少了操作的时间复杂度，也不用开新空间存subString，从而减少了空间复杂度。
class Solution {
    List<String> result = new ArrayList<>();
    public List<String> restoreIpAddresses(String s) {
        StringBuilder sb = new StringBuilder(s);
        backTracking(sb, 0, 0);
        return result;
    }
    private void backTracking(StringBuilder s, int startIndex, int dotCount){
        if(dotCount == 3){
            if(isValid(s, startIndex, s.length() - 1)){
                result.add(s.toString());
            }
            return;
        }
        for(int i = startIndex; i < s.length(); i++){
            if(isValid(s, startIndex, i)){
                s.insert(i + 1, '.');
                backTracking(s, i + 2, dotCount + 1);
                s.deleteCharAt(i + 1);
            }else{
                break;
            }
        }
    }
    //[start, end]
    private boolean isValid(StringBuilder s, int start, int end){
        if(start > end)
            return false;
        if(s.charAt(start) == '0' && start != end)
            return false;
        int num = 0;
        for(int i = start; i <= end; i++){
            int digit = s.charAt(i) - '0';
            num = num * 10 + digit;
            if(num > 255)
                return false;
        }
        return true;
    }
}

//方法二：比上面的方法时间复杂度低，更好地剪枝，优化时间复杂度
class Solution {
    List<String> result = new ArrayList<String>();
	StringBuilder stringBuilder = new StringBuilder();

	public List<String> restoreIpAddresses(String s) {
		restoreIpAddressesHandler(s, 0, 0);
		return result;
	}

	// number表示stringbuilder中ip段的数量
	public void restoreIpAddressesHandler(String s, int start, int number) {
		// 如果start等于s的长度并且ip段的数量是4，则加入结果集，并返回
		if (start == s.length() && number == 4) {
			result.add(stringBuilder.toString());
			return;
		}
		// 如果start等于s的长度但是ip段的数量不为4，或者ip段的数量为4但是start小于s的长度，则直接返回
		if (start == s.length() || number == 4) {
			return;
		}
		// 剪枝：ip段的长度最大是3，并且ip段处于[0,255]
		for (int i = start; i < s.length() && i - start < 3 && Integer.parseInt(s.substring(start, i + 1)) >= 0
				&& Integer.parseInt(s.substring(start, i + 1)) <= 255; i++) {
			// 如果ip段的长度大于1，并且第一位为0的话，continue
			if (i + 1 - start > 1 && s.charAt(start) - '0' == 0) {
				continue;
			}
			stringBuilder.append(s.substring(start, i + 1));
			// 当stringBuilder里的网段数量小于3时，才会加点；如果等于3，说明已经有3段了，最后一段不需要再加点
			if (number < 3) {
				stringBuilder.append(".");
			}
			number++;
			restoreIpAddressesHandler(s, i + 1, number);
			number--;
			// 删除当前stringBuilder最后一个网段，注意考虑点的数量的问题
			stringBuilder.delete(start + number, i + number + 2);
		}
	}
}
```

## 子集问题

### Leetcode[78. 子集](https://leetcode.cn/problems/subsets/)

==回溯==

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int startIndex) {
        res.add(new ArrayList<>(path));
        if (startIndex == nums.length) return;

        for (int i = startIndex; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```

==位运算==

```java
class Solution {
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}
```

### Leetcode[90. 子集 II](https://leetcode.cn/problems/subsets-ii/)

==回溯 排序==

```java
class Solution {
    private LinkedList<String> res;
    private LinkedList<String> path = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        path.add("JFK");
        boolean[] used = new boolean[tickets.size()];
        backTracking((ArrayList) tickets, used);
        return res;
    }

    public boolean backTracking(ArrayList<List<String>> tickets, boolean[] used) {
        if (path.size() == tickets.size() + 1) {
            res = new LinkedList(path);
            return true;
        }

        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path.getLast())) {
                path.add(tickets.get(i).get(1));
                used[i] = true;

                if (backTracking(tickets, used)) {
                    return true;
                }

                used[i] = false;
                path.removeLast();
            }
        }
        return false;
    }
}
```

## 排列问题

### Leetcode[46. 全排列](https://leetcode.cn/problems/permutations/)

给定一个不含重复数字的数组 `nums` ，返回其 *所有可能的全排列* 。你可以 **按任意顺序** 返回答案。

==回溯==

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
```

```java
class Solution {

    List<List<Integer>> result = new ArrayList<>();// 存放符合条件结果的集合
    LinkedList<Integer> path = new LinkedList<>();// 用来存放符合条件结果
    boolean[] used;
    public List<List<Integer>> permute(int[] nums) {
        if (nums.length == 0){
            return result;
        }
        used = new boolean[nums.length];
        permuteHelper(nums);
        return result;
    }

    private void permuteHelper(int[] nums){
        if (path.size() == nums.length){
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++){
            if (used[i]){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            permuteHelper(nums);
            path.removeLast();
            used[i] = false;
        }
    }
}
```

### Leetcode[47. 全排列 II](https://leetcode.cn/problems/permutations-ii/)

==回溯 排序==

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        List<Integer> output = new ArrayList<Integer>();
        for (int num : nums) {
            output.add(num);
        }
        int n = nums.length;
        backtrack(n, output, res, 0);
        return res;
    }

    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<Integer>(output));
        }
        Set<Integer> set = new HashSet<>();
        for (int i = first; i < n; i++) {
            if (set.contains(output.get(i))) continue;
            set.add(output.get(i));
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
```

```java
class Solution {
    //存放结果
    List<List<Integer>> result = new ArrayList<>();
    //暂存结果
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        boolean[] used = new boolean[nums.length];
        Arrays.fill(used, false);
        Arrays.sort(nums);
        backTrack(nums, used);
        return result;
    }

    private void backTrack(int[] nums, boolean[] used) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // used[i - 1] == true，说明同⼀树⽀nums[i - 1]使⽤过
            // used[i - 1] == false，说明同⼀树层nums[i - 1]使⽤过
            // 如果同⼀树层nums[i - 1]使⽤过则直接跳过
            if (i > 0 && nums[i] == nums[i - 1] && used[i - 1] == false) {
                continue;
            }
            //如果同⼀树⽀nums[i]没使⽤过开始处理
            if (used[i] == false) {
                used[i] = true;//标记同⼀树⽀nums[i]使⽤过，防止同一树枝重复使用
                path.add(nums[i]);
                backTrack(nums, used);
                path.remove(path.size() - 1);//回溯，说明同⼀树层nums[i]使⽤过，防止下一树层重复
                used[i] = false;//回溯
            }
        }
    }
}
```



## 棋盘问题

### Leecode[51. N 皇后](https://leetcode.cn/problems/n-queens/)

==动态规划==

```java
class Solution {
    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] c : chessboard) {
            Arrays.fill(c, '.');
        }
        backTrack(n, 0, chessboard);
        return res;
    }


    public void backTrack(int n, int row, char[][] chessboard) {
        if (row == n) {
            res.add(Array2List(chessboard));
            return;
        }

        for (int col = 0;col < n; ++col) {
            if (isValid (row, col, n, chessboard)) {
                chessboard[row][col] = 'Q';
                backTrack(n, row+1, chessboard);
                chessboard[row][col] = '.';
            }
        }

    }


    public List Array2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();

        for (char[] c : chessboard) {
            list.add(String.copyValueOf(c));
        }
        return list;
    }


    public boolean isValid(int row, int col, int n, char[][] chessboard) {
        // 检查列
        for (int i=0; i<row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        // 检查135度对角线
        for (int i=row-1, j=col+1; i>=0 && j<=n-1; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }
}
```

### Leetcode[37. 解数独](https://leetcode.cn/problems/sudoku-solver/)

==动态规划==

```java
class Solution {
    public void solveSudoku(char[][] board) {
        solveSudokuHelper(board);
    }

    private boolean solveSudokuHelper(char[][] board){
        //「一个for循环遍历棋盘的行，一个for循环遍历棋盘的列，
        // 一行一列确定下来之后，递归遍历这个位置放9个数字的可能性！」
        for (int i = 0; i < 9; i++){ // 遍历行
            for (int j = 0; j < 9; j++){ // 遍历列
                if (board[i][j] != '.'){ // 跳过原始数字
                    continue;
                }
                for (char k = '1'; k <= '9'; k++){ // (i, j) 这个位置放k是否合适
                    if (isValidSudoku(i, j, k, board)){
                        board[i][j] = k;
                        if (solveSudokuHelper(board)){ // 如果找到合适一组立刻返回
                            return true;
                        }
                        board[i][j] = '.';
                    }
                }
                // 9个数都试完了，都不行，那么就返回false
                return false;
                // 因为如果一行一列确定下来了，这里尝试了9个数都不行，说明这个棋盘找不到解决数独问题的解！
                // 那么会直接返回， 「这也就是为什么没有终止条件也不会永远填不满棋盘而无限递归下去！」
            }
        }
        // 遍历完没有返回false，说明找到了合适棋盘位置了
        return true;
    }

    /**
     * 判断棋盘是否合法有如下三个维度:
     *     同行是否重复
     *     同列是否重复
     *     9宫格里是否重复
     */
    private boolean isValidSudoku(int row, int col, char val, char[][] board){
        // 同行是否重复
        for (int i = 0; i < 9; i++){
            if (board[row][i] == val){
                return false;
            }
        }
        // 同列是否重复
        for (int j = 0; j < 9; j++){
            if (board[j][col] == val){
                return false;
            }
        }
        // 9宫格里是否重复
        int startRow = (row / 3) * 3;
        int startCol = (col / 3) * 3;
        for (int i = startRow; i < startRow + 3; i++){
            for (int j = startCol; j < startCol + 3; j++){
                if (board[i][j] == val){
                    return false;
                }
            }
        }
        return true;
    }
}
```

## 其他

### Leetcode[491. 递增子序列](https://leetcode.cn/problems/non-decreasing-subsequences/)

==回溯==

```java
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrack(nums, 0);
        return res;
    }

    private void backtrack(int[] nums, int startIndex) {
        if (path.size() >= 2) res.add(new ArrayList<>(path));
        if (startIndex == nums.length) return;
        Set<Integer> set = new HashSet<>();
        for (int i = startIndex; i < nums.length; i++) {
            if (set.contains(nums[i])) continue;
            if (path.size() > 0 && nums[i] < path.get(path.size() - 1)) continue;
            set.add(nums[i]);
            path.add(nums[i]);
            backtrack(nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
```

### Leetcode[332. 重新安排行程](https://leetcode.cn/problems/reconstruct-itinerary/)

```java
class Solution {
    private LinkedList<String> res;
    private LinkedList<String> path = new LinkedList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> a.get(1).compareTo(b.get(1)));
        path.add("JFK");
        boolean[] used = new boolean[tickets.size()];
        backTracking((ArrayList) tickets, used);
        return res;
    }

    public boolean backTracking(ArrayList<List<String>> tickets, boolean[] used) {
        if (path.size() == tickets.size() + 1) {
            res = new LinkedList(path);
            return true;
        }

        for (int i = 0; i < tickets.size(); i++) {
            if (!used[i] && tickets.get(i).get(0).equals(path.getLast())) {
                path.add(tickets.get(i).get(1));
                used[i] = true;

                if (backTracking(tickets, used)) {
                    return true;
                }

                used[i] = false;
                path.removeLast();
            }
        }
        return false;
    }
}
```

```java
class Solution {
    private Deque<String> res;
    private Map<String, Map<String, Integer>> map;

    private boolean backTracking(int ticketNum){
        if(res.size() == ticketNum + 1){
            return true;
        }
        String last = res.getLast();
        if(map.containsKey(last)){//防止出现null
            for(Map.Entry<String, Integer> target : map.get(last).entrySet()){
                int count = target.getValue();
                if(count > 0){
                    res.add(target.getKey());
                    target.setValue(count - 1);
                    if(backTracking(ticketNum)) return true;
                    res.removeLast();
                    target.setValue(count);
                }
            }
        }
        return false;
    }

    public List<String> findItinerary(List<List<String>> tickets) {
        map = new HashMap<String, Map<String, Integer>>();
        res = new LinkedList<>();
        for(List<String> t : tickets){
            Map<String, Integer> temp;
            if(map.containsKey(t.get(0))){
                temp = map.get(t.get(0));
                temp.put(t.get(1), temp.getOrDefault(t.get(1), 0) + 1);
            }else{
                temp = new TreeMap<>();//升序Map
                temp.put(t.get(1), 1);
            }
            map.put(t.get(0), temp);

        }
        res.add("JFK");
        backTracking(tickets.size());
        return new ArrayList<>(res);
    }
}
```



# 贪心

## Leetcode[455. 分发饼干](https://leetcode.cn/problems/assign-cookies/)

==贪心==

小饼干优先喂饱喂给小胃口的孩子

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0, i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j++]) {
                i++;
                res++;
            }
        }
        return res;
    }
}
```

## Leetcode[376. 摆动序列](https://leetcode.cn/problems/wiggle-subsequence/)

==贪心==

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int K) {
    	// 将数组按照绝对值大小从大到小排序，注意要按照绝对值的大小
	nums = IntStream.of(nums)
		     .boxed()
		     .sorted((o1, o2) -> Math.abs(o2) - Math.abs(o1))
		     .mapToInt(Integer::intValue).toArray();
	int len = nums.length;	    
	for (int i = 0; i < len; i++) {
	    //从前向后遍历，遇到负数将其变为正数，同时K--
	    if (nums[i] < 0 && K > 0) {
	    	nums[i] = -nums[i];
	    	K--;
	    }
	}
	// 如果K还大于0，那么反复转变数值最小的元素，将K用完

	if (K % 2 == 1) nums[len - 1] = -nums[len - 1];
	return Arrays.stream(nums).sum();

    }
}
```

```java
class Solution {
    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int sum = 0;
        if (nums[0] > 0) {
            for (int num : nums) sum += num;
            if (k % 2 == 1) sum -= 2 * nums[0];
            return sum;
        }
        for (int i = 0; i < nums.length; i++) {
            if (k > 0) {
                if (nums[i] == 0) k = 0;
                else if (nums[i] > 0) {
                    sum += nums[i];
                    if (k % 2 == 0) k = 0;
                    else {
                        sum -= 2 * Math.min(-1 * nums[i - 1], nums[i]);
                        k = 0;
                    }
                } else sum += -1 * nums[i];
                k--;  
            } else {
                sum += nums[i];
            }
            
        }
        if (k > 0) {
            if (k % 2 == 1) sum += 2 * nums[nums.length - 1];
        }
        return sum;
    }
}
```

## Leetcode[860. 柠檬水找零](https://leetcode.cn/problems/lemonade-change/)

==贪心==

```java
class Solution {
    public boolean lemonadeChange(int[] bills) {
        int five = 0;
        int ten = 0;

        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                five++;
            } else if (bills[i] == 10) {
                five--;
                ten++;
            } else if (bills[i] == 20) {
                if (ten > 0) {
                    ten--;
                    five--;
                } else {
                    five -= 3;
                }
            }
            if (five < 0 || ten < 0) return false;
        }
        
        return true;
    }
}
```

## 序列问题

### Leetcode[376. 摆动序列](https://leetcode.cn/problems/wiggle-subsequence/)

==贪心==

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int preDiff = 0, curDiff = 0, result = 1;
        for (int i = 0; i < nums.length - 1; i++) {
            curDiff = nums[i + 1] - nums[i];
            if ((preDiff <= 0 && curDiff > 0) || (preDiff >= 0 && curDiff < 0)) {
                preDiff = curDiff;
                result++;
            }
        }     
        return result;                 
    }
}
```

==动态规划==

- 设 dp 状态`dp[i][0]`，表示考虑前 i 个数，第 i 个数作为山峰的摆动子序列的最长长度

- 设 dp 状态`dp[i][1]`，表示考虑前 i 个数，第 i 个数作为山谷的摆动子序列的最长长度

  

  `dp[i][0] = max(dp[i][0], dp[j][1] + 1)`，其中`0 < j < i`且`nums[j] < nums[i]`，表示将 nums[i]接到前面某个山谷后面，作为山峰。

  `dp[i][1] = max(dp[i][1], dp[j][0] + 1)`，其中`0 < j < i`且`nums[j] > nums[i]`，表示将 nums[i]接到前面某个山峰后面，作为山谷。

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        // 0 i 作为波峰的最大长度
        // 1 i 作为波谷的最大长度
        int dp[][] = new int[nums.length][2];

        dp[0][0] = dp[0][1] = 1;
        for (int i = 1; i < nums.length; i++){
            //i 自己可以成为波峰或者波谷
            dp[i][0] = dp[i][1] = 1;

            for (int j = 0; j < i; j++){
                if (nums[j] > nums[i]){
                    // i 是波谷
                    dp[i][1] = Math.max(dp[i][1], dp[j][0] + 1);
                }
                if (nums[j] < nums[i]){
                    // i 是波峰
                    dp[i][0] = Math.max(dp[i][0], dp[j][1] + 1);
                }
            }
        }

        return Math.max(dp[nums.length - 1][0], dp[nums.length - 1][1]);
    }
}
```

### Leetcode[738. 单调递增的数字](https://leetcode.cn/problems/monotone-increasing-digits/)

==贪心==

```java
class Solution {
    public int monotoneIncreasingDigits(int n) {
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for (int i = s.length() - 2; i >= 0; i--) {
            if (chars[i] > chars[i + 1]) {
                chars[i]--;
                start = i+1;
            }
        }
        for (int i = start; i < s.length(); i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
```

## 股票问题

见动态规划篇章

### Leetcode[121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)

### Leetcode[122. 买卖股票的最佳时机 II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/)

### Leetcode[714. 买卖股票的最佳时机含手续费](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

## 两个维度权衡问题

### Leetcode[135. 分发糖果](https://leetcode.cn/problems/candy/)

==贪心==

```java
class Solution {
    public int candy(int[] ratings) {
        int[] temp = new int[ratings.length];
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) temp[i] = temp[i - 1] + 1;
        }

        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) temp[i] = Math.max(temp[i], temp[i + 1] + 1);
        }

        int res = 0;
        for (int num : temp) res += num;
        return res + ratings.length;
    }
}
```

### Leetcode[406. 根据身高重建队列](https://leetcode.cn/problems/queue-reconstruction-by-height/)

==贪心==

```java
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        // 身高从大到小排（身高相同k小的站前面）
        Arrays.sort(people, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];   // a - b 是升序排列，故在a[0] == b[0]的狀況下，會根據k值升序排列
            return b[0] - a[0];   //b - a 是降序排列，在a[0] != b[0]，的狀況會根據h值降序排列
        });

        LinkedList<int[]> que = new LinkedList<>();

        for (int[] p : people) {
            que.add(p[1],p);   //Linkedlist.add(index, value)，會將value插入到指定index裡。
        }

        return que.toArray(new int[people.length][0]);
    }
}
```



## Leetcode[53. 最大子数组和](https://leetcode.cn/problems/maximum-subarray/)

==贪心==

```java
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 1){
            return nums[0];
        }
        int sum = Integer.MIN_VALUE;
        int count = 0;
        for (int i = 0; i < nums.length; i++){
            count += nums[i];
            sum = Math.max(sum, count); // 取区间累计的最大值（相当于不断确定最大子序终止位置）
            if (count <= 0){
                count = 0; // 相当于重置最大子序起始位置，因为遇到负数一定是拉低总和
            }
        }
       return sum;
    }
}
```

==动态规划==

```java
// DP 方法
class Solution {
    public int maxSubArray(int[] nums) {
        int ans = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        ans = dp[0];

        for (int i = 1; i < nums.length; i++){
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            ans = Math.max(dp[i], ans);
        }

        return ans;
    }
}
```



# 动态规划

## 背包问题

### 【01背包】Leetcode[416. 分割等和子集](https://leetcode.cn/problems/partition-equal-subset-sum/)

==动态规划 01背包==

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum % 2 == 1) return false;
        int[] dp = new int[sum / 2 + 1];
        for (int num : nums)
            for (int w = dp.length - 1; w >= num; w--)
                dp[w] = Math.max(dp[w], dp[w - num] + num);
        return sum - 2 * dp[dp.length - 1] == 0;
    }
}
```

### 【01背包】Leetcode[1049. 最后一块石头的重量 II](https://leetcode.cn/problems/last-stone-weight-ii/)

```java
class Solution {
    int res = Integer.MAX_VALUE;
    
    public int lastStoneWeightII(int[] stones) {
        dfs(stones, 0);
        return res;
    }

    private void dfs(int[] stones, int start) {
        if (start == stones.length) {
            int prev = 0;
            for (int s : stones) prev = Math.abs(prev - s);
            res = Math.min(res, prev);
            return;
        }

        for (int i = start; i < stones.length; i++) {
            swap(stones, start, i);
            dfs(stones, start + 1);
            swap(stones, start, i);
        }
    }

    private void swap(int[] stones, int i, int j) {
        int temp = stones[i];
        stones[i] = stones[j];
        stones[j] = temp;
    }
}
```

### 【01背包】Leetcode[494. 目标和](https://leetcode.cn/problems/target-sum/)

==动态规划 01背包==

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) sum += num;
        if (sum < -1 * target || sum < target) return 0;
        if ((sum + target) % 2 == 1) return 0;
        int weight = Math.min((sum + target) / 2, (sum - target) / 2);
        int[] dp = new int[weight +1];
        dp[0] = 1;
        for (int num : nums)
            for (int w = weight; w >= num; w--)
                dp[w] += dp[w - num];
        return dp[weight]; 
    }
}
```

### 【完全背包】Leetcode[518. 零钱兑换 II](https://leetcode.cn/problems/coin-change-ii/)

==动态规划 01背包 完全背包==

```java
class Solution {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins)
            for (int w = coin; w <= amount; w++)
                dp[w] += dp[w - coin];
        return dp[amount];
    }
}
```

### 【完全背包】Leetcode[377. 组合总和 Ⅳ](https://leetcode.cn/problems/combination-sum-iv/)

==动态规划 01背包 完全背包==

```java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int w = 1; w <= target; w++)
            for (int num : nums) 
                if (w >= num)
                    dp[w] += dp[w - num];
        return dp[target];
    }
}
```

### 【完全背包】Leecode[70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)

==动态规划 01背包 完全背包==

```java
class Solution {
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int w = 1; w <= n; w++) {
            dp[w] += dp[w - 1];
            if (w >= 2) dp[w] += dp[w - 2];
        }
        return dp[n];
    }
}
```

==另外一种动态规划==

```java
class Solution {
    public int climbStairs(int n) {
        int pre1 = 0, pre2 = 1;
        for (int i = 1; i <= n; i++) {
            int temp = pre2;
            pre2 = pre1 + pre2;
            pre1 = temp;
        }
        return pre2;
    }
}
```

### 【完全背包】Leecode[322. 零钱兑换](https://leetcode.cn/problems/coin-change/)

==动态规划 01背包 完全背包==

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = Integer.MAX_VALUE;
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) dp[i] = max;
        for (int coin : coins)
            for (int w = coin; w <= amount; w++)
                if (dp[w - coin] != max)
                    dp[w] = Math.min(dp[w], dp[w - coin] + 1);
        return dp[amount] == max ? -1 : dp[amount];
    }
}
```

==贪心==

会超时，贪心策略为：优先选择大的面值

```java
class Solution {
    int res = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount){
        if(amount==0){
            return 0;
            }
        Arrays.sort(coins);
        mincoin(coins,amount,coins.length-1,0);
        return res==Integer.MAX_VALUE? -1:res;
    }
    private void mincoin(int[] coins,int amount, int index, int count){
        if(amount==0){
            res = Math.min(res,count);
            return;
        }
        if(index<0){
            return;
        }
        for(int i = amount/coins[index];i>=0 && i+count<res; i--){
            mincoin(coins,amount - (i*coins[index]), index-1, count+i);
        }
    }
}
```

### 【完全背包】Leecode [279. 完全平方数](https://leetcode.cn/problems/perfect-squares/)

==动态规划 01背包 完全背包==

```java
class Solution {
    public int numSquares(int n) {
        int m = (int)Math.pow(n, 0.5);
        int max = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) dp[i] = max;
        for (int i = 1; i <= m; i++)
            for (int w = i * i; w <= n; w++)
                    dp[w] = Math.min(dp[w], dp[w - i * i] + 1);
        return dp[n];
    }
}
```

## 股票问题

### Leetcode[121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)

==贪心==

```java
class Solution {
    public int maxProfit(int[] prices) {
        // 找到一个最小的购入点
        int low = Integer.MAX_VALUE;
        // res不断更新，直到数组循环完毕
        int res = 0;
        for(int i = 0; i < prices.length; i++){
            low = Math.min(prices[i], low);
            res = Math.max(prices[i] - low, res);
        }
        return res;
    }
}
```

==动态规划==

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int length = prices.length;
        // dp[i][0]代表第i天持有股票的最大收益
        // dp[i][1]代表第i天不持有股票的最大收益
        int[][] dp = new int[length][2];
        int result = 0;
        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] + prices[i], dp[i - 1][1]);
        }
        return dp[length - 1][1];
    }
}
```

### Leetcode[122. 买卖股票的最佳时机 II](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/)

==贪心==

```java
// 贪心思路
class Solution {
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 1; i < prices.length; i++) {
            result += Math.max(prices[i] - prices[i - 1], 0);
        }
        return result;
    }
}
```

==动态规划==

```java
// 动态规划
class Solution {
    // 实现1：二维数组存储
    // 可以将每天持有与否的情况分别用 dp[i][0] 和 dp[i][1] 来进行存储
    // 时间复杂度：O(n)，空间复杂度：O(n)
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][] dp = new int[n][2];     // 创建二维数组存储状态
        dp[0][1] = 0;                   // 初始状态
        dp[0][0] = -prices[0];
        for (int i = 1; i < n; ++i) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i]);    // 第 i 天，持有股票
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);    // 第 i 天，不持有股票
        }
        return dp[n - 1][1];    // 卖出股票收益高于持有股票收益，因此取[0]
    }
}
```

### Leetcode[714. 买卖股票的最佳时机含手续费](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

==贪心==

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int buy = prices[0] + fee;
        int profit = 0;
        for (int i = 1; i < n; ++i) {
            if (prices[i] + fee < buy) {
                buy = prices[i] + fee;
            } else if (prices[i] > buy) {
                profit += prices[i] - buy;
                buy = prices[i];
            }
        }
        return profit;
    }
}
```

==动态规划==

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        if (prices == null || prices.length == 0) return 0;
        int[][] dp = new int[prices.length][2];
        dp[0][0] = -prices[0] - fee;
        dp[0][1] = 0;
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] - prices[i] - fee);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
        }
        return dp[prices.length - 1][1];
    }
}
```

### Leetcode[123. 买卖股票的最佳时机 III](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/)

==动态规划==

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) {
            return 0;
        }
        int[][] dp = new int[prices.length][4];
        dp[0][0] = -prices[0];
        dp[0][2] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
            dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
        }
        return dp[prices.length - 1][3]; 
    }
}
```

### Leetcode[188. 买卖股票的最佳时机 IV](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iv/)

==动态规划==

滚动数组

```java
class Solution {
    public int maxProfit(int k, int[] prices) {
        if (prices == null && prices.length == 0) return 0;
        int[] dp = new int[ 2 * k];
        for (int i = 0; i < 2 * k; i += 2) dp[i] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[0] = Math.max(dp[0], -prices[i]);
            for (int j = 1; j < 2 * k; j++) {
                if (j % 2 == 0) dp[j] = Math.max(dp[j], dp[j - 1] - prices[i]);
                else dp[j] = Math.max(dp[j], dp[j - 1] + prices[i]);
            }
        }
        return dp[2 * k - 1];
    }
}
```

### Leetcode[309. 买卖股票的最佳时机含冷冻期](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/)

==动态规划==

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int[][] dp = new int[prices.length][3];
        dp[0][0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
            dp[i][1] = dp[i - 1][0] + prices[i];
            dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1]);
        }
        return Math.max(dp[prices.length - 1][1], dp[prices.length - 1][2]);
    }
}
```

# 图论

## Leecode[797. 所有可能的路径](https://leetcode.cn/problems/all-paths-from-source-to-target/)

==DFS==

```java
class Solution {
    List<List<Integer>> res;
    List<Integer> path;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        path.add(0);
        dfs(graph, 0);
        return res;
    }

    private void dfs(int[][] graph, int start) {
        if (start == graph.length - 1) {
            res.add(new ArrayList<>(path));
            return;
        }
        for (int next : graph[start]) {
            path.add(next);
            dfs(graph, next);
            path.remove(path.size() - 1);
        }
    }
}
```

## Leetcode[200. 岛屿数量](https://leetcode.cn/problems/number-of-islands/)

==DFS==

```java
class Solution {
    public int numIslands(char[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    res++;
                }
            }
        return res;
    }

    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
```

==BFS==

```java
class Solution {

    boolean[][] visited;
    int[][] move = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int numIslands(char[][] grid) {
        int res = 0;
        visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(!visited[i][j] && grid[i][j] == '1') {
                    bfs(grid, i, j);
                    res++;
                }
            }
        }
        return res;
    }

    //将这片岛屿上的所有陆地都访问到
    public void bfs(char[][] grid, int y, int x) {
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{y, x});
        visited[y][x] = true;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int m = cur[0];
            int n = cur[1];
            for(int i = 0; i < 4; i++) {
                int nexty = m + move[i][0];
                int nextx = n + move[i][1];
                if(nextx < 0 || nexty == grid.length || nexty < 0 || nextx == grid[0].length) continue;
                if(!visited[nexty][nextx] && grid[nexty][nextx] == '1') {
                    queue.offer(new int[]{nexty, nextx}); 
                    visited[nexty][nextx] = true; //只要加入队列就标记为访问
                }
            }
        }
    }
}
```

## 华为笔试9.20

见上文论述

## Leetcode[695. 岛屿的最大面积](https://leetcode.cn/problems/max-area-of-island/)

==DFS==

```java
class Solution {
    static int ans;
    static int temp;

    public int maxAreaOfIsland(int[][] grid) {
        ans = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1) {
                    temp = 0;
                    dfs(grid, i, j);
                    ans = Math.max(ans, temp);
                }
        return ans;
    }

    private void dfs(int[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i == grid.length || j == grid[0].length || grid[i][j] == 0) return;
        temp++;
        grid[i][j] = 0;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
```

```java
//这里使用深度优先搜索 DFS 来完成本道题目。我们使用 DFS 计算一个岛屿的面积，同时维护计算过的最大的岛屿面积。同时，为了避免对岛屿重复计算，我们在 DFS 的时候对岛屿进行 “淹没” 操作，即将岛屿所占的地方置为 0。
public int maxAreaOfIsland(int[][] grid) {
    int res = 0;
    for(int i = 0;i < grid.length;i++){
        for(int j = 0;j < grid[0].length;j++){
        	//每遇到一个岛屿就计算这个岛屿的面积同时”淹没“这个岛屿
            if(grid[i][j] == 1){
            	//每次计算一个岛屿的面积都要与res比较，维护最大的岛屿面积作为最后的答案
                res = Math.max(res,dfs(grid,i,j));
            }
        }
    }
    return res;
}
public int dfs(int[][] grid,int i,int j){
	//搜索边界：i，j超过grid的范围或者当前元素为0，即当前所在的地方已经是海洋
    if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return 0;
    //淹没土地，防止后续被重复计算
    grid[i][j] = 0;
    //递归的思路：要求当前土地(i,j)所在的岛屿的面积，则等于1加上下左右相邻的土地的总面积
    return 1 + dfs(grid,i - 1,j) +
               dfs(grid,i + 1,j) +
               dfs(grid,i,j + 1) +
               dfs(grid,i,j - 1);
}
```

==BFS==

```java
//BFS
class Solution {
    int[][] dir = {
        {0, 1}, {1, 0}, {0, -1}, {-1, 0}
    };

    int count;
    boolean visited[][];

    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        visited = new boolean[grid.length][grid[0].length];
        
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(visited[i][j] == false && grid[i][j] == 1){
                    count = 0;
                    bfs(grid, i, j);
                    res = Math.max(res, count);
                }
            }
        }
        return res;
    }
    private void bfs(int[][] grid, int x, int y){
        Queue<Integer> que = new LinkedList<>();
        que.offer(x);
        que.offer(y);
        visited[x][y] = true;
        count++;
        
        while(!que.isEmpty()){
            int currX = que.poll();
            int currY = que.poll();

            for(int i = 0; i < 4; i++){
                int nextX = currX + dir[i][0];
                int nextY = currY + dir[i][1];
                
                if(nextX < 0 || nextY < 0 || nextX >= grid.length || nextY >= grid[0].length)
                    continue;
                if(visited[nextX][nextY] == false && grid[nextX][nextY] == 1){
                    que.offer(nextX);
                    que.offer(nextY);
                    visited[nextX][nextY] = true;
                    count++;
                }
            }
        }
    }
}
```

## Leetcode[1020. 飞地的数量](https://leetcode.cn/problems/number-of-enclaves/)

==DFS==

```java
class Solution {
    public int numEnclaves(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        for (int i = 0; i < row; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, col - 1);
        }
        for (int i = 0; i < col; i++) {
            dfs(grid, 0, i);
            dfs(grid, row - 1, i);
        }
        int res = 0;
        for (int i = 1; i < row - 1; i++)
            for (int j = 1; j < col - 1; j++)
                if (grid[i][j] == 1)
                    res++;
        return res;
    }

    private void dfs(int[][] grid, int r, int c) {
        if (r < 0 || c < 0 || r == grid.length || c == grid[0].length || grid[r][c] == 0) return;
        grid[r][c] = 0;
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

}
```

==BFS==

```java
class Solution {
    // 四个方向
    private static final int[][] position = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    // 广度优先遍历，把可以通向边缘部分的 1 全部标记成 true
    public void bfs(int[][] grid, Queue<int[]> queue, boolean[][] visited) {
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] current: position) {
                int row = curPos[0] + current[0], col = curPos[1] + current[1];
                // 下标越界直接跳过
                if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length) 
                    continue;
                // 当前位置不是 1 或者已经被访问了就直接跳过
                if (visited[row][col] || grid[row][col] == 0) continue;
                visited[row][col] = true;
                queue.add(new int[]{row, col});
            }
        }
    }

    public int numEnclaves(int[][] grid) {
        int rowSize = grid.length, colSize = grid[0].length, ans = 0;	// ans 记录答案
        // 标记数组记录每个值为 1 的位置是否可以到达边界，可以为 true，反之为 false
        boolean[][] visited = new boolean[rowSize][colSize];
        Queue<int[]> queue = new ArrayDeque<>();
        // 搜索左侧边界和右侧边界查找 1 存入队列
        for (int row = 0; row < rowSize; row++) {
            if (grid[row][0] == 1) {
                visited[row][0] = true;
                queue.add(new int[]{row, 0});
            }
            if (grid[row][colSize - 1] == 1) {
                visited[row][colSize - 1] = true;
                queue.add(new int[]{row, colSize - 1});
            }
        }
        // 搜索上边界和下边界遍历，但是四个角不用遍历，因为上面已经遍历到了
        for (int col = 1; col < colSize - 1; col++) {
            if (grid[0][col] == 1) {
                visited[0][col] = true;
                queue.add(new int[]{0, col});
            }
            if (grid[rowSize - 1][col] == 1 && !visited[rowSize - 1][col]) {
                visited[rowSize - 1][col] = true;
                queue.add(new int[]{rowSize - 1, col});
            }
        }
        bfs(grid, queue, visited);		// 广度优先遍历
        // 查找没有标记过的 1，记录到 ans 中
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (grid[row][col] == 1 && !visited[row][col]) ++ans;
            }
        }
        return ans;
    }
}
```

## Leetcode[130. 被围绕的区域](https://leetcode.cn/problems/surrounded-regions/)

==DFS==

```java
class Solution {
    boolean[][] visited;

    public void solve(char[][] board) {
        int row = board.length, col = board[0].length;
        visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            dfs(board, i, 0);
            dfs(board, i, col - 1);
        }
        for (int i = 0; i < col; i++) {
            dfs(board, 0, i);
            dfs(board, row - 1, i);
        }
        for (int i = 1; i < row - 1; i++)
            for (int j = 1; j < col - 1; j++)
                if (board[i][j] == 'O' && !visited[i][j])
                    board[i][j] = 'X';
    }

    private void dfs(char[][] board, int r, int c) {
        if (r < 0 || c < 0 || r == board.length || c == board[0].length || board[r][c] == 'X' || visited[r][c]) return;
        visited[r][c] = true;
        dfs(board, r - 1, c);
        dfs(board, r + 1, c);
        dfs(board, r, c - 1);
        dfs(board, r, c + 1);
    }
}
```



==BFS==

```java
// 广度优先遍历
// 使用 visited 数组进行标记
class Solution {
    private static final int[][] position = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};  // 四个方向

    public void solve(char[][] board) {
        // rowSize：行的长度，colSize：列的长度
        int rowSize = board.length, colSize = board[0].length; 
        boolean[][] visited = new boolean[rowSize][colSize];
        Queue<int[]> queue = new ArrayDeque<>();
        // 从左侧边，和右侧边遍历
        for (int row = 0; row < rowSize; row++) {
            if (board[row][0] == 'O') {
                visited[row][0] = true;
                queue.add(new int[]{row, 0});
            }
            if (board[row][colSize - 1] == 'O') {
                visited[row][colSize - 1] = true;
                queue.add(new int[]{row, colSize - 1});
            }
        }
        // 从上边和下边遍历，在对左侧边和右侧边遍历时我们已经遍历了矩阵的四个角
        // 所以在遍历上边和下边时可以不用遍历四个角
        for (int col = 1; col < colSize - 1; col++) {
            if (board[0][col] == 'O') {
                visited[0][col] = true;
                queue.add(new int[]{0, col});
            }
            if (board[rowSize - 1][col] == 'O') {
                visited[rowSize - 1][col] = true;
                queue.add(new int[]{rowSize - 1, col});
            }
        }
        // 广度优先遍历，把没有被 'X' 包围的 'O' 进行标记
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            for (int[] pos: position) {
                int row = current[0] + pos[0], col = current[1] + pos[1];
                // 如果范围越界、位置已被访问过、该位置的值不是 'O'，就直接跳过
                if (row < 0 || row >= rowSize || col < 0 || col >= colSize) continue;
                if (visited[row][col] || board[row][col] != 'O') continue;
                visited[row][col] = true;
                queue.add(new int[]{row, col});
            }
        }
        // 遍历数组，把没有被标记的 'O' 修改成 'X'
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                if (board[row][col] == 'O' && !visited[row][col]) board[row][col] = 'X';
            }
        }
    }
}
```

## Leetcode[417. 太平洋大西洋水流问题](https://leetcode.cn/problems/pacific-atlantic-water-flow/)

==DFS==

```java
class Solution {
    boolean[][][] visited;
    

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int row = heights.length, col = heights[0].length;
        visited = new boolean[row][col][2];
        for (int i = 0; i < row; i++) {
            dfs(heights, i, 0, -1, 0);
            dfs(heights, i, col - 1, -1, 1);
        }
        for (int i = 0; i < col; i++) {
            dfs(heights, 0, i, -1, 0);
            dfs(heights, row - 1, i, -1, 1);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < row; i++)
            for (int j = 0; j < col; j++)
                if (visited[i][j][0] && visited[i][j][1])
                    res.add(Arrays.asList(i, j));
        return res;
    }

    private void dfs(int[][] heights, int r, int c, int prev, int sign) {
        if (r < 0 || c < 0 || r == heights.length || c == heights[0].length || visited[r][c][sign] || heights[r][c] < prev) return;
        visited[r][c][sign] = true;
        dfs(heights, r - 1, c, heights[r][c], sign);
        dfs(heights, r + 1, c, heights[r][c], sign);
        dfs(heights, r, c - 1, heights[r][c], sign);
        dfs(heights, r, c + 1, heights[r][c], sign);
    }
}
```

```java
class Solution {
    // 四个位置
    private static final int[][] position = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * @param heights 题目给定的二维数组
     * @param row 当前位置的行号
     * @param col 当前位置的列号
     * @param sign 记录是哪一条河，两条河中可以一个为 0，一个为 1
     * @param visited 记录这个位置可以到哪条河
     */
    public void dfs(int[][] heights, int row, int col, int sign, boolean[][][] visited) {
        for (int[] current: position) {
            int curRow = row + current[0], curCol = col + current[1];
            // 越界
            if (curRow < 0 || curRow >= heights.length || curCol < 0 || curCol >= heights[0].length)
                continue;
            // 高度不合适或者已经被访问过了
            if (heights[curRow][curCol] < heights[row][col] || visited[curRow][curCol][sign]) continue;
            visited[curRow][curCol][sign] = true;
            dfs(heights, curRow, curCol, sign, visited);
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rowSize = heights.length, colSize = heights[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        // 记录 [row, col] 位置是否可以到某条河，可以为 true，反之为 false；
        // 假设太平洋的标记为 1，大西洋为 0
        boolean[][][] visited = new boolean[rowSize][colSize][2];
        for (int row = 0; row < rowSize; row++) {
            visited[row][colSize - 1][0] = true;
            visited[row][0][1] = true;
            dfs(heights, row, colSize - 1, 0, visited);
            dfs(heights, row, 0, 1, visited);
        }
        for (int col = 0; col < colSize; col++) {
            visited[rowSize - 1][col][0] = true;
            visited[0][col][1] = true;
            dfs(heights, rowSize - 1, col, 0, visited);
            dfs(heights, 0, col, 1, visited);
        }
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                // 如果该位置即可以到太平洋又可以到大西洋，就放入答案数组
                if (visited[row][col][0] && visited[row][col][1])
                    ans.add(List.of(row, col));
            }
        }
        return ans;
    }
}
```

==BFS==

```java
class Solution {
    // 四个位置
    private static final int[][] position = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    /**
     * @param heights 题目给定的二维数组
     * @param queue 记录可以到达边界的节点
     * @param visited 记录这个位置可以到哪条河
     */
    public void bfs(int[][] heights, Queue<int[]> queue, boolean[][][] visited) {
        while (!queue.isEmpty()) {
            int[] curPos = queue.poll();
            for (int[] current: position) {
                int row = curPos[0] + current[0], col = curPos[1] + current[1], sign = curPos[2];
                // 越界
                if (row < 0 || row >= heights.length || col < 0 || col >= heights[0].length) continue;
                // 高度不合适或者已经被访问过了
                if (heights[row][col] < heights[curPos[0]][curPos[1]] || visited[row][col][sign]) continue;
                visited[row][col][sign] = true;
                queue.add(new int[]{row, col, sign});
            }
        }
    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rowSize = heights.length, colSize = heights[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        boolean[][][] visited = new boolean[rowSize][colSize][2];
        // 队列，保存的数据为 [行号, 列号, 标记]
        // 假设太平洋的标记为 1，大西洋为 0
        Queue<int[]> queue = new ArrayDeque<>();
        for (int row = 0; row < rowSize; row++) {
            visited[row][colSize - 1][0] = true;
            visited[row][0][1] = true;
            queue.add(new int[]{row, colSize - 1, 0});
            queue.add(new int[]{row, 0, 1});
        }
        for (int col = 0; col < colSize; col++) {
            visited[rowSize - 1][col][0] = true;
            visited[0][col][1] = true;
            queue.add(new int[]{rowSize - 1, col, 0});
            queue.add(new int[]{0, col, 1});
        }
        bfs(heights, queue, visited);
        for (int row = 0; row < rowSize; row++) {
            for (int col = 0; col < colSize; col++) {
                // 如果该位置即可以到太平洋又可以到大西洋，就放入答案数组
                if (visited[row][col][0] && visited[row][col][1])
                    ans.add(List.of(row, col));
            }
        }
        return ans;
    }
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
>public boolean add(E e) {
>
>​    return map.put(e, PRESENT)==null;
>
>}
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

# 剑指offer05-替换空格

请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

 示例 1：

> 输入：s = "We are happy."
> 输出："We%20are%20happy."

**法1(自己的)**

`时间复杂度：O(n) 空间复杂度：O(n)`

空间复杂度是因为需要额外空间sb

```java
    class Solution {
        public String replaceSpace(String s) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c == ' ') {
                    sb.append("%20");
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }
```

**法二 原地修改**

`时间复杂度：O(n) 空间复杂度：O(1)`

原地扩展长度，不需要使用额外空间。

```c++
class Solution {
public:
    string replaceSpace(string s) {
        int count = 0, len = s.size();
        // 统计空格数量
        for (char c : s) {
            if (c == ' ') count++;
        }
        // 修改 s 长度
        s.resize(len + 2 * count);
        // 倒序遍历修改
        for(int i = len - 1, j = s.size() - 1; i < j; i--, j--) {
            if (s[i] != ' ')
                s[j] = s[i];
            else {
                s[j - 2] = '%';
                s[j - 1] = '2';
                s[j] = '0';
                j -= 2;
            }
        }
        return s;
    }
};
```

# 剑指offer06-从尾到头打印链表

输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

**示例 1：**

```
输入：head = [1,3,2]
输出：[2,3,1]
```

**法一(自己的)**

利用栈

`时间复杂度：O(n) 空间复杂度：O(n)`

时间复杂度：O(n)。正向遍历一遍链表，然后从栈弹出全部节点，等于又反向遍历一遍链表。
空间复杂度：O(n)。额外使用一个栈存储链表中的每个节点。

```java
    class Solution {
        public int[] reversePrint(ListNode head) {
            Stack<Integer> s = new Stack<>();
            int length = 0;
            while(head != null) {
                s.push(head.val);
                head = head.next;
                length++;
            }
            int[] reverseList = new int[length];
            for (int i = 0; i < length; i++) {
                reverseList[i] = s.pop();
            }
            return reverseList;
        }
    }
```

**法二(递归法)**

`时间复杂度：O(n) 空间复杂度：O(n)`

递归实现链表反序。

```java
class Solution {
    ArrayList<Integer> tmp = new ArrayList<Integer>();
    public int[] reversePrint(ListNode head) {
        recur(head);
        int[] res = new int[tmp.size()];
        for(int i = 0; i < res.length; i++)
            res[i] = tmp.get(i);
        return res;
    }
    void recur(ListNode head) {
        if(head == null) return;
        recur(head.next);
        tmp.add(head.val);
    }
}

```

# **剑指offer07-重建二叉树

输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。

假设输入的前序遍历和中序遍历的结果中都不含重复的数字。

示例1：

![img](https://assets.leetcode.com/uploads/2021/02/19/tree.jpg)

```
Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
Output: [3,9,20,null,null,15,7]
```

**法1(自己的)**

`时间复杂度：O(n) 空间复杂度：O(n)`

```java
    class Solution {
        int[] preOrder;
        int[] inOrder;
        int pos = 0;
        public TreeNode buildTree(int[] preorder, int[] inorder) {
            if (preorder.length == 0) {
                return null;
            }
            preOrder = preorder;
            inOrder = inorder;
            return buildTree(0, preorder.length);
        }
        private TreeNode buildTree(int start, int end) {
            int rootValue = preOrder[pos++];
            TreeNode root = new TreeNode(rootValue);
            int leftStart = start;
            int rightEnd = end;
            int leftEnd = 0;
            int rightStart = 0;
            if ((end - start) == 1) {
                root.left = null;                
                root.right = null;
            } else {
                for (int i = start; i < end; i++) {
                    if (inOrder[i] == rootValue) {
                        leftEnd = i;
                        rightStart = i + 1;
                        break;
                    }
                }
                root.left = (leftStart == leftEnd ? null : buildTree(leftStart, leftEnd));
                root.right = (rightStart == rightEnd ? null : buildTree(rightStart, rightEnd));
            }
            return root;
        }
    }
```

**法二**

`时间复杂度：O(n) 空间复杂度：O(n)`

使用HahsMap来进行查找，更快。

时间复杂度O(N) ： 其中 N为树的节点数量。初始化 HashMap 需遍历 inorder ，占用 O(N) 。递归共建立 N 个节点，每层递归中的节点建立、搜索操作占用 O(1) ，因此使用 O(N) 时间。
空间复杂度 O(N) ： HashMap 使用 O(N) 额外空间；最差情况下（输入二叉树为链表时），递归深度达到 N ，占用 O(N) 的栈帧空间；因此总共使用 O(N)空间。

```java
class Solution {
    int[] preorder;
    HashMap<Integer, Integer> dic = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for(int i = 0; i < inorder.length; i++)
            dic.put(inorder[i], i);
        return recur(0, 0, inorder.length - 1);
    }
    TreeNode recur(int root, int left, int right) {
        if(left > right) return null;                          // 递归终止
        TreeNode node = new TreeNode(preorder[root]);          // 建立根节点
        int i = dic.get(preorder[root]);                       // 划分根节点、左子树、右子树
        node.left = recur(root + 1, left, i - 1);              // 开启左子树递归
        node.right = recur(root + i - left + 1, i + 1, right); // 开启右子树递归
        return node;                                           // 回溯返回根节点
    }
}

```



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

# **剑指Offer12-矩阵中的路径

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

* 时间复杂度 $O(3^KMN)$： 最差情况下，需要遍历矩阵中长度为 K 字符串的所有方案，时间复杂度为 $O(3^K)$；矩阵中共有 MN个起点，时间复杂度为 $O(MN)$ 。方案数计算： 设字符串长度为 K，搜索中每个字符有上、下、左、右四个方向可以选择，舍弃回头（上个字符）的方向，剩下 3种选择，因此方案数的复杂度为 $O(3^K)$。

* 空间复杂度 $O(K)$ ： 搜索过程中的递归深度不超过 K ，因此系统因函数调用累计使用的栈空间占用 $O(K)$（因为函数返回后，系统调用的栈空间会释放）。最坏情况下 $K = MN$ ，递归深度为 $MN$，此时系统栈使用 $O(MN)$的额外空间。

  ==<del>如果递归中使用全局变量，则为O(K);如果传递broad参数，则为O(KMN)</del>>==(即使传递broad参数，传递过程中使用的仍然是$O(K)$)

**法一(自己的)**

`时间复杂度：O(3^KMN) 空间复杂度：O(MN)`

```java
    class Solution {
        int[][] track;
        char[][] map;
        String matchWord;
        
        public boolean exist(char[][] board, String word) {
            if (board.length == 0 || board[0].length == 0 || word.equals("")) {
                return false;
            }
            
            char firstChar = word.charAt(0);
            boolean state = false;
            map = board;
            matchWord = word;
            for (int i = 0; i < board.length; i++) {
                if (state == true) {
                    break;
                }
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == firstChar) {
                        track = new int[board.length][board[0].length];
                        track[i][j] = 1;
                        if (word.length() == 1) {
                            state = true;
                            break;
                        }
                        if (state =  move(i, j, 1)) {
                            break;
                        }
                    }
                }
            }
            return state;
        }
   
        public boolean move(int i, int j, int matchCount) {
            if (canMove(i + 1, j, matchCount)) {
                if (update(i + 1, j, matchCount)) {
                    return true;
                }
            }
            if (canMove(i - 1, j, matchCount)) {
                if (update(i - 1, j, matchCount)) {
                    return true;
                }
            }
            if (canMove(i, j + 1, matchCount)) {
                if (update(i, j + 1, matchCount)) {
                    return true;
                }
            }
            if (canMove(i, j - 1, matchCount)) {
                if (update(i, j - 1, matchCount)) {
                    return true;
                }
            }
            return false;
        }
        public boolean canMove(int i, int j, int matchCount) {
            if (i < 0 || i >= map.length) {
                return false;
            }
            if (j < 0 || j >= map[0].length) {
                return false;
            }
            if (track[i][j] == 1) {
                return false;
            }
            if (map[i][j] == matchWord.charAt(matchCount)) {
                return true;
            }
            return false;
        }
        public boolean update(int i, int j, int matchCount) {
            track[i][j] = 1;
            if (++matchCount == matchWord.length() || move(i, j, matchCount)) {
                return true;
            }
            track[i][j] = 0;
            return false;
        }
    }
```

**法二(他人的)：DFS+回溯**

`时间复杂度：O(3^KMN) 空间复杂度：O(MN)`

法二相比法一，代码更加简短；而且没有使用额外的空间作为标记数组，直接在board上进行操作，更加能体现回溯的过程。

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
                if(dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }
    boolean dfs(char[][] board, char[] word, int i, int j, int k) {
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word[k]) return false;
        if(k == word.length - 1) return true;
        board[i][j] = '\0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) || 
                      dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
        board[i][j] = word[k];
        return res;
    }
}

```

# *剑指Offer19-正则表达式匹配

请实现一个函数用来匹配包含'. '和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。

==动态规划==

`时间 O(mn) 空间 O(mn)`

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int j = 2; j <= n; j += 2) {
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        }
        for (int i = 1; i <= m; i++) 
            for (int j = 1; j <= n; j++) {
                dp[i][j] = p.charAt(j - 1) == '*' ? 
                    dp[i][j - 2] || (dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.')) :
                    dp[i - 1][j - 1] && (s.charAt(i - 1) == p.charAt(j - 1) || p.charAt(j - 1) == '.');
            }
        return dp[m][n];
    }
} 
```



# 剑指Offer30-包含main函数的栈

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

示例：

> MinStack minStack = new MinStack();
> minStack.push(-2);
> minStack.push(0);
> minStack.push(-3);
> minStack.min();   --> 返回 -3.
> minStack.pop();
> minStack.top();      --> 返回 0.
> minStack.min();   --> 返回 -2。

**法一（自己的）**

`时间复杂度：O(1) 空间复杂度：O(n)`

```java
lass MinStack {
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
```

**法二**

`时间复杂度：O(1) 空间复杂度：O(n)`

法二与法一相比，两者复杂度量级相同，但法二实际占用的空间更少，因为其辅助栈所占用的空间更少。

法一的辅助栈存储着每个元素在主栈中对应的最小值；法二的辅助栈

```java
class MinStack {
    Stack<Integer> A, B;
    public MinStack() {
        A = new Stack<>();
        B = new Stack<>();
    }
    public void push(int x) {
        A.add(x);
        if(B.empty() || B.peek() >= x)
            B.add(x);
    }
    public void pop() {
        if(A.pop().equals(B.peek())) //注意使用equals，而不是使用"==“符号
            B.pop();
    }
    public int top() {
        return A.peek();
    }
    public int min() {
        return B.peek();
    }
}
```

# 剑指Offer31-栈的压入弹出序列

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

**法一（自己的）**

`时间复杂度：O(n) 空间复杂度：O(n)`

法一直接构造一个辅助栈来模拟栈的压入和弹出过程。

```java
    class Solution {
        private Stack<Integer> s = new Stack<>();
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            int popIndex = 0;
            int length = pushed.length;
            if (length != popped.length) {
                return false;
            }
            for (int i = 0; i < length; i++) {
                s.push(pushed[i]);
                while (!s.isEmpty() && s.peek() == popped[popIndex]) {
                    popIndex++;
                    s.pop();
                }
                if (popIndex == length) {
                    return true;
                }
            }
            return s.isEmpty() ? true : false; //直接"return s.isEmpty();"即可
        }
    }
```

**法二**

`时间复杂度：O(n) 空间复杂度：O(n)`

法二只考虑数组长度相等的情况，没有考虑数组长度不相等的情况。从健壮性来说，法一比法二好。

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for(int num : pushed) {
            stack.push(num); // num 入栈
            while(!stack.isEmpty() && stack.peek() == popped[i]) { // 循环判断与出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }
}
```

# 剑指Offer32I-从上到下打印二叉树I

从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            return new int[0];
        }
        Queue<TreeNode> visited = new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        visited.offer(root);
        while (!visited.isEmpty()) {
            TreeNode temp = visited.poll();
            list.add(temp.val);
            if (temp.left != null) {
                visited.offer(temp.left);
            }
            if (temp.right != null) {
                visited.offer(temp.right);
            }
        }
        int[] ans = new int[list.size()];
        for (int i = 0 ; i < ans.length; i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }
}
```

```java
class Solution {
    List<Integer> l1 = new ArrayList<>();

    public int[] levelOrder(TreeNode root) {
        if (root == null) return new int[0];
        List<TreeNode> l = new ArrayList<>();
        l.add(root);
        bfs(l);
        int[] ans = new int[l1.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = l1.get(i);
        }
        return ans;
    }

    public void bfs(List<TreeNode> children) {
        List<TreeNode> allChildren = new ArrayList<>();
        for (TreeNode node : children) {
            l1.add(node.val);
            if (node.left != null) {
                allChildren.add(node.left);
            }
            if (node.right != null) {
                allChildren.add(node.right);
            }
        }
        if (allChildren.size() > 0) {
            bfs(allChildren);
        }
    }
}
```



# 剑指Offer32II-从上到下打印二叉树II

从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

 **法一(自己的)**

`时间复杂度：O(n) 空间复杂度：O(logn)+O(n)=O(n)`

广度优先遍历(BFS)

```java
       class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new LinkedList<>();
            }
            List<TreeNode> tmp = new LinkedList<>();
            tmp.add(root);
            return levelOrder(tmp);
        }

        List<List<Integer>> levelOrder(List<TreeNode> list) {
            List<TreeNode> tmpList = new LinkedList<>();
            List<List<Integer>> res = new LinkedList<>();
            List<Integer> tmpRes = new LinkedList<>();

            for (TreeNode node : list) {
                tmpRes.add(node.val);
                if (node.left != null) {
                    tmpList.add(node.left);
                }
                if (node.right != null) {
                    tmpList.add(node.right);
                }
            }
            res.add(tmpRes);
            if (!tmpList.isEmpty()) {
                res.addAll(levelOrder(tmpList));
            }
            return res;
        }
    }
```

**法二 迭代写法**

`时间复杂度：O(n) 空间复杂度：O(n)`

时间复杂度 O(N) ： N 为二叉树的节点数量，即 BFS 需循环 NN次。
空间复杂度 O(N) ： 最差情况下，即当树为平衡二叉树时，最多有 N/2 个树节点同时在 queue 中，使用 O(N) 大小的额外空间。

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                tmp.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }
}

```

**法三 递归写法**

`时间复杂度：O(n) 空间复杂度：O(n)`

==法三使用全局变量，法一法二没有使用全局变量==

```java
class Solution {
    List<List<Integer>> node=new ArrayList();
    public List<List<Integer>> levelOrder(TreeNode root) {
        lei(root,0);
        return node;
    }
    public void lei(TreeNode root,int k){
        if(root!=null){
            if(node.size()<=k)node.add(new ArrayList());
            node.get(k).add(root.val);
            lei(root.left,k+1);
            lei(root.right,k+1);
        }
    }
}
```

# 剑指Offer32III-从上打印二叉树III

请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

**法一(自己的)-层序遍历+倒序**

`时间复杂度：O(n) 空间复杂度：O(n)`

直接在原有基础上加上

> `            for(int i=1;i<res.size();i+=2){
>              Collections.reverse(res.get(i));
>       }`

```java
class Solution {
        public List<List<Integer>> levelOrder(TreeNode root) {
            if (root == null) {
                return new LinkedList<>();
            }
            List<TreeNode> tmp = new LinkedList<>();
            tmp.add(root);
            List<List<Integer>> res = levelOrder(tmp);
            for(int i=1;i<res.size();i+=2){
                Collections.reverse(res.get(i));
            }
            return res;
        }

        List<List<Integer>> levelOrder(List<TreeNode> list) {
            List<TreeNode> tmpList = new LinkedList<>();
            List<List<Integer>> res = new LinkedList<>();
            List<Integer> tmpRes = new LinkedList<>();

            for (TreeNode node : list) {
                tmpRes.add(node.val);
                if (node.left != null) {
                    tmpList.add(node.left);
                }
                if (node.right != null) {
                    tmpList.add(node.right);
                }
            }
            res.add(tmpRes);
            if (!tmpList.isEmpty()) {
                res.addAll(levelOrder(tmpList));
            }
            return res;
        }
    }
```

**法二 层序遍历+双端队列**

`时间复杂度：O(n) 空间复杂度：O(n)`

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) queue.add(root);
        while(!queue.isEmpty()) {
            LinkedList<Integer> tmp = new LinkedList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node = queue.poll();
                if(res.size() % 2 == 0) tmp.addLast(node.val); // 偶数层 -> 队列头部
                else tmp.addFirst(node.val); // 奇数层 -> 队列尾部
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            res.add(tmp);
        }
        return res;
    }
}

```

**法三 层序遍历+双端队列(奇偶逻辑层分离)**

`时间复杂度：O(n) 空间复杂度：O(n)`

- 方法二代码简短、容易实现；但需要判断每个节点的所在层奇偶性，即冗余了 N*N 次判断。
- 通过将奇偶层逻辑拆分，可以消除冗余的判断。

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        Deque<TreeNode> deque = new LinkedList<>();
        List<List<Integer>> res = new ArrayList<>();
        if(root != null) deque.add(root);
        while(!deque.isEmpty()) {
            // 打印奇数层
            List<Integer> tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) {
                // 从左向右打印
                TreeNode node = deque.removeFirst();
                tmp.add(node.val);
                // 先左后右加入下层节点
                if(node.left != null) deque.addLast(node.left);
                if(node.right != null) deque.addLast(node.right);
            }
            res.add(tmp);
            if(deque.isEmpty()) break; // 若为空则提前跳出
            // 打印偶数层
            tmp = new ArrayList<>();
            for(int i = deque.size(); i > 0; i--) {
                // 从右向左打印
                TreeNode node = deque.removeLast();
                tmp.add(node.val);
                // 先右后左加入下层节点
                if(node.right != null) deque.addFirst(node.right);
                if(node.left != null) deque.addFirst(node.left);
            }
            res.add(tmp);
        }
        return res;
    }
}

```

**法四-递归写法**

`时间复杂度：O(n) 空间复杂度：O(n)`

==结果是BFS的形式，但执行过程有点像DFS的递归过程。==

```java
class Solution {
    List<List<Integer>> node=new LinkedList();
    public List<List<Integer>> levelOrder(TreeNode root) {
        lei(root,0);
        return node;
    }
    public void lei(TreeNode root,int k){
        if(root!=null){
            if(node.size()<=k)node.add(new ArrayList());
            if (k % 2 == 0) {
                node.get(k).add(root.val);
            } else {
                node.get(k).add(0, root.val);
            }
            
            lei(root.left,k+1);
            lei(root.right,k+1);
        }
    }
}
```

# **剑指Offer33-二叉搜索树的后序遍历序列

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 `true`，否则返回 `false`。假设输入的数组的任意两个数字都互不相同。

 参考以下这颗二叉搜索树：

```
     5
    / \
   2   6
  / \
 1   3
```

**示例 1：**

```
输入: [1,6,3,2,5]
输出: false
```

**示例 2：**

```
输入: [1,3,2,6,5]
输出: true
```

**法一(自己的)**

`时间复杂度：O(n^2) 空间复杂度；O(n)`

```java
    class Solution {
        public boolean verifyPostorder(int[] postorder) {
            return verifyPostorder(postorder, 0, postorder.length - 1);
        }

        public boolean verifyPostorder(int[] postorder, int start, int end) {
            if (end <= start) {
                return true;
            }
            int endValue = postorder[end];
            if (postorder[end - 1] < endValue) {
                for (int i = start; i < end - 1; i++) {
                    if (postorder[i] > endValue) {
                        return false;
                    }
                }
                return verifyPostorder(postorder, start, end - 1);
            } else {
                for (int i = end - 2; i >= 0; i--) {
                    if (postorder[i] < endValue) {
                        for (int j = i - 1; j >= 0; j--) {
                            if (postorder[j] > endValue) {
                                return false;
                            }
                        }
                        return verifyPostorder(postorder, start, i) && verifyPostorder(postorder, i + 1, end - 1);
                    }
                }
                return verifyPostorder(postorder, start, end - 1);
            }
        }
    }
```

**法二(递归分治)**

`时间复杂度：O(n^2) 空间复杂度；O(n)(最坏) O(logn)(平均)`（类似于快排的平均空间复杂度为O(logn)、最坏空间复杂度为O(n)）

法二思路跟法一思路相同，但代码更加简洁。

时间复杂度 $O(N^2)$： 每次调用 recur(i,j)减去一个根节点，因此递归占用 O(N)；最差情况下（即当树退化为链表），每轮递归都需遍历树所有节点，占用 O(N)。
空间复杂度 $O(N)$ ： 最差情况下（即当树退化为链表），递归深度将达到 N 。

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        return recur(postorder, 0, postorder.length - 1);
    }
    boolean recur(int[] postorder, int i, int j) {
        if(i >= j) return true;
        int p = i;
        while(postorder[p] < postorder[j]) p++;
        int m = p;
        while(postorder[p] > postorder[j]) p++;
        return p == j && recur(postorder, i, m - 1) && recur(postorder, m, j - 1);
    }
}
```

**法三(辅助单调栈)**

`时间复杂度：O(n) 空间复杂度：O(n)`

复杂度分析：

时间复杂度 $O(N)$ ： 遍历 postorder 所有节点，各节点均入栈 / 出栈一次，使用 O(N) 时间。
空间复杂度 $O(N)$： 最差情况下，单调栈 stack存储所有节点，使用 O(N)额外空间。

```java
class Solution {
    public boolean verifyPostorder(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for(int i = postorder.length - 1; i >= 0; i--) {
            if(postorder[i] > root) return false;
            while(!stack.isEmpty() && stack.peek() > postorder[i])
            	root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }
}
```

# **剑指Offer34-二叉树中和为某一值的路径

给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
叶子节点 是指没有子节点的节点。

示例 1：

![img](https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg)

```
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
```

**法一(自己的)**
`时间复杂度：O(n) 空间复杂度：O(logn)最坏O(n)`

```java
class Solution {
        List<List<Integer>> res = new LinkedList<>();
        int sum = 0;

        public List<List<Integer>> pathSum(TreeNode root, int target) {
            findPath(root, target);
            return res;
        }

        public boolean findPath(TreeNode root, int target) {
            if (root != null){
                if (target == root.val && root.left ==null && root.right == null) {
                    res.add(new LinkedList<>());
                    res.get(sum).add(0, root.val);
                    sum++;
                    return true;
                }
                int tmp = sum;
                boolean res1 = findPath(root.left, target - root.val);
                if (res1) {
                    for (int i = tmp; i < sum; i++) {
                        res.get(i).add(0, root.val);
                    }
                }
                tmp = sum;
                boolean res2 = findPath(root.right, target - root.val);
                if (res2) {
                    for (int i = tmp; i < sum; i++) {
                        res.get(i).add(0, root.val);
                    }
                }
                return res1 || res2;
            }
            return false;
        }
    }
```

**法二深度优先+回溯**

`时间复杂度：O(n) 空间复杂度：O(logn)最坏O(n)`

先序遍历+路径记录

法一是自底向顶记录，采用add(0, element)方式；法二是自顶向底记录，采用add(element)方式

> 值得注意的是，记录路径时若直接执行 res.append(path) ，则是将 path 对象加入了 res ；后续 path 改变时， res 中的 path 对象也会随之改变。
>
> 正确做法：res.append(list(path)) ，相当于复制了一个 path 并加入到 res 。

```java
class Solution {
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>(); 
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        recur(root, sum);
        return res;
    }
    void recur(TreeNode root, int tar) {
        if(root == null) return;
        path.add(root.val);
        tar -= root.val;
        if(tar == 0 && root.left == null && root.right == null)
            res.add(new LinkedList(path));
        recur(root.left, tar);
        recur(root.right, tar);
        path.removeLast();
    }
}
```

**法三广度优先**

`时间复杂度：O(n^2) 空间复杂度：O(n)`

```java
class Solution {
    List<List<Integer>> ret = new LinkedList<List<Integer>>();
    Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        if (root == null) {
            return ret;
        }

        Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
        Queue<Integer> queueSum = new LinkedList<Integer>();
        queueNode.offer(root);
        queueSum.offer(0);

        while (!queueNode.isEmpty()) {
            TreeNode node = queueNode.poll();
            int rec = queueSum.poll() + node.val;

            if (node.left == null && node.right == null) {
                if (rec == target) {
                    getPath(node);
                }
            } else {
                if (node.left != null) {
                    map.put(node.left, node);
                    queueNode.offer(node.left);
                    queueSum.offer(rec);
                }
                if (node.right != null) {
                    map.put(node.right, node);
                    queueNode.offer(node.right);
                    queueSum.offer(rec);
                }
            }
        }

        return ret;
    }

    public void getPath(TreeNode node) {
        List<Integer> temp = new LinkedList<Integer>();
        while (node != null) {
            temp.add(node.val);
            node = map.get(node);
        }
        Collections.reverse(temp);
        ret.add(new LinkedList<Integer>(temp));
    }
}
```

# 剑指offer35-复杂链表的复制

请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。

示例 1：

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

**法一(自己的)**

`时间复杂度：O(n) 空间复杂度：O(n)`

```java
    class Solution {
        public Node copyRandomList(Node head) {
            List<Node> list  = new ArrayList<>();
            HashMap<Node, Integer> map = new HashMap<>();
            Node pointer = head;
            int i = 0;
            while(pointer != null) {
                Node n = new Node(pointer.val);
                list.add(n);
                map.put(pointer, i++);
                pointer = pointer.next;
            }
            list.add(null);
            map.put(null, i);
            for (i = 0; i < list.size() - 1; i++) {
                list.get(i).next = list.get(i + 1);
                list.get(i).random = list.get(map.get(head.random));
                head = head.next;
            }
            return list.get(0);
            
        }
    }
```

**法二(哈希表)**

`时间复杂度：O(n) 空间复杂度：O(n)`

- 时间复杂度 O(N) ：两轮遍历链表，使用 O(N) 时间。
- 空间复杂度 O(N) ：哈希表 dic 使用线性大小的额外空间。

法二相比法一少用了一个列表，更加节省空间。

```java
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        Map<Node, Node> map = new HashMap<>();
        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        while(cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        // 4. 构建新链表的 next 和 random 指向
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        // 5. 返回新链表的头节点
        return map.get(head);
    }
}

```

**法三(拼接+拆分)**

`时间复杂度：O(n) 空间复杂度：O(1)`

- 时间复杂度 O(N) ：三轮遍历链表，使用 O(N)*O*(*N*) 时间。
- 空间复杂度 O(1)) ：节点引用变量使用常数大小的额外空间。

```java
class Solution {
    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while(cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null) {
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while(cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }
}
```



# 剑指offer36-二叉搜索树与双向链表

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

 为了让您更好地理解问题，以下面的二叉搜索树为例：

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png)

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

 ![img](https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png)

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。

**法一(自己的)**

`时间复杂度：O(n) 空间复杂度：O(n)`

```java
    class Solution {
        List<Node> path = new ArrayList<>();
        public Node treeToDoublyList(Node root) {
            inOrderTraverse(root);
            int length = path.size();
            if (length == 0) {
                return root;
            }
            if (length == 1) {
                root.left = root;
                root.right = root;
            }
            path.get(0).left = path.get(length - 1);
            path.get(0).right = path.get(1);
            path.get(length - 1).left = path.get(length - 2);
            path.get(length - 1).right = path.get(0);
            for (int i = 1; i < length - 2; i++) {
                path.get(i).left = path.get(i - 1);
                path.get(i).right = path.get(i + 1);
            }
            return path.get(0);
            
        }
        void inOrderTraverse(Node root) {
            if (root == null) {
                return;
            }
            inOrderTraverse(root.left);
            path.add(root);
            inOrderTraverse(root.right);
        }
    }
```

**法二**

`时间复杂度：O(n) 空间复杂度：O(n)`

法二没有使用path全局变量，在中序遍历的过程中，就已经完成循环链表的创建。

```java
class Solution {
    Node pre, head;
    public Node treeToDoublyList(Node root) {
        if(root == null) return null;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }
    void dfs(Node cur) {
        if(cur == null) return;
        dfs(cur.left);
        if(pre != null) pre.right = cur;
        else head = cur;
        cur.left = pre;
        pre = cur;
        dfs(cur.right);
    }
}
```

# 剑指offer37-序列化二叉树

请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

下面三种方法：

`时间复杂度：O(n) 空间复杂度：O(n)`

**法一(自己的)**

序列化与反序列化采用递归广度搜索实现。

```java
public class Codec {
        // Encodes a tree to a single string.
        public Integer[] serialize(TreeNode root) {
            List<Integer> list = new ArrayList<>();
            if (root == null) {
                return new Integer[]{};
            }
            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            while(!q.isEmpty()) {
                TreeNode node = q.poll();
                if (node == null) {
                    list.add(null);
                    continue;
                }
                list.add(node.val);
                q.add(node.left);
                q.add(node.right);
            }   
            /* 可以删除掉
            for (int i = list.size() - 1; i >= 0; i--) {
                if (list.get(i) == null) {
                    list.remove(i);
                } else {
                    break;
                }
            }
            */
            Integer[] tmp = new Integer[list.size()];
            return list.toArray(tmp);
        }
        // Decodes your encoded data to tree.
        public TreeNode deserialize(Integer[] data) {
            TreeNode head = null;
            int length = data.length;
            if (length == 0) {
                return head;
            }
            Queue<TreeNode> q = new LinkedList<>();
            head = new TreeNode(data[0]);
            q.add(head);
            int i = 1;
            while(!q.isEmpty()) {
                TreeNode node = q.poll();
                if (i < length) {
                    if (data[i] != null) {
                        node.left = new TreeNode(data[i]);
                        q.add(node.left);
                    } 
                    i++;
                }
                if (i < length) {
                    if (data[i] != null) {
                        node.right = new TreeNode(data[i]);
                        q.add(node.right);
                    } 
                    i++;
                }
            }
            return head;
        }
    }
```

**法二、层序遍历DFS**

实际执行法一所用时间比法二少。

```java
public class Codec {
    public String serialize(TreeNode root) {
        if(root == null) return "[]";
        StringBuilder res = new StringBuilder("[");
        Queue<TreeNode> queue = new LinkedList<>() {{ add(root); }};
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node != null) {
                res.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            }
            else res.append("null,");
        }
        res.deleteCharAt(res.length() - 1);
        res.append("]");
        return res.toString();
    }

    public TreeNode deserialize(String data) {
        if(data.equals("[]")) return null;
        String[] vals = data.substring(1, data.length() - 1).split(",");
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> queue = new LinkedList<>() {{ add(root); }};
        int i = 1;
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(!vals[i].equals("null")) {
                node.left = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.left);
            }
            i++;
            if(!vals[i].equals("null")) {
                node.right = new TreeNode(Integer.parseInt(vals[i]));
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
}
```

**法三、DFS**

可以采用StringBuilder实现。

```java
public class Codec {
    public String serialize(TreeNode root) {
        return rserialize(root, "");
    }
  
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new LinkedList<String>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }

    public String rserialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += str.valueOf(root.val) + ",";
            str = rserialize(root.left, str);
            str = rserialize(root.right, str);
        }
        return str;
    }
  
    public TreeNode rdeserialize(List<String> dataList) {
        if (dataList.get(0).equals("None")) {
            dataList.remove(0);
            return null;
        }
  
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0)));
        dataList.remove(0);
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);
    
        return root;
    }
}

```

# **剑指offer38-字符串的排列

输入一个字符串，打印出该字符串中字符的所有排列。

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

**示例:**

```
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```

**法一(自己的)**

```java
class Solution {
        public String[] permutation(String s) {
            if (s.length() <= 1) {
                return new String[]{s};
            }
            HashSet<String> set = new HashSet<>();
            String[] post = permutation(s.substring(1));
            int length = s.length();
            String first = s.substring(0, 1);
            for (String str : post) {
                for (int i = 0; i < length - 1; i++) {
                    set.add(str.substring(0, i) + first + str.substring(i, length - 1));
                }
                set.add(str + first);
            }
            String[] res = new String[set.size()];
            return set.toArray(res);
        }
    }
```



**法二、回溯**

时间复杂度 $O(N!N)$： N为字符串 s 的长度；时间复杂度和字符串排列的方案数成线性关系，方案数为$ N \times (N-1) \times (N-2) … \times  $，即复杂度为 $O(N!) $；字符串拼接操作 join() 使用 $O(N)$ ；因此总体时间复杂度为 $O(N!N)$。
空间复杂度 $O(N^2)$： 全排列的递归深度为 N ，系统累计使用栈空间大小为 $O(N)$ ；递归中辅助 Set 累计存储的字符数量最多为 $N + (N-1) + ... + 2 + 1 = (N+1)N/2 $，即占用 $O(N^2)$的额外空间。

执行速度比法一快得多。

**终止条件**： 当 x = len(c) - 1 时，代表所有位已固定（最后一位只有 1 种情况），则将当前组合 c 转化为字符串并加入 res ，并返回；
**递推参数**： 当前固定位 x ；
**递推工作**： 初始化一个 Set ，用于排除重复的字符；将第 x 位字符与 i \in∈ [x, len(c)] 字符分别交换，并进入下层递归；
**剪枝**： 若 c[i] 在 Set 中，代表其是重复字符，因此 “剪枝” ；
将 c[i] 加入 Set ，以便之后遇到重复字符时剪枝；
**固定字符**： 将字符 c[i] 和 c[x] 交换，即固定 c[i] 为当前位字符；
**开启下层递归**： 调用 dfs(x + 1) ，即开始固定第 x + 1 个字符；
**还原交换**： 将字符 c[i] 和 c[x] 交换（还原之前的交换）；

```java
class Solution {
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }
}
```

# 剑指Offer39-数组中出现次数超过一半的数字

数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

**法一**

`时间复杂度：O(n) 空间复杂度:O(n)`

```java
class Solution {
        public int majorityElement(int[] nums) {
            HashMap<Integer, Integer> map = new HashMap<>();
            if (nums.length <= 2) return nums[0];
            int halfLength = nums.length / 2;
            for (int i : nums) {
                if (map.containsKey(i)) {
                    int count = map.get(i) + 1;
                    if (count > halfLength) {
                        return i;
                    }
                    map.put(i, count);
                } else {
                    map.put(i, 1);
                }
            }
            return -1;
        }
    }
```

**法二 摩尔投票法（最佳解法）**

`时间复杂度：O(n) 空间复杂度:O(1)`

设输入数组 nums 的众数为 x ，数组长度为 n 。

推论一： 若记 众数 的票数为 +1 ，非众数 的票数为 -1 ，则一定有所有数字的 票数和 >0 。

推论二： 若数组的前 a 个数字的 票数和 =0 ，则 数组剩余 (n-a)个数字的 票数和一定仍 >0 ，即后 (n-a)个数字的 众数仍为 x 。

```java
class Solution {
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for(int num : nums){
            if(votes == 0) x = num;
            votes += num == x ? 1 : -1;
        }
        return x;
    }
}
```

**法三 数组排序法**

 将数组 nums排序，**数组中点的元素** 一定为众数。

`时间复杂度：看排序算法的类型 空间复杂度:看排序算法的类型`

# 剑指offer40-最小的k个数

**法一(排序)**

时间复杂度 $O(NlogN) $： 库函数、快排等排序算法的平均时间复杂度为 $O(NlogN)$ 。
空间复杂度$ O(N)$ ： 快速排序的递归深度最好（平均）为 $O(logN)$ ，最差情况（即输入数组完全倒序）为$O(N)$。

自己的：

```java
    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            int[] res = sort(arr);
            return Arrays.copyOf(res, k);
        }

        public int[] sort(int[] sourceArray) {
            // 对 arr 进行拷贝，不改变参数内容
            int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
    
            return quickSort(arr, 0, arr.length - 1);
        }
    
        private int[] quickSort(int[] arr, int left, int right) {
            if (left < right) {
                int partitionIndex = partition(arr, left, right);
                quickSort(arr, left, partitionIndex - 1);
                quickSort(arr, partitionIndex + 1, right);
            }
            return arr;
        }
    
        private int partition(int[] arr, int left, int right) {
            // 设定基准值（pivot）
            int pivot = left;
            int index = pivot + 1;
            for (int i = index; i <= right; i++) {
                if (arr[i] < arr[pivot]) {
                    swap(arr, i, index);
                    index++;
                }
            }
            swap(arr, pivot, index - 1);
            return index - 1;
        }
    
        private void swap(int[] arr, int i, int j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }
```

别人的

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        quickSort(arr, 0, arr.length - 1);
        return Arrays.copyOf(arr, k);
    }
    private void quickSort(int[] arr, int l, int r) {
        // 子数组长度为 1 时终止递归
        if (l >= r) return;
        // 哨兵划分操作（以 arr[l] 作为基准数）
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        // 递归左（右）子数组执行哨兵划分
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

```

**法二（基于快速排序的分组）**

> 本方法优化时间复杂度的本质是通过判断舍去了不必要的递归（哨兵划分）。

* 时间复杂度 $O(N)$： 其中 N为数组元素数量；对于长度为 N 的数组执行哨兵划分操作的时间复杂度为 $O(N)$ ；每轮哨兵划分后根据 k和 i的大小关系选择递归，由于 i 分布的随机性，则向下递归子数组的平均长度为 $\frac{N}{2} $；因此平均情况下，哨兵划分操作一共有 $N + \frac{N}{2} + \frac{N}{4} + ... + \frac{N}{N} = \frac{N - \frac{1}{2}}{1 - \frac{1}{2}} = 2N - 1$（等比数列求和），即总体时间复杂度为 $O(N)$ 。
* 空间复杂度 $O(\log N) $： 划分函数的平均递归深度为 $O(\log N)$ 。

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k >= arr.length) return arr;
        return quickSort(arr, k, 0, arr.length - 1);
    }
    private int[] quickSort(int[] arr, int k, int l, int r) {
        int i = l, j = r;
        while (i < j) {
            while (i < j && arr[j] >= arr[l]) j--;
            while (i < j && arr[i] <= arr[l]) i++;
            swap(arr, i, j);
        }
        swap(arr, i, l);
        if (i > k) return quickSort(arr, k, l, i - 1);
        if (i < k) return quickSort(arr, k, i + 1, r);
        return Arrays.copyOf(arr, k);
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
```

**法三（堆）**

我们用一个大根堆实时维护数组的前 k小值。首先将前 k 个数插入大根堆中，随后从第 k+1 个数开始遍历，如果当前遍历到的数比大根堆的堆顶的数要小，就把堆顶的数弹出，再插入当前遍历到的数。最后将大根堆里的数存入数组返回即可。

时间复杂度：$O(n\log k)$，其中 n 是数组 arr 的长度。由于大根堆实时维护前 k小值，所以插入删除都是 $O(\log k)$ 的时间复杂度，最坏情况下数组里 n个数都会插入，所以一共需要 $O(n\log k)$ 的时间复杂度。

空间复杂度：$O(k)$，因为大根堆里最多 k 个数。



```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        int[] vec = new int[k];
        if (k == 0) { // 排除 0 的情况
            return vec;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(new Comparator<Integer>() {
            public int compare(Integer num1, Integer num2) {
                return num2 - num1;
            }
        });
        for (int i = 0; i < k; ++i) {
            queue.offer(arr[i]);
        }
        for (int i = k; i < arr.length; ++i) {
            if (queue.peek() > arr[i]) {
                queue.poll();
                queue.offer(arr[i]);
            }
        }
        for (int i = 0; i < k; ++i) {
            vec[i] = queue.poll();
        }
        return vec;
    }
}
```



**法四（数据范围有限直接计数排序）**

`时间复杂度：O(n) 空间复杂度：O(m) m为数组长度`

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 统计每个数字出现的次数
        int[] counter = new int[10001];
        for (int num: arr) {
            counter[num]++;
        }
        // 根据counter数组从头找出k个数作为返回结果
        int[] res = new int[k];
        int idx = 0;
        for (int num = 0; num < counter.length; num++) {
            while (counter[num]-- > 0 && idx < k) {
                res[idx++] = num;
            }
            if (idx == k) {
                break;
            }
        }
        return res;
    }
}
```

# 剑指offer41-数据流中的中位数

如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

例如，

[2,3,4] 的中位数是 3

[2,3] 的中位数是 (2 + 3) / 2 = 2.5

设计一个支持以下两种操作的数据结构：

void addNum(int num) - 从数据流中添加一个整数到数据结构中。
double findMedian() - 返回目前所有元素的中位数。
示例 1：

```
输入：["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]
[[],[1],[2],[],[3],[]]
输出：[null,null,null,1.50000,null,2.00000]示例 2：
```

示例2：

```java
输入：["MedianFinder","addNum","findMedian","addNum","findMedian"]
[[],[2],[],[3],[]]
输出：[null,null,2.00000,null,2.50000]
```

**法一(自己的 暴力排序法)**

`插入数据：O(n) 查找中位数：O(1) 空间复杂度：O(n)`

```java
    class MedianFinder {

        /** initialize your data structure here. */
        private List<Integer> list;
        public MedianFinder() {
            list = new ArrayList<>(); 
        }
        
        public void addNum(int num) {
            int i ;
            for (i = list.size() - 1; i >= 0; i--) {
                if (list.get(i) < num) {
                    break;
                }
            }
            list.add(i + 1, num);
        }
        
        public double findMedian() {
            int length = list.size();
            if (length % 2 == 1) {
                return list.get(length / 2);
            } else {
                return (list.get(length / 2 - 1) + list.get(length / 2)) / 2.0;
            }
        }
    }
```

**法二(堆法)**

* 时间复杂度：
  查找中位数 $O(1)$ ： 获取堆顶元素使用$ O(1)$时间；
  添加数字 $O(\log N)$： 堆的插入和弹出操作使用$ O(\log N)$ 时间。

* 空间复杂度 $O(N)$ ： 其中 N为数据流中的元素数量，小顶堆 A 和大顶堆 B 最多同时保存 N 个元素。

```java
class MedianFinder {
    Queue<Integer> A, B;
    public MedianFinder() {
        A = new PriorityQueue<>(); // 小顶堆，保存较大的一半
        B = new PriorityQueue<>((x, y) -> (y - x)); // 大顶堆，保存较小的一半
    }
    public void addNum(int num) {
        if(A.size() != B.size()) {
            A.add(num);
            B.add(A.poll());
        } else {
            B.add(num);
            A.add(B.poll());
        }
    }
    public double findMedian() {
        return A.size() != B.size() ? A.peek() : (A.peek() + B.peek()) / 2.0;
    }
}
```

# *剑指offer42-连续子数组的最大和

输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。

要求时间复杂度为O(n)。

**示例1:**

```
输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

**法一(动态规划)**

`时间复杂度：O(n) 空间复杂度：O(1)【直接在原数组上进行操作】`

```java
class Solution {
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }
}
```

**法二(分治)**

时间复杂度：假设我们把递归的过程看作是一颗二叉树的先序遍历，那么这颗二叉树的深度的渐进上界为 $O(\log n)$，这里的总时间相当于遍历这颗二叉树的所有节点，故总时间的渐进上界是 $O(\sum_{i=1}^{\log n} 2^{i-1})=O(n)$，故渐进时间复杂度为 $O(n)$。
空间复杂度：递归会使用 $O(\log n)$ 的栈空间，故渐进空间复杂度为 $O(\log n)$。

```java
class Solution {
    public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }
}
```

「方法二」相较于「方法一」来说，时间复杂度相同，但是因为使用了递归，并且维护了四个信息的结构体，运行的时间略长，空间复杂度也不如方法一优秀，而且难以理解。那么这种方法存在的意义是什么呢？

对于这道题而言，确实是如此的。但是仔细观察「方法二」，它不仅可以解决区间 [0, n-1]，还可以用于解决任意的子区间 [l,r]的问题。如果我们把 [0, n-1] 分治下去出现的所有子区间的信息都用堆式存储的方式记忆化下来，即建成一颗真正的树之后，我们就可以在 $O(\log n)$的时间内求到任意区间内的答案，我们甚至可以修改序列中的值，做一些简单的维护，之后仍然可以在 $O(\log n)$ 的时间内求到任意区间内的答案，对于大规模查询的情况下，这种方法的优势便体现了出来。这棵树就是上文提及的一种神奇的数据结构——线段树。

**法三(暴力遍历法)**

`时间复杂度：O(n^2) 空间复杂度：O(1)`

直接遍历各种可能性

# 剑指Offer43-1~n整数中1出现的次数

输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。

例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。

**法一、自己的**

`时间复杂度： 空间复杂度：`

```java
    class Solution {
        public int countDigitOne(int n) {
            int count = getCount(n);
            return countDigitOne(n, count);
        }
        private int countDigitOne(int n, int count) {
            if (n >= 1 && n < 10) {
                return 1;
            }
            if (n == 0) {
                return 0;
            }
            int mod  = (int)Math.pow(10, count - 1);
            int firstDigit = n / mod;
            int remainder = n % mod;
            int count1 = count - 1;
            int mod1 = mod;
            while (remainder < mod1 / 10) {
                count1--;
                mod1 /= 10;
            }
            if (firstDigit == 1) {
                return remainder + 1 +  countDigitOne(remainder, count1) + countDigitOne(mod- 1, count - 1);
            } else {
                return mod + countDigitOne(remainder, count - 1) + firstDigit * countDigitOne(mod - 1, count - 1);
            }
            
        }
        private int getCount(int n) {
            int count = 0;
            while (n > 0) {
                n /= 10;
                count++;
            }
            return count;
        }
    }
```

前者相比后者理论上消耗时间更少，因为必须要每次计算总位数。

```java
    class Solution {
        public int countDigitOne(int n) {
            if (n >= 1 && n < 10) {
                return 1;
            }
            if (n == 0) {
                return 0;
            }
            int count = getCount(n);
            int mod  = (int)Math.pow(10, count - 1);
            int firstDigit = n / mod;
            int remainder = n % mod;
            int count1 =getCount(remainder);
            if (firstDigit == 1) {
                return remainder + 1 +  countDigitOne(remainder) + countDigitOne(mod- 1);
            } else {
                return mod + countDigitOne(remainder) + firstDigit * countDigitOne(mod - 1);
            }
            
        }
        private int getCount(int n) {
            int count = 0;
            while (n > 0) {
                n /= 10;
                count++;
            }
            return count;
        }
    }
```

**法二、**

时间复杂度$ O(\log n)$) ： 循环内的计算操作使用 $O(1)$ 时间；循环次数为数字 n的位数，即 $\log_{10}{n}$ ，因此循环使用$ O(\log n)$时间。
空间复杂度 $O(1)$ ： 几个变量使用常数大小的额外空间。

```java
class Solution {
    public int countDigitOne(int n) {
        int digit = 1, res = 0;
        int high = n / 10, cur = n % 10, low = 0;
        while(high != 0 || cur != 0) {
            if(cur == 0) res += high * digit;
            else if(cur == 1) res += high * digit + low + 1;
            else res += (high + 1) * digit;
            low += cur * digit;
            cur = high % 10;
            high /= 10;
            digit *= 10;
        }
        return res;
    }
}

```

**法三**

`时间复杂度：O(logn) 空间复杂度：O(1)`

```java
class Solution {
    public int countDigitOne(int n) {
        // mulk 表示 10^k
        // 在下面的代码中，可以发现 k 并没有被直接使用到（都是使用 10^k）
        // 但为了让代码看起来更加直观，这里保留了 k
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (n / (mulk * 10)) * mulk + Math.min(Math.max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }
}
```

# 剑指offer44-数字序列中某一位的数字

数字以0123456789101112131415…的格式序列化到一个字符序列中。在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。

请写一个函数，求任意第n位对应的数字。

**法一 own**

`时间复杂度:O(logn) 空间复杂度：O(1)`

```java
static class Solution {
    public int findNthDigit(int n) {
        if (n < 10) {
            return n;
        }
        long sum_end = 0;
        int digit = 0;
        while (sum_end < n) {
            digit++;
            sum_end = (long)(Math.pow(10, digit) * digit - (Math.pow(10, digit) - 1) /9);
        }
        digit--;
        long sum_start = (long)(Math.pow(10, digit) * digit - (Math.pow(10, digit) - 1) /9);
        long remain = n - sum_start - 1;
        digit ++;
        long number = (long)Math.pow(10, digit - 1) + remain / digit;
        int order = (int)(remain % digit);
        for (int i = 1; i < digit - order; i++) {
            number /= 10;
        }
        return (int)(number % 10);
    }
}
```

**法二 leetcode**

`时间复杂度：O(logn) 空间复杂度：O(logn)`

法一用公式直接求解了和，法二隐式地求解了和，法二相比法二更加符合编程的思想，因为法一需要人为地去推倒求和公式$S_n=10^n\times n - \frac{10^n-1}{9}\quad n\ \text{means digits}$；法二中digit和count的初始值设置的比法一的更好。

```java
class Solution {
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            digit += 1;
            start *= 10;
            count = digit * start * 9;
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3. 求解第几位的巧妙方式，但会占用更多的内存空间
    }
}

```

# 剑指offer45-把数组排成最小的数

输入一个非负整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。

`时间复杂度：O(nlogn) 空间复杂度：O(n)`

**法一自己排序**

```java
class Solution {
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        quickSort(strs, 0, strs.length - 1);
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }
    void quickSort(String[] strs, int l, int r) {
        if(l >= r) return;
        int i = l, j = r;
        String tmp = strs[i];
        while(i < j) {
            while((strs[j] + strs[l]).compareTo(strs[l] + strs[j]) >= 0 && i < j) j--;
            while((strs[i] + strs[l]).compareTo(strs[l] + strs[i]) <= 0 && i < j) i++;
            tmp = strs[i];
            strs[i] = strs[j];
            strs[j] = tmp;
        }
        strs[i] = strs[l];
        strs[l] = tmp;
        quickSort(strs, l, i - 1);
        quickSort(strs, i + 1, r);
    }
}

```

**法二Arrays.sort**

```java
class Solution {
    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();
    }
}
```

# 剑指Offer46-把数字翻译成字符串

给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。

 动态规划法

`时间复杂度：O(logn) 空间复杂度：O(logn)`

**1 own递归备忘录法**

```java
class Solution {
    int[] memo;

    public int translateNum(int num) {
        String numString = String.valueOf(num);
        memo = new int[numString.length() + 1];
        return translateNumString(numString);
    }

    public int translateNumString(String numString) {
        int l = numString.length();
        if (l <= 1) {
            return 1;
        }
        String twoNum = numString.substring(0, 2);
        if (memo[l] == 0) {
            if (twoNum.compareTo("25") <= 0 && twoNum.substring(0, 1).compareTo("0") != 0) {
                memo[l] = translateNumString(numString.substring(2)) + translateNumString(numString.substring(1));
            } else {
                memo[l] = translateNumString(numString.substring(1));
            }
        }
        return memo[l];
    }
}
```

**2 迭代**

从左至右遍历

```java
class Solution {
    public int translateNum(int num) {
        String src = String.valueOf(num);
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < src.length(); ++i) {
            p = q; 
            q = r; 
            r = 0;
            r += q;
            if (i == 0) {
                continue;
            }
            String pre = src.substring(i - 1, i + 1);
            if (pre.compareTo("25") <= 0 && pre.compareTo("10") >= 0) {
                r += p;
            }
        }
        return r;
    }
}

```



```java
    public static int translateNum(int num) {
        char[] ch = String.valueOf(num).toCharArray();
        int len = ch.length;
        int[] dp = new int[len + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= len; i++){
            int n = (ch[i - 2] - '0') * 10 + (ch[i - 1] - '0');
            if(n >= 10 && n <= 25){
                dp[i] = dp[i - 1] + dp[i - 2];
            }else{
                dp[i] = dp[i - 1];
            }
        }
        return dp[len];
    }
```

```java
class Solution {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int a = 1, b = 1;
        for(int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i - 2, i);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }
}
```

从右至左遍历

```java
class Solution {
    public int translateNum(int num) {
        String s = String.valueOf(num);
        int a = 1, b = 1;
        for(int i = s.length() - 2; i > -1; i--) {
            String tmp = s.substring(i, i + 2);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }
}
```

不采用字符串的形式

```java
class Solution {
    public int translateNum(int num) {
        int a = 1, b = 1, x, y = num % 10;
        while(num != 0) {
            num /= 10;
            x = num % 10;
            int tmp = 10 * x + y;
            int c = (tmp >= 10 && tmp <= 25) ? a + b : a;
            b = a;
            a = c;
            y = x;
        }
        return a;
    }
}
```

# 剑指Offer47-礼物的最大价值

从棋盘的左上角开始拿格子里的礼物，并每次 **向右** 或者 **向下** 移动一格、直到到达棋盘的右下角。

动态规划

`时间复杂度 O(MN) 空间复杂度 O(1)`

```java
class Solution {
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 1; i < m; i++) {
            grid[i][0] = grid[i - 1][0] + grid[i][0];
        }
        for (int i = 1; i < n; i++) {
            grid[0][i] = grid[0][i - 1] + grid[0][i];
        }
        for (int i = 1; i < m; i++)
            for (int j = 1; j < n; j++) {
                grid[i][j] = Math.max(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        return grid[m - 1][n - 1];
    }
}
```

# 剑指Offer48-最长不含重复字符的子字符串

请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。

**法一-own**

hashset+队列

`时间复杂度：O(n) 空间复杂度：最大有O(M) M表示字符集的大小`

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> hs = new HashSet<>();
        Queue<Character> q = new LinkedList<>();
        int maxLength = 0, nowLength = 0;
        char[] cs = s.toCharArray();
        for (char c : cs) {
            if (!hs.contains(c)) {
                hs.add(c);
                nowLength++;
            } else {
                maxLength = Math.max(maxLength, nowLength);
                while (q.peek() != c) {
                    nowLength--;
                    hs.remove(q.peek());
                    q.poll();
                }
                q.poll();
            }
            q.offer(c);
        }
        return Math.max(maxLength, nowLength);
    }
}
```

**法二-滑动窗口法**

hashset+双指针

`时间复杂度：O(n) 空间复杂度：同法一`   

 ==法二比法一少用了一个队列数据结构，更节省空间；法二的长度计算直接通过双指针求解即可，不需要像法一还需要额外设置变量存储长度==

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 哈希集合，记录每个字符是否出现过
        Set<Character> occ = new HashSet<Character>();
        int n = s.length();
        // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
        int rk = 0, ans = 0;
        for (int i = 0; i < n; ++i) {
            if (i != 0) {
                // 左指针向右移动一格，移除一个字符
                occ.remove(s.charAt(i - 1));
            }
            while (rk < n && !occ.contains(s.charAt(rk))) {
                // 不断地移动右指针
                occ.add(s.charAt(rk));
                ++rk;
            }
            // 第 i 到 rk 个字符是一个极长的无重复字符子串
            ans = Math.max(ans, rk - i);
        }
        return ans;
    }
}

```

下面写法不行（要想固定右端写法，参考法三和法五）

```java
class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n <= 1) {
            return n;
        }
        Set<Character> visited = new HashSet<>();
        int lk = 0, max = 1;
        visited.add(s.charAt(0));
        for (int i = 1; i < n; i++) {
            char c = s.charAt(i);
            if (visited.contains(c)) {
                while (lk < i && s.charAt(lk) != c) {
                    visited.remove(s.charAt(lk));
                    lk++;
                }
            } 
            visited.add(c);
            max = Math.max(max, i - lk);
        }
        return max;
    }
}
```



法三-动态规划+哈希表**

`时间复杂度：O(n) 空间复杂度：O(1)`

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0;
        for(int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); // 获取索引 i
            dic.put(s.charAt(j), j); // 更新哈希表
            tmp = tmp < j - i ? tmp + 1 : j - i; // dp[j - 1] -> dp[j]
            res = Math.max(res, tmp); // max(dp[j - 1], dp[j])
        }
        return res;
    }
}
```

**法四-动态规划+线性遍历**

`时间复杂度：O(n^2) 空间复杂度：O(1)`

```java
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        res = tmp = i = 0
        for j in range(len(s)):
            i = j - 1
            while i >= 0 and s[i] != s[j]: i -= 1 # 线性查找 i
            tmp = tmp + 1 if tmp < j - i else j - i # dp[j - 1] -> dp[j]
            res = max(res, tmp) # max(dp[j - 1], dp[j])
        return res
```

**法五-双指针+哈希表**

`时间复杂度：O(n) 空间复杂度：O(1)`

==法五类似于法二的滑动窗口法，只不过法二是固定左端，法五是固定右端==

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int i = -1, res = 0;
        for(int j = 0; j < s.length(); j++) {
            if(dic.containsKey(s.charAt(j)))
                i = Math.max(i, dic.get(s.charAt(j))); // 更新左指针 i
            dic.put(s.charAt(j), j); // 哈希表记录
            res = Math.max(res, j - i); // 更新结果
        }
        return res;
    }
}
```

# *剑指Offer49-丑数

我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。

**1 动态规划**

`时间复杂度：O(n) 空间复杂度：O(n)`

```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] dp = new int[n];
        int a = 0, b = 0, c = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            int m = dp[a] * 2, k = dp[b] * 3, p = dp[c] * 5;
            dp[i] = Math.min(Math.min(m, k), p);
            if (dp[i] == m) {
                a++;
            }
            if (dp[i] == k) {
                b++;
            }
            if (dp[i] == p) {
                c++;
            }
        }
        return dp[n - 1];
    }
}
```

**2 最小堆**

`时间复杂度：O(nlogn) 空间复杂度：O(n)`

```java
class Solution {
    public int nthUglyNumber(int n) {
        int[] factors = {2, 3, 5};
        Set<Long> seen = new HashSet<Long>();
        PriorityQueue<Long> heap = new PriorityQueue<Long>();
        seen.add(1L);
        heap.offer(1L);
        int ugly = 0;
        for (int i = 0; i < n; i++) {
            long curr = heap.poll();
            ugly = (int) curr;
            for (int factor : factors) {
                long next = curr * factor;
                if (seen.add(next)) {
                    heap.offer(next);
                }
            }
        }
        return ugly;
    }
}
```

# 剑指Offer50-第一个只出现一次的字符

在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。

**1 own**

`时间复杂度 O(n) 空间复杂度 O(1)(26个字母，最多O(26))`

```java
class Solution {
    public char firstUniqChar(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        int L = s.length();
        for (int i = 0; i < L; i++) {
            char c = s.charAt(i);
            if (hm.containsKey(c)) {
                hm.put(c, L);
            } else {
                hm.put(c, i);
            }
        }
        int min = L;
        char uniqueChar = ' ';
        for (Map.Entry<Character, Integer> entry : hm.entrySet()) {
            if (entry.getValue() < min) {
                min = entry.getValue();
                uniqueChar = entry.getKey();
            }
        }
        return uniqueChar;
    }
}
```

**2 哈希表**

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public char firstUniqChar(String s) {
        HashMap<Character, Boolean> dic = new HashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc)
            dic.put(c, !dic.containsKey(c));
        for(char c : sc)
            if(dic.get(c)) return c;
        return ' ';
    }
}
```

**3 有序哈希表**

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public char firstUniqChar(String s) {
        Map<Character, Boolean> dic = new LinkedHashMap<>();
        char[] sc = s.toCharArray();
        for(char c : sc)
            dic.put(c, !dic.containsKey(c));
        for(Map.Entry<Character, Boolean> d : dic.entrySet()){
           if(d.getValue()) return d.getKey();
        }
        return ' ';
    }
}
```

**4 队列+哈希表**

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public char firstUniqChar(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        Queue<Pair> queue = new LinkedList<Pair>();
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            char ch = s.charAt(i);
            if (!position.containsKey(ch)) {
                position.put(ch, i);
                queue.offer(new Pair(ch, i));
            } else {
                position.put(ch, -1);
                while (!queue.isEmpty() && position.get(queue.peek().ch) == -1) {
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? ' ' : queue.poll().ch;
    }

    class Pair {
        char ch;
        int pos;

        Pair(char ch, int pos) {
            this.ch = ch;
            this.pos = pos;
        }
    }
}
```

# 剑指Offer51-数组中的逆序对

在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。

**1 own**

归并排序，利用分治思想

`时间复杂度 O(nlogn) 空间复杂度 O(n)`

```java
class Solution {
    public int reversePairs(int[] nums) {
        return mergeSort(nums, 0, nums.length);
    }

    public int mergeSort(int[] nums, int left, int right) {
        int mid = (left + right) / 2;
        if (right -left <= 1) {
            return 0;
        }
        return mergeSort(nums, left, mid) + mergeSort(nums, mid, right) + merge(nums, left, mid, right);
    }

    public int merge(int[] nums, int left, int mid, int right) {
        int i = left, j = mid;
        int[] temp = new int[right - left];
        int pos = 0, res = 0;;
        while (i < mid && j < right) {
            if (nums[i] <= nums[j]) {
                temp[pos++] = nums[i++];
            } else {
                temp[pos++] = nums[j++];
                res += mid - i;
            }
        }
        while (i < mid) {
            temp[pos++] = nums[i++];
        }
        while (j < right) {
            temp[pos++] = nums[j++];
        }
        System.arraycopy(temp, 0, nums, left, right - left);
        return res;
    }
}
```

**2 归并排序**

`时间复杂度 O(nlogn) 空间复杂度 O(n)`

```java
class Solution {
    int[] nums, tmp;
    public int reversePairs(int[] nums) {
        this.nums = nums;
        tmp = new int[nums.length];
        return mergeSort(0, nums.length - 1);
    }
    private int mergeSort(int l, int r) {
        // 终止条件
        if (l >= r) return 0;
        // 递归划分
        int m = (l + r) / 2;
        int res = mergeSort(l, m) + mergeSort(m + 1, r);
        // 合并阶段
        int i = l, j = m + 1;
        for (int k = l; k <= r; k++)
            tmp[k] = nums[k];
        for (int k = l; k <= r; k++) {
            if (i == m + 1)
                nums[k] = tmp[j++];
            else if (j == r + 1 || tmp[i] <= tmp[j])
                nums[k] = tmp[i++];
            else {
                nums[k] = tmp[j++];
                res += m - i + 1; // 统计逆序对
            }
        }
        return res;
    }
}
```

**3 离散化树状数组**

[树状数组](https://zhuanlan.zhihu.com/p/93795692, https://www.bilibili.com/video/BV1pE41197Qj)

`时间复杂度 O(nlogn) 空间复杂度 O(n)`

```java
class Solution {
    public int reversePairs(int[] nums) {
        int n = nums.length;
        int[] tmp = new int[n];
        System.arraycopy(nums, 0, tmp, 0, n);
        // 离散化
        Arrays.sort(tmp);
        for (int i = 0; i < n; ++i) {
            nums[i] = Arrays.binarySearch(tmp, nums[i]) + 1;
        }
        // 树状数组统计逆序对
        BIT bit = new BIT(n);
        int ans = 0;
        for (int i = n - 1; i >= 0; --i) {
            ans += bit.query(nums[i] - 1);
            bit.update(nums[i]);
        }
        return ans;
    }
}

class BIT {
    private int[] tree;
    private int n;

    public BIT(int n) {
        this.n = n;
        this.tree = new int[n + 1];
    }

    public static int lowbit(int x) {
        return x & (-x);
    }

    public int query(int x) {
        int ret = 0;
        while (x != 0) {
            ret += tree[x];
            x -= lowbit(x);
        }
        return ret;
    }

    public void update(int x) {
        while (x <= n) {
            ++tree[x];
            x += lowbit(x);
        }
    }
}
```

# 剑指Offer52-两个链表的首个公共节点

输入两个链表，找出它们的第一个公共节点。

**1 哈希集合 own**

`时间复杂度 O(m+n) 空间复杂度 O(m)`

```java
class Solution1 {
        ListNode getIntersectionNode(ListNode headA, ListNode headB) {
            Set<ListNode> collection = new HashSet<>(); //collection用visited更具有可读性
            for (ListNode now = headA; now != null; now = now.next) {
                collection.add(now);
            }
            for (ListNode now = headB; now != null; now = now.next) {
                if (collection.contains(now)) {
                    return now;
                }
            }
            return null;
        }
    }
```

**2 双指针**

`时间复杂度 O(m+n) 空间复杂度 O(1)`

```java
class Solution:
    def getIntersectionNode(self, headA: ListNode, headB: ListNode) -> ListNode:
        A, B = headA, headB
        while A != B:
            A = A.next if A else headB
            B = B.next if B else headA
        return A
```

# 剑指Offer53I-在排序数组中查找数字I

统计一个数字在排序数组中出现的次数。

`时间复杂度 O(logn) 空间复杂度 O(1)`

**1 own**

一次二分

如果`right`是开区间，则`while`的判断条件为`left < right`；
如果`right`是闭区间，则`while`的判断条件为`left <= right`。

```java
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length, mid = 0;
        while (left < right) {
            mid = (left + right) / 2;
            if (nums[mid] == target) {
                break;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        if (nums.length == 0 ||  nums[mid] != target) {
            return 0;
        } 
        for (left = mid - 1; left >=0; left--) {
            if (nums[left] != target) {
                break;
            }
        }
        for (right = mid + 1; right < nums.length; right++) {
            if (nums[right] != target) {
                break;
            }
        }
        return right - left - 1;
    }
}
```

**2 二次二分(1)**

```java
class Solution {
    public int search(int[] nums, int target) {
        // 搜索右边界 right
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] <= target) i = m + 1;
            else j = m - 1;
        }
        int right = i;
        // 若数组中无 target ，则提前返回
        if(j >= 0 && nums[j] != target) return 0;
        // 搜索左边界 right
        i = 0; j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] < target) i = m + 1;
            else j = m - 1;
        }
        int left = j;
        return right - left - 1;
    }
}
```

**3 二次二分(2)**

```java
class Solution {
    public int search(int[] nums, int target) {
        return helper(nums, target) - helper(nums, target - 1);
    }
    int helper(int[] nums, int tar) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (i + j) / 2;
            if(nums[m] <= tar) i = m + 1;
            else j = m - 1;
        }
        return i;
    }
}
```

# 剑指Offer53II-在排序数组中查找数组II

一个长度为n-1的递增排序数组中的所有数字都是唯一的，并且每个数字都在范围0～n-1之内。在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。

**1 二分 own**

`时间复杂度 O(logn) 空间复杂度 O(1)`

```java
class Solution {
    public int missingNumber(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] != mid) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right + 1;
    }
}
```

# 剑指Offer54-二叉搜索树的第K大节点

给定一棵二叉搜索树，请找出其中第 `k` 大的节点的值

`时间复杂度 最大O(n) 空间复杂度 最大O(n)` 最大是树退化为链表时，如全部为右节点时。

```java
class Solution {
    int res, k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    void dfs(TreeNode root) {
        if(root == null) return;
        dfs(root.right);
        if(k == 0) return;
        if(--k == 0) res = root.val;
        dfs(root.left);
    }
}
```

# 剑指Offer55I-二叉树的深度

输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

`时间复杂度 O(n) 空间复杂度 最差为O(n)`

**1 own DFS**

```java
class Solution {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    int depth;
    public int maxDepth(TreeNode root) {
        this.depth = 0;
        dfs(root, 0);
        return depth;
    }
    public void dfs(TreeNode root, int d) {
        if (root == null) {
            depth = Math.max(depth, d);
            return ;
        }
        dfs(root.left, ++d);
        dfs(root.right, d);
    }
}
```

**2 简洁DFS**

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```

**3 BFS**

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        List<TreeNode> queue = new LinkedList<>() {{ add(root); }}, tmp;
        int res = 0;
        while(!queue.isEmpty()) {
            tmp = new LinkedList<>();
            for(TreeNode node : queue) {
                if(node.left != null) tmp.add(node.left);
                if(node.right != null) tmp.add(node.right);
            }
            queue = tmp;
            res++;
        }
        return res;
    }
}
```

# 剑指Offer55II-平衡二叉树

输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。

**1 own(自顶向下)**

判断每个节点的是不是平衡二叉树

先序遍历 + 判断深度

`时间复杂度 O(nlogn) 空间复杂度 最差O(n)`

![](.\picture\Offer55-1.png)

```java
boolean ans = true;
public boolean isBalanced(TreeNode root) {
    if (ans == false || root == null) {
        return ans;
    }
    ans = Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 ? true : false;
    ans = ans && isBalanced(root.left);
    ans = ans && isBalanced(root.right);
    return ans;
}

public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}
```

可以对上式进行简化

```java

public boolean isBalanced(TreeNode root) {
    if (root == null) {
        return true;
    }
    return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && 	        	isBalanced(root.right);
}

public int maxDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}
```

**2 后序遍历+剪枝**

`时间复杂度 O(n) 空间复杂度 最差O(n)`

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    private int recur(TreeNode root) {
        if (root == null) return 0;
        int left = recur(root.left);
        if(left == -1) return -1;
        int right = recur(root.right);
        if(right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }
}
```

# 剑指Offer56I-数组中数字出现的次数

一个整型数组 `nums` 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是$O(n)$，空间复杂度是$O(1)$。

==位运算==

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public int[] singleNumbers(int[] nums) {
        int x = 0, y = 0, m = 1, temp = 0;
        for (int i : nums) {
            temp ^= i;
        }
        while ((temp & m) == 0) {
            m <<= 1;
        }
        for (int i : nums) {
            if ((i & m)  != 0) {
                x ^= i;
            } else {
                y ^= i;
            }
        }
        return new int[] {x, y};
    }
}
```

# 剑指Offer56II-数组中数字出现的次数

在一个数组 `nums` 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

**1、==哈希表==**

`时间复杂度 O(n) 空间复杂度 O(n)`

```java
/ 使用 HashMap 记录各个数字出现的次数
public int singleNumber(int[] nums) {
    Map<Integer, Integer> map = new HashMap();

    for(int i = nums.length - 1; i >= 0; --i){
        int key = nums[i];
        if(!map.containsKey(key)){
            // 如果之前没有遇到这一数字，则放入 map 中
            map.put(key, 1);
        }else{
            // 如果之前遇到过这一数字，则出现次数加 1
            map.put(key, map.get(key) + 1);
        }
    }

    for(Map.Entry<Integer, Integer> entry: map.entrySet()){
        if(entry.getValue() == 1){
            return entry.getKey();
        }
    }

    return -1;
}
```

**2、==有限状态机==**

2、3的整体思路如下：

![](./picture/Offer56-1.png)

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0;
        for(int num : nums){
            ones = ones ^ num & ~twos;
            twos = twos ^ num & ~ones;
        }
        return ones;
    }
}
```

**3、==遍历统计==**

也是利用位运算的例子

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public int singleNumber(int[] nums) {
        int[] counts = new int[32];
        for(int num : nums) {
            for(int j = 0; j < 32; j++) {
                counts[j] += num & 1;
                num >>>= 1;
            }
        }
        int res = 0, m = 3;
        for(int i = 0; i < 32; i++) {
            res <<= 1;
            res |= counts[31 - i] % m;
        }
        return res;
    }
}
```

# 剑指Offer57I-和为s的两个数字

输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。

**1 ==双指针法==**

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int i = 0, j = nums.length - 1;
        while(i < j) {
            int s = nums[i] + nums[j];
            if(s < target) i++;
            else if(s > target) j--;
            else return new int[] { nums[i], nums[j] };
        }
        return new int[0];
    }
}

```

**2 ==哈希表==**

`时间复杂度 O(n) 空间复杂度 O(n)`

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{nums[i], target - nums[i]};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
```

# 剑指Offer57II-和为s的连续正数序列

输入一个正整数 target ，输出所有和为 target 的连续正整数序列（至少含有两个数）。

序列内的数字由小到大排列，不同序列按照首个数字从小到大排列。

**1 ==own==**

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        List<Integer> possibleStarts = new ArrayList<>();
        Queue<Integer> possibleNums = new LinkedList<>();
        for (int num = target / 2; num >= 2; num--) {
            int mid = target / num, remainder = target % num;
            int temp = (num - 1) /2 + 1;
            int start = mid - temp + 1;
            if (start > 0) 
                if ((num % 2 == 0 && remainder == temp) ||(num % 2 != 0 && remainder == 0)) {
                    possibleStarts.add(start);
                    possibleNums.offer(num);
                }
        }
        int[][] ans = new int[possibleStarts.size()][];
        for (int k = 0; k < possibleStarts.size(); k++) {
            int[] temp = new int[possibleNums.poll()];
            temp[0] = possibleStarts.get(k);
            for (int i = 1; i < temp.length; i++) {
                temp[i] = temp[i - 1] + 1;
            }
            ans[k] = temp;
        }
        return ans;
    }
}
```

**2 ==求和公式==**

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        int i = 1;
        double j = 2.0;
        List<int[]> res = new ArrayList<>();
        while(i < j) {
            j = (-1 + Math.sqrt(1 + 4 * (2 * target + (long) i * i - i))) / 2;
            if(i < j && j == (int)j) {
                int[] ans = new int[(int)j - i + 1];
                for(int k = i; k <= (int)j; k++)
                    ans[k - i] = k;
                res.add(ans);
            }
            i++;
        }
        return res.toArray(new int[0][]);
    }
}
```

**3 ==滑动窗口法或者双指针法==**

`时间复杂度 O(n) 空间复杂度 O(1)`

```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        int i = 1, j = 2, s = 3;
        List<int[]> res = new ArrayList<>();
        while(i < j) {
            if(s == target) {
                int[] ans = new int[j - i + 1];
                for(int k = i; k <= j; k++)
                    ans[k - i] = k;
                res.add(ans);
            }
            if(s >= target) {
                s -= i;
                i++;
            } else {
                j++;
                s += j;
            }
        }
        return res.toArray(new int[0][]);
    }
}
```

# 剑指Offer58I-翻转单词顺序

输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。

**1 ==own==**

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        Stack<String> strs = new Stack<>();
        StringBuilder sb = new StringBuilder();
        boolean flag = false;
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if (temp != ' ') {
                flag = true;
                sb.append(temp);
            } else if (flag == true) {
                flag = false;
                strs.push(sb.toString());
                sb = new StringBuilder();
            }
        }
        strs.push(sb.toString());
        sb = new StringBuilder();
        int length = strs.size();
        for (int i = 0; i < length - 1; i++) {
            sb.append(strs.pop() + " ");
        }
        sb.append(strs.pop());
        return sb.toString();
    }
}
```

**2 ==双指针==**

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public String reverseWords(String s) {
        s = s.trim(); // 删除首尾空格
        int j = s.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while(i >= 0) {
            while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
            res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
            while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
            j = i; // j 指向下个单词的尾字符
        }
        return res.toString().trim(); // 转化为字符串并返回
    }
}
```

**3 ==分割+倒序==**

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public String reverseWords(String s) {
        String[] strs = s.trim().split(" "); // 删除首尾空格，分割字符串
        StringBuilder res = new StringBuilder();
        for(int i = strs.length - 1; i >= 0; i--) { // 倒序遍历单词列表
            if(strs[i].equals("")) continue; // 遇到空单词则跳过
            res.append(strs[i] + " "); // 将单词拼接至 StringBuilder
        }
        return res.toString().trim(); // 转化为字符串，删除尾部空格，并返回
    }
}
```

或者

```java
class Solution {
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
```

# 剑指Offer58II-左转字符串

字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(n));
        for (int i = 0; i < n; i++) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
}
```

利用求余简化

```java
class Solution {
    public String reverseLeftWords(String s, int n) {
        StringBuilder res = new StringBuilder();
        for(int i = n; i < n + s.length(); i++)
            res.append(s.charAt(i % s.length()));
        return res.toString();
    }
}
```

# 剑指Offer59I-滑动窗口的最大值

给定一个数组 `nums` 和滑动窗口的大小 `k`，请找出所有滑动窗口里的最大值。

**1==单调队列==**

`时间 O(n) 空间 O(1)`

写法一 单调队列添加下标

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];
        for (int i = 0; i < nums.length; i++) {
            if (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                ans[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] ans = new int[nums.length - k + 1];
        for (int  i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            if (!deque.isEmpty() && i - deque.peekFirst() >= k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }
}
```

写法二 单调队列添加数值

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for(int i = 0; i < k; i++) {
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        // 形成窗口后
        for(int i = k; i < nums.length; i++) {
            if(deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }
}
```

**2==优先队列==**

`时间 O(nlogn) 空间 O(n)`

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }
}
```

**3==分块+预处理==**

`时间 O(n) 空间 O(n)`

类似于稀疏表（ST表）

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }
}
```

# 剑指Offer59II-队列的最大值

请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。

若队列为空，pop_front 和 max_value 需要返回 -1

```
示例 1：

输入: 
["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
[[],[1],[2],[],[],[]]
输出: [null,null,null,2,1,2]
```

`时间 O(1) 空间O(n)`

```java
class MaxQueue {
    List<Integer> data = new ArrayList<>();
    Deque<Integer> deque = new LinkedList<>();
    public MaxQueue() {

    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
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
```

# 剑指Offer 60-n个骰子的点数

把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。

你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

==动态规划==

`时间 O(n^2) 空间 O(n)`

```java
class Solution {
    public double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 /6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++)
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            dp = tmp;
        }
        return dp;
    }
}
```

# 剑指Offer61-扑克牌中的顺子

从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。

**1==own==**

`时间 O(n)=O(5) 空间 O(14)`

```java
class Solution {
    public boolean isStraight(int[] nums) {
        int[] temp = new int[14];
        int min = 14, max = -1;
        for (int i = 0; i < nums.length; i++) {
            temp[nums[i]]++;
            if (nums[i] != 0) {
                min = Math.min(min, nums[i]);
                max = Math.max(max, nums[i]);
            }
        }

        for (int i = min; i < max + 1; i++) {
            if (temp[i] > 1 || temp[0] < 0) {
                return false;
            } else if (temp[i] == 0) {
                temp[i] = 1;
                temp[0]--;
            }
        }
        return true;
    }
}
```

精简

```java
class Solution {
    public boolean isStraight(int[] nums) {
        int[] temp = new int[14];
        int min = 14, max = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;
            temp[nums[i]]++;
            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i]);
        }

        for (int i = min; i < max + 1; i++) {
            if (temp[i] > 1) {
                return false;
            } 
        }
        return max - min < 5;
    }
}
```

**2==集合==**

`时间 O(n)=O(5) 空间 O(n)=O(5)`

看起来复杂度一样，实际执行时间法1比法2用时少，这是因为使用java的容器，虽说时间复杂度量级一样，但容器操作时间更长（涉及的语句长度更长）。

```java
class Solution {
    public boolean isStraight(int[] nums) {
        Set<Integer> repeat = new HashSet<>();
        int max = 0, min = 14;
        for(int num : nums) {
            if(num == 0) continue; // 跳过大小王
            max = Math.max(max, num); // 最大牌
            min = Math.min(min, num); // 最小牌
            if(repeat.contains(num)) return false; // 若有重复，提前返回 false
            repeat.add(num); // 添加此牌至 Set
        }
        return max - min < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }
}
```

**3==排序+遍历==**

`时间 O(nlogn)=O(5log5) 空间 O(2)`

```java
class Solution {
    public boolean isStraight(int[] nums) {
        int joker = 0;
        Arrays.sort(nums); // 数组排序
        for(int i = 0; i < 4; i++) {
            if(nums[i] == 0) joker++; // 统计大小王数量
            else if(nums[i] == nums[i + 1]) return false; // 若有重复，提前返回 false
        }
        return nums[4] - nums[joker] < 5; // 最大牌 - 最小牌 < 5 则可构成顺子
    }
}
```

#  *剑指Offer62- 圆圈中最后剩下的数字

0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。求出这个圆圈里剩下的最后一个数字。

例如，0、1、2、3、4这5个数字组成一个圆圈，从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。

==动态规划==

`时间 O(n) 空间 O(1)`

```java
class Solution {
    public int lastRemaining(int n, int m) {
        int dp = 0;
        for (int i = 2; i <= n; i++) {
            dp = (dp + m) % i;
        }
        return dp;
    }
}
```

==链表==

`时间 O(n) 空间 O(n)`

超时

```java
class Solution {
    public int lastRemaining(int n, int m) {
        ListNode head = new ListNode(0), now = head, prev = null;
        for (int i = 1; i < n; i++) {
            now.next(i);
            now = now.next;
        }
        now.next = head;
        prev = now;
        now = head;
        while (n != 1) {
            for (int j = 0; j < (m - 1) % n; j++) {
                prev = now;
                now = now.next;
            }
            prev.next = now.next;
            now = prev.next;
            n--;
        }
        return now.val;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int val) {
        this.val = val;
    }

    void next(int val) {
        this.next = new ListNode(val);
    }
}
```

# 剑指Offer63-股票的最大利润

假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？

==动态规划==

`时间 O(n) 空间O(n)`

```java
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        int[] dp = new int[length];
        int min = prices[0];
        for (int i = 1; i < length; i++) {
            if (prices[i] - min < dp[i - 1]) {
                dp[i] = dp[i - 1];
            } else {
                dp[i] = prices[i] - min;
            }
            min = Math.min(min, prices[i]);
        }
        return dp[length - 1];
    }
}
```

简化

```java
class Solution {
    public int maxProfit(int[] prices) {
        int length = prices.length;
        if (length == 0) {
            return 0;
        }
        int[] dp = new int[length];
        int min = prices[0];
        for (int i = 1; i < length; i++) {
			dp[i] = Math.max(dp[i - 1], prices[i] - min)
            min = Math.min(min, prices[i]);
        }
        return dp[length - 1];
    }
}
```

`时间 O(n) 空间O(1)`

```java
class Solution {
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for(int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }
}
```

# 剑指Offer64-求1+2+...+n

求 `1+2+...+n` ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

**1==递归==**

```java
class Solution {
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}

```

**2==快速乘==**

```java
class Solution {
    public int sumNums(int n) {
        int ans = 0, A = n, B = n + 1;
        boolean flag;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        flag = ((B & 1) > 0) && (ans += A) > 0;
        A <<= 1;
        B >>= 1;

        return ans >> 1;
    }
}
```

# 剑指Offer65-不用加减乘除做加法

写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。

==位运算==

`时间 O(1) 空间 O(1)`

自己的写法

```java
class Solution {
    public int add(int a, int b) {
        int p = 1, c = 0, ans = a, temp;
        for (int i = 0; i < 32; i++) {
            temp = b & p;
            ans = ans ^ temp ^ c;
            p = p << 1;
            c = ((a & temp) | (c & temp) | (a & c)) << 1;
        }
        return ans;
    }
}
```

另外一种写法更简洁，更符合编程思维。

```java
class Solution {
    public int add(int a, int b) {
        while(b != 0) { // 当进位为 0 时跳出
            int c = (a & b) << 1;  // c = 进位
            a ^= b; // a = 非进位和
            b = c; // b = 进位
        }
        return a;
    }
}
```

# 剑指Offer66-构建乘积数组

给定一个数组 $A[0,1,…,n-1]$，请构建一个数组 $B[0,1,…,n-1]$，其中$ B[i]$ 的值是数组 $A$ 中除了下标$ i$ 以外的元素的积, 即$ B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]$。不能使用除法。

==上下三角遍历==

`时间 O(n) 空间 O(1)`

```java
class Solution {
    public int[] constructArr(int[] a) {
        int len = a.length;
        if (len == 0) {
            return new int[0];
        }
        int[] b = new int[len];
        b[0] = 1;
        int temp = 1;
        for (int i = 1; i < len; i++) {
            b[i] = b[i - 1] * a[i - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            temp *= a[i + 1];
            b[i] *= temp;
        }
        return b;
    }
}
```

# 剑指Offer67-把字符串转化成整数

写一个函数 `StrToInt`，实现把字符串转换成整数这个功能。不能使用 `atoi` 或者其他类似的库函数。

首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。

当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。

该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。

注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。

在任何情况下，若函数不能进行有效的转换时，请返回 0。

`时间 O(n) 空间 O(1)`

```java
class Solution {
    public int strToInt(String str) {
        int res = 0, bndry = Integer.MAX_VALUE / 10;
        int i = 0, sign = 1, length = str.length();
        if(length == 0) return 0;
        while(str.charAt(i) == ' ')
            if(++i == length) return 0;
        if(str.charAt(i) == '-') sign = -1;
        if(str.charAt(i) == '-' || str.charAt(i) == '+') i++;
        for(int j = i; j < length; j++) {
            if(str.charAt(j) < '0' || str.charAt(j) > '9') break;
            if(res > bndry || res == bndry && str.charAt(j) > '7')
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            res = res * 10 + (str.charAt(j) - '0');
        }
        return sign * res;
    }
}
```

https://leetcode.cn/problems/string-to-integer-atoi/solution/zi-fu-chuan-zhuan-huan-zheng-shu-atoi-by-leetcode-/

`时间 O(n) 空间 O(n)` 空间复杂度由`trim`引起

```java
class Solution {
    public int strToInt(String str) {
        int flag = 1, i = 0;
        StringBuilder ans = new StringBuilder();
        str = str.trim();
        int len = str.length();
        String min = String.valueOf(Integer.MIN_VALUE).substring(1), max = String.valueOf(Integer.MAX_VALUE);
        if (len == 0) {
            return 0;
        }
        char c;
        if ((c = str.charAt(0)) == '+') {
            flag = 1;
            i = 1;
        } else if (c == '-') {
            flag = -1;
            i = 1;
        }
        for (; i < len; i++) {
            if ((c = str.charAt(i)) >= '0' && c <= '9') {
                ans.append(c);
            } else {
                break;
            }
        }
        String res = ans.toString();
        for (i = 0; i < res.length() - 1; i++) {
            if (res.charAt(i) != '0') {
                break;
            }
        }
        if (res.length() == 0) {
            return 0;
        }                
        res = res.substring(i);
        int l1 = res.length(), l2 = max.length();
        if ((l1 > l2 && flag == -1) || (l1 == l2 && res.compareTo(min) >= 0 && flag == -1)) {
            return Integer.MIN_VALUE;
        } else if (l1 > l2 || (l1 == l2 && res.compareTo(max) >= 0)) {
            return flag * Integer.MAX_VALUE;
        } else {
            return flag * Integer.valueOf(res);
        }
    }
}
```

# 剑指Offer68I-二叉搜索树的最近公共祖先

给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

`时间 最大O(n) 空间 最大O(n)`

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val < p.val && root.val < q.val)
            return lowestCommonAncestor(root.right, p, q);
        if(root.val > p.val && root.val > q.val)
            return lowestCommonAncestor(root.left, p, q);
        return root;
    }
}
```

减小判断条件

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(p.val > q.val) { // 保证 p.val < q.val
            TreeNode tmp = p;
            p = q;
            q = tmp;
        }
        while(root != null) {
            if(root.val < p.val) // p,q 都在 root 的右子树中
                root = root.right; // 遍历至右子节点
            else if(root.val > q.val) // p,q 都在 root 的左子树中
                root = root.left; // 遍历至左子节点
            else break;
        }
        return root;
    }
}
```

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val > q.val) return lowestCommonAncestor(root, q, p);
        if (root.val < p.val) return lowestCommonAncestor(root.right, p, q);
        if (root.val > q.val) return lowestCommonAncestor(root.left, p, q);
        return root;
    }
}
```



# 剑指Offer68II-二叉树的最近公共祖先

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]

==DFS==

`时间 O(n) 空间 最大O(n)`

```java
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left == null) return right;
        if(right == null) return left;
        return root;
    }
}
```

==DFS存储父节点==

`时间 O(n) 空间 O(n)`

```java
class Solution {
    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }
}
```

# 剑指OfferII44-二叉树每层中的最大值

给定一棵二叉树的根节点 `root` ，请找出该二叉树中每一层的最大值。

==1 BFS==

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Queue<TreeNode> visit = new LinkedList<>();
        List<Integer> res = new ArrayList<>();
        int max = Integer.MIN_VALUE, len = 0;
        TreeNode temp = null;
        visit.offer(root);
        while (!visit.isEmpty()) {
           len = visit.size();
           for (int i = 0; i < len; i++) {
                temp = visit.poll();
                max = Math.max(max, temp.val);
                if (temp.left != null) {
                    visit.add(temp.left);
                }
                if (temp.right != null) {
                    visit.add(temp.right);
                }
           }
           res.add(max);
        }
        return res;
    }
}
```

==2 DFS==

`时间 O(n) 空间 最大O(n)`

```java
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        if (root == null) {
            return new ArrayList<Integer>();
        }
        List<Integer> res = new ArrayList<Integer>();
        dfs(res, root, 0);
        return res;
    }

    public void dfs(List<Integer> res, TreeNode root, int curHeight) {
        if (curHeight == res.size()) {
            res.add(root.val);
        } else {
            res.set(curHeight, Math.max(res.get(curHeight), root.val));
        }
        if (root.left != null) {
            dfs(res, root.left, curHeight + 1);
        }
        if (root.right != null) {
            dfs(res, root.right, curHeight + 1);
        }
    }
}

```

























































# Leetcode1-两数之和

给定一个整数数组 `nums` 和一个整数目标值 `target`，请你在该数组中找出 **和为目标值** *`target`* 的那 **两个** 整数，并返回它们的数组下标。

你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。

你可以按任意顺序返回答案。

**1 ==暴力搜索==**

`时间复杂度 O(n^2) 空间复杂度 O(1)`

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) 
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        return new int[0];
    }
}
```

**2 ==哈希表==**

`时间复杂度 O(n) 空间复杂度 O(n)`

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hashtable = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; ++i) {
            if (hashtable.containsKey(target - nums[i])) {
                return new int[]{hashtable.get(target - nums[i]), i};
            }
            hashtable.put(nums[i], i);
        }
        return new int[0];
    }
}
```

# Leetcode2-[两数相加](https://leetcode.cn/problems/add-two-numbers/description/)

给你两个 **非空** 的链表，表示两个非负的整数。它们每位数字都是按照 **逆序** 的方式存储的，并且每个节点只能存储 **一位** 数字。

请你将两个数相加，并以相同形式返回一个表示和的链表。

你可以假设除了数字 0 之外，这两个数都不会以 0 开头

`时间 O(n) 空间 O(1) `

==own==

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(), temp = res;
        int carry = 0, value;
        while (l1 != null && l2 != null) {
            value = l1.val + l2.val + carry;
            carry = value / 10;
            temp.next = new ListNode(value > 9 ? value - 10 : value); 
            temp = temp.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        while (l1 != null) {
            value = l1.val + carry;
            carry = value / 10;
            temp.next = new ListNode(value > 9 ? value - 10 : value); 
            temp = temp.next;
            l1 = l1.next;
        }
        while (l2 != null) {
            value = l2.val + carry;
            carry = value / 10;
            temp.next = new ListNode(value > 9 ? value - 10 : value); 
            temp = temp.next;
            l2 = l2.next;
        }
        if (carry > 0) {
            temp.next = new ListNode(carry);
        }
        return res.next;
    }
}
```

==其他==

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null, tail = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int n1 = l1 != null ? l1.val : 0;
            int n2 = l2 != null ? l2.val : 0;
            int sum = n1 + n2 + carry;
            if (head == null) {
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }
}

```

# Leetcode3-[无重复字符的最长子串](https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/)

同剑指Offer48

# Leetcode4-[寻找两个正序数组的中位数](https://leetcode.cn/problems/median-of-two-sorted-arrays/description/)

给定两个大小分别为 `m` 和 `n` 的正序（从小到大）数组 `nums1` 和 `nums2`。请你找出并返回这两个正序数组的 **中位数** 。

算法的时间复杂度应该为 `O(log (m+n))` 

==1 直接遍历==

`时间 O(m+n) 空间 O(1)`

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int k = (m + n) / 2;
        int i = 0, j = 0;
        int res1 = 0, res2 = 0;
        while (i < m || j < n) {
            if (j == n || i != m && nums1[i] < nums2[j]) {
                i++;
                if (i + j == k) {
                    res1 = nums1[i - 1];
                }
                if (i + j == k + 1) {
                    res2 = nums1[i - 1];
                }
            } else if (i == m || j != n && nums1[i] >= nums2[j]) {
                j++;
                if (i + j == k) {
                    res1 = nums2[j - 1];
                }
                if (i + j == k + 1) {
                    res2 = nums2[j - 1];
                }
            }
        }
        return (m + n) % 2 == 1 ? res2 : (res1 + res2) / 2.0;
    }
}
```

更简洁的写法

```java
public double findMedianSortedArrays(int[] A, int[] B) {
    int m = A.length;
    int n = B.length;
    int len = m + n;
    int left = -1, right = -1;
    int aStart = 0, bStart = 0;
    for (int i = 0; i <= len / 2; i++) {
        left = right;
        if (aStart < m && (bStart >= n || A[aStart] < B[bStart])) {
            right = A[aStart++];
        } else {
            right = B[bStart++];
        }
    }
    if ((len & 1) == 0)
        return (left + right) / 2.0;
    else
        return right;
}
```

==2 二分法（转化为求第k小）==

`时间 O(log(m+n)) 空间 O(1)`

递归的写法空间复杂度也是`O(1)`，因为其实尾递归的形式，不会额外占用栈空间。

```java
public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int n = nums1.length;
    int m = nums2.length;
    int left = (n + m + 1) / 2;
    int right = (n + m + 2) / 2;
    //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
    return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;  
}
    
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1 
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

```

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length1 = nums1.length, length2 = nums2.length;
        int totalLength = length1 + length2;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKthElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
            double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public int getKthElement(int[] nums1, int[] nums2, int k) {
        /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
         * 这里的 "/" 表示整除
         * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
         * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
         * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
         * 这样 pivot 本身最大也只能是第 k-1 小的元素
         * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
         * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
         * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
         */

        int length1 = nums1.length, length2 = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;

        while (true) {
            // 边界情况
            if (index1 == length1) {
                return nums2[index2 + k - 1];
            }
            if (index2 == length2) {
                return nums1[index1 + k - 1];
            }
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            
            // 正常情况
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, length1) - 1;
            int newIndex2 = Math.min(index2 + half, length2) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }
}
```

==3 二分法（划分数组）==

`时间 O(log min(m, n)) 空间 O(1)`

```java
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length;
        int n = nums2.length;
        int left = 0, right = m;
        // median1：前一部分的最大值
        // median2：后一部分的最小值
        int median1 = 0, median2 = 0;

        while (left <= right) {
            // 前一部分包含 nums1[0 .. i-1] 和 nums2[0 .. j-1]
            // 后一部分包含 nums1[i .. m-1] 和 nums2[j .. n-1]
            int i = (left + right) / 2;
            int j = (m + n + 1) / 2 - i;

            // nums_im1, nums_i, nums_jm1, nums_j 分别表示 nums1[i-1], nums1[i], nums2[j-1], nums2[j]
            int nums_im1 = (i == 0 ? Integer.MIN_VALUE : nums1[i - 1]);
            int nums_i = (i == m ? Integer.MAX_VALUE : nums1[i]);
            int nums_jm1 = (j == 0 ? Integer.MIN_VALUE : nums2[j - 1]);
            int nums_j = (j == n ? Integer.MAX_VALUE : nums2[j]);

            if (nums_im1 <= nums_j) {
                median1 = Math.max(nums_im1, nums_jm1);
                median2 = Math.min(nums_i, nums_j);
                left = i + 1;
            } else {
                right = i - 1;
            }
        }

        return (m + n) % 2 == 0 ? (median1 + median2) / 2.0 : median1;
    }
}
```

# Leetcode5-[最长回文子串](https://leetcode.cn/problems/longest-palindromic-substring/)

给你一个字符串 `s`，找到 `s` 中最长的回文子串。

==1 动态规划==

`时间O(n^2) 空间 O(n^2)`

注意这个动态规划循环的方向是枚举子串长度的方向

```java
public class Solution {

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }

        int maxLen = 1;
        int begin = 0;
        // dp[i][j] 表示 s[i..j] 是否是回文串
        boolean[][] dp = new boolean[len][len];
        // 初始化：所有长度为 1 的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        char[] charArray = s.toCharArray();
        // 递推开始
        // 先枚举子串长度
        for (int L = 2; L <= len; L++) {
            // 枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                // 由 L 和 i 可以确定右边界，即 j - i + 1 = L 得
                int j = L + i - 1;
                // 如果右边界越界，就可以退出当前循环
                if (j >= len) {
                    break;
                }

                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                // 只要 dp[i][L] == true 成立，就表示子串 s[i..L] 是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }
}
```

```java
public String longestPalindrome(String s) {
    int n = s.length();
    String res = "";
    boolean[][] dp = new boolean[n][n];
    for (int i = n - 1; i >= 0; i--) {
        for (int j = i; j < n; j++) { // for (int j = n - 1; j >= i; j--) 
            dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || dp[i + 1][j - 1]); //j - i 代表长度减去 1        
            if (dp[i][j] &&  j - i + 1 > res.length()) {
                res = s.substring(i, j + 1);
            }
        }
    }
    return res;
}

```

简洁写法

`时间O(n^2) 空间 O(n)`

```java
public String longestPalindrome7(String s) {
		int n = s.length();
		String res = "";
		boolean[] P = new boolean[n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = n - 1; j >= i; j--) {
				P[j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || P[j - 1]);
				if (P[j] && j - i + 1 > res.length()) {
					res = s.substring(i, j + 1);
				}
			}
		}
		return res;
	}
```

==2 中心扩散法==

`时间O(n^2) 空间 O(1)`

```java
public String longestPalindrome(String s) {
    if (s == null || s.length() < 1) return "";
    int start = 0, end = 0;
    for (int i = 0; i < s.length(); i++) {
        int len1 = expandAroundCenter(s, i, i);
        int len2 = expandAroundCenter(s, i, i + 1);
        int len = Math.max(len1, len2);
        if (len > end - start) {
            start = i - (len - 1) / 2;
            end = i + len / 2;
        }
    }
    return s.substring(start, end + 1);
}

private int expandAroundCenter(String s, int left, int right) {
    int L = left, R = right;
    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
        L--;
        R++;
    }
    return R - L - 1;
}
```

==3 Manacher's Algorithm 马拉车算法==

`时间O(n) 空间O(n)`

```java
class Solution {
    public String longestPalindrome(String s) {
        int start = 0, end = -1;
        StringBuffer t = new StringBuffer("#");
        for (int i = 0; i < s.length(); ++i) {
            t.append(s.charAt(i));
            t.append('#');
        }
        t.append('#');
        s = t.toString();

        List<Integer> arm_len = new ArrayList<Integer>();
        int right = -1, j = -1;
        for (int i = 0; i < s.length(); ++i) {
            int cur_arm_len;
            if (right >= i) {
                int i_sym = j * 2 - i;
                int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                cur_arm_len = expand(s, i - min_arm_len - 1, i + min_arm_len + 1);
            } else {
                cur_arm_len = expand(s, i, i);
            }
            arm_len.add(cur_arm_len);
            if (i + cur_arm_len > right) {
                j = i;
                right = i + cur_arm_len;
            }
            if (cur_arm_len * 2 + 1 > end - start) {
                start = i - cur_arm_len;
                end = i + cur_arm_len;
            }
        }

        StringBuffer ans = new StringBuffer();
        for (int i = start; i <= end; ++i) {
            if (s.charAt(i) != '#') {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }

    public int expand(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            --left;
            ++right;
        }
        return (right - left - 2) / 2;
    }
}
```

# Leetcode10-正则表达式匹配

同剑指Offer19

# Leetcode11-[盛最多水的容器](https://leetcode.cn/problems/container-with-most-water/description/)

给定一个长度为 `n` 的整数数组 `height` 。有 `n` 条垂线，第 `i` 条线的两个端点是 `(i, 0)` 和 `(i, height[i])` 。

找出其中的两条线，使得它们与 `x` 轴共同构成的容器可以容纳最多的水。

返回容器可以储存的最大水量。

**说明：**你不能倾斜容器。

==双指针法==

`时间O(n) 空间O(1)`

```java
class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1;
        int res = -1;
        while (i < j) {
            if (height[i] < height[j]) {
                res = Math.max(res, height[i] * (j - i));
                i++;
            } else {
                res = Math.max(res, height[j] * (j - i));
                j--;
            }
        }
        return res;
    }
}
```

更短写法

```java
class Solution {
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while(i < j) {
            res = height[i] < height[j] ? 
                Math.max(res, (j - i) * height[i++]): 
                Math.max(res, (j - i) * height[j--]); 
        }
        return res;
    }
}
```

# Leetcode15-[三数之和](https://leetcode.cn/problems/3sum/description/)

给你一个包含 `n` 个整数的数组 `nums`，判断 `nums` 中是否存在三个元素 *a，b，c ，*使得 *a + b + c =* 0 ？请你找出所有和为 `0` 且不重复的三元组。

**注意：**答案中不可以包含重复的三元组。

==双指针==

`时间 O(n^2) 空间 O(1) O(logn)【考虑排序时使用的快排的空间复杂度】`

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int len = nums.length;
        for (int k = 0; k < len - 2; k++) {
            if (nums[k] > 0) {
                break;
            }
            if (k > 0 && nums[k] == nums[k - 1]) {
                continue;
            }
            int i = k + 1, j = len - 1;
            while (i < j) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < 0) {
                    while (i < j && nums[i] == nums[++i]);
                } else if (sum > 0) {
                    while (i < j && nums[j] == nums[--j]);
                } else {
                    ans.add(new ArrayList<Integer>(Arrays.asList(nums[k], nums[i], nums[j])));
                    while (i < j && nums[i] == nums[++i]);
                    while (i < j && nums[j] == nums[--j]); 
                }
            }
        }
        return ans;
    }
}
```

# Leetcode19-[删除链表的倒数第 N 个结点](https://leetcode.cn/problems/remove-nth-node-from-end-of-list/description/)

给你一个链表，删除链表的倒数第 `n` 个结点，并且返回链表的头结点。

==1两次遍历==

`时间 O(n) 空间O(1)`

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        int len = 0;
        ListNode temp = head;
        while (temp != null) {
            temp = temp.next;
            len++;
        }
        temp = head;
        if (len - n == 0) {
            return head.next;
        }
        for (int i = 0; i < len - n - 1; i++) {
            temp = temp.next;
        }
        temp.next = temp.next.next;
        return head;
    }
}
```

也可以采用dummy的写法，这样就不需要分类讨论了

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next;
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }
}

```

==2栈==

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
```

==3 保存遍历量==

`时间 O(n) 空间 O(n)`

```java
class Solution {
    List<ListNode> visited = new ArrayList<>();
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode temp = head;
        while (temp != null) {
            visited.add(temp); 
            temp = temp.next;
        }
        int index = visited.size() - n - 1;
        if (index < 0) return head.next;
        ListNode indexNode = visited.get(index);
        indexNode.next  = indexNode.next.next;
        return head;
    }
}
```

==4 双指针==

只用一次遍历

`时间 O(n) 空间 O(1)`

```java
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);
        ListNode first = head;
        ListNode second = dummy;
        for (int i = 0; i < n; ++i) {
            first = first.next;
        }
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        ListNode ans = dummy.next;
        return ans;
    }
}
```

# Leetcode20-[有效的括号](https://leetcode.cn/problems/valid-parentheses/description/)

给定一个只包括 `'('`，`')'`，`'{'`，`'}'`，`'['`，`']'` 的字符串 `s` ，判断字符串是否有效。

有效字符串需满足：

1. 左括号必须用相同类型的右括号闭合。
2. 左括号必须以正确的顺序闭合。

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            char peek = ' ';
            if (!stack.isEmpty() 
                && (peek = stack.peek()) == '[' &&  c == ']'
                || peek == '{' && c == '}'
                || peek == '(' && c == ')') {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
```

```java
class Solution {
    private static final Map<Character,Character> map = new HashMap<Character,Character>(){{
        put('{','}'); put('[',']'); put('(',')'); put('?','?');
    }};
    public boolean isValid(String s) {
        if(s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{ add('?'); }};
        for(Character c : s.toCharArray()){
            if(map.containsKey(c)) stack.addLast(c);
            else if(map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }
}
```

```java
public boolean isValid(String s) {
        if(s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||c!=stack.pop())
                return false;
        }
		return stack.isEmpty();
    }
```

# Leetcode21-[合并两个有序链表](https://leetcode.cn/problems/merge-two-sorted-lists/description/)

==递归==

`时间 最大O(m + n) 空间 最大O(m+n)`

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }
}
```

==迭代==

`时间 最大O(m + n) 空间 O(1)`

```java
class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode temp = new ListNode(0), head = temp;
        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                temp.next = list1;
                list1 = list1.next;
            } else {
                temp.next = list2;
                list2 = list2.next;
            }
            temp = temp.next;
        }
        if (list1 != null) {
            temp.next = list1;
        }
        if (list2 != null) {
            temp.next = list2;
        }
        return head.next;
    }
}
```

```java
class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
```

# *Leetcode22-[括号生成](https://leetcode.cn/problems/generate-parentheses/description/)

数字 `n` 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

==1 暴力法==

时间 $O(2^{2n}n)$ 空间 $O(n)$

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<String>();
        generateAll(new char[2 * n], 0, combinations);
        return combinations;
    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current)) {
                result.add(new String(current));
            }
        } else {
            current[pos] = '(';
            generateAll(current, pos + 1, result);
            current[pos] = ')';
            generateAll(current, pos + 1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') {
                ++balance;
            } else {
                --balance;
            }
            if (balance < 0) {
                return false;
            }
        }
        return balance == 0;
    }
}
```

==2 回溯==

时间 $O(C^n_{2n}-C^{n-1}_{2n})=O(\frac{4^n}{\sqrt{n}})$ 空间 $O(n)$

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtrack(ans, new StringBuilder(), 0, 0, n);
        return ans;
    }

    public void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append('(');
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(')');
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }
}
```

==3 按括号序列的递归 动态规划==

时间 $O(C^n_{2n}-C^{n-1}_{2n})=O(\frac{4^n}{\sqrt{n}})$ 空间 $O(\frac{4^n}{\sqrt{n}})$

```java
class Solution {
    ArrayList[] cache = new ArrayList[100];

    public List<String> generate(int n) {
        if (cache[n] != null) {
            return cache[n];
        }
        ArrayList<String> ans = new ArrayList<String>();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left: generate(c)) {
                    for (String right: generate(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        cache[n] = ans;
        return ans;
    }

    public List<String> generateParenthesis(int n) {
        return generate(n);
    }
}
```

```java
public List<String> generateParenthesis(int n) {
		LinkedList<LinkedList<String>> result = new LinkedList<LinkedList<String>>();
		if (n == 0)
			return result.get(0);
		LinkedList<String> list0 = new LinkedList<String>();
		list0.add("");
		result.add(list0);
		LinkedList<String> list1 = new LinkedList<String>();
		list1.add("()");
		result.add(list1);
		for (int i = 2; i <= n; i++) {
			LinkedList<String> temp = new LinkedList<String>();
			for (int j = 0; j < i; j++) {
				List<String> str1 = result.get(j);
				List<String> str2 = result.get(i - 1 - j);
				for (String s1 : str1) {
					for (String s2 : str2) {
						String el = "(" + s1 + ")" + s2;
						temp.add(el);
					}
				}

			}
			result.add(temp);
		}
		return result.get(n);
	}


```

# Leetcode[23. 合并K个升序链表](https://leetcode.cn/problems/merge-k-sorted-lists/)

给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

链表个数为`k` 每个链表长度最长为`n`

==顺序合并==

`时间 O(k^2n) 空间 O(1)`

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }
}
```

==优先队列==

`时间O(knlogk) 空间O(k)`

```java
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> queue = new PriorityQueue<>((a, b) -> a.val - b.val);
        ListNode head = new ListNode(0), tmp = head;
        for (ListNode node : lists) {
            if (node != null) {
                queue.add(node);
            }
        }
        while (!queue.isEmpty()) {
            tmp = tmp.next = queue.poll();
            if (tmp.next != null) {
                queue.offer(tmp.next);
            }
        }
        return head.next;
    }
}
```

==分而治之==

`时间O(k^2n) 空间O(logk)`

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
   public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}
```

# Leetcode[28. 找出字符串中第一个匹配项的下标](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/)

==字符串 KMP==

```java
class Solution {
    public void getNext(int[] next, String s){
        int j = -1;
        next[0] = j;
        for (int i = 1; i < s.length(); i++){
            while(j >= 0 && s.charAt(i) != s.charAt(j+1)){
                j=next[j];
            }

            if(s.charAt(i) == s.charAt(j+1)){
                j++;
            }
            next[i] = j;
        }
    }
    public int strStr(String haystack, String needle) {
        if(needle.length()==0){
            return 0;
        }

        int[] next = new int[needle.length()];
        getNext(next, needle);
        int j = -1;
        for(int i = 0; i < haystack.length(); i++){
            while(j>=0 && haystack.charAt(i) != needle.charAt(j+1)){
                j = next[j];
            }
            if(haystack.charAt(i) == needle.charAt(j+1)){
                j++;
            }
            if(j == needle.length()-1){
                return (i-needle.length()+1);
            }
        }

        return -1;
    }
}
```



# Leetcode[31. 下一个排列](https://leetcode.cn/problems/next-permutation/)

整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。

例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。

例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
给你一个整数数组 nums ，找出 nums 的下一个排列。

必须 原地 修改，只允许使用额外常数空间。

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }
        if (i >= 0) {
            int j = nums.length - 1;
            while (j >= 0 && nums[i] >= nums[j]) {
                j--;
            }
            swap(nums, i, j);
        }
        reverse(nums, i + 1);
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums, int start) {
        int left = start, right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
```

# Leetcode32- [最长有效括号](https://leetcode.cn/problems/longest-valid-parentheses/description/)

给你一个只包含 `'('` 和 `')'` 的字符串，找出最长有效（格式正确且连续）括号子串的长度。

==1 动态规划==

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public int longestValidParentheses(String s) {
        int len = s.length();
        int[] dp = new int[len];
        int max = 0;
        for (int i = 1; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] >= 1 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }
            max = Math.max(dp[i], max);
        }
        return max;
    }
}
```

==2 栈==

`时间 O(n) 空间 O(n)`

```java
class Solution {
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }
}
```

==3 贪心+补充说明==

`时间O(n) 空间 O(1)`

```java
class Solution {
    public int longestValidParentheses(String s) {
        int left = 0, right = 0, maxlength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                left++;
            } else {
                right++;
            }
            if (left == right) {
                maxlength = Math.max(maxlength, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return maxlength;
    }
}
```

# Leetcode[33. 搜索旋转排序数组](https://leetcode.cn/problems/search-in-rotated-sorted-array/)

整数数组 nums 按升序排列，数组中的值 互不相同 。

在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。

给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。

你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。

==二分查找==

`时间O(logn) 空间O(1)`

```java
class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        if (n == 0) {
            return -1;
        }
        if (n == 1) {
            return nums[0] == target ? 0 : -1;
        }
        int l = 0, r = n - 1;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[0] <= nums[mid]) {
                if (nums[0] <= target && target < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (nums[mid] < target && target <= nums[n - 1]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }
        return -1;
    }
}
```

# Leetcode[34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/)

给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。

如果数组中不存在目标值 target，返回 [-1, -1]。

你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。

==法一==

但不适用于float型的查找

```java
class Solution {
    //先找>=target的第一个
    //再找>target的第一个
    //我真是这辈子都不想看见这题
    public int[] searchRange(int[] nums, int target) {
        int l=search(nums,target);
        int r=search(nums,target+1);
        if(l==nums.length||nums[l]!=target)
            return new int[]{-1,-1};
        return new int[]{l,r - 1};
    }
    //找>=target的第一个
    public int search(int[] nums,int target){
        int l=0,r=nums.length;
        while(l<r){
            int mid=(r+l)>>1;
            if(nums[mid]>=target)
                r=mid;
            else
                l=mid+1;
        }
        return l;
    }
}
```

==法二==

两次二分分别找左边界和右边界

法二和法一二分的判断方式均为while(l < r)

```c++
class Solution {
public:
    vector<int> searchRange(vector<int>& nums, int target) {
        int n = nums.size();

        vector<int> ans(2, -1);
        if (n == 0) return ans;
        
        int l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r >> 1;
            if (nums[mid] >= target) r = mid;
            else l = mid + 1;
        }
        if (nums[r] != target) return ans;
        ans[0] = r;
        l = 0, r = n - 1;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            if (nums[mid] <= target) l = mid;
            else r = mid - 1;
        }
        ans[1] = r;

        return ans;
    }
};
```

==法三==

法三的二分判断是while(l <= r)

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target) {
            return new int[]{leftIdx, rightIdx};
        } 
        return new int[]{-1, -1};
    }

    public int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
}
```

# Leetcode[42. 接雨水](https://leetcode.cn/problems/trapping-rain-water/)

给定 `n` 个非负整数表示每个宽度为 `1` 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。

==动态规划==

`时间O(n) 空间O(n)`

```java
class Solution {
    public int trap(int[] height) {
        int len = height.length;
        int[] leftMax = new int[len], rightMax = new int[len];
        leftMax[0] = height[0];
        rightMax[len - 1] = height[len - 1];
        for (int i = 1; i < len; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]); 
        }
        for (int j = len - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }
        int ans = 0, min, tmp;
        for (int k = 1; k < len - 1; k++) {
            min = Math.min(leftMax[k - 1], rightMax[k + 1]);
            ans += (tmp = min - height[k]) > 0 ? tmp : 0; 
        }
        return ans;
    }
}
```

==双指针==

`时间O(n) 空间O(1)`

```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (height[left] < height[right]) {
                ans += leftMax - height[left];
                ++left;
            } else {
                ans += rightMax - height[right];
                --right;
            }
        }
        return ans;
    }
}
```

==栈==

`时间O(n) 空间O(n)`

```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        Deque<Integer> stack = new LinkedList<Integer>();
        int n = height.length;
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int top = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int currWidth = i - left - 1;
                int currHeight = Math.min(height[left], height[i]) - height[top];
                ans += currWidth * currHeight;
            }
            stack.push(i);
        }
        return ans;
    }
}
```

# Leetcode[43. 字符串相乘](https://leetcode.cn/problems/multiply-strings/)

给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。

```java
class Solution {
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum % 10;
                res[i + j] += sum / 10;
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0) continue;
            result.append(res[i]);
        }
        return result.toString();
    }
}
```

# Leetcode[48. 旋转图像](https://leetcode.cn/problems/rotate-image/)

给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。

你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。

```java
class Solution {
    public void rotate(int[][] matrix) {
        int len = matrix[0].length;
        for (int i = 0; i < len / 2; i++)
            for (int j = i; j < len - i - 1; j++) {
                int temp = matrix[i][j];
                int p = i, q = j, m;
                for (int k = 0; k < 3; k++) {
                    matrix[p][q] = matrix[len - 1 -q][p];
                    m = p;
                    p = len - 1 - q;
                    q = p;
                }
                matrix[p][q] = temp;
            }
    }
}
```

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < (n + 1) / 2; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - j - 1][i];
                matrix[n - j - 1][i] = matrix[n - i - 1][n - j - 1];
                matrix[n - i - 1][n - j - 1] = matrix[j][n - i - 1];
                matrix[j][n - i - 1] = temp;
            }
        }
    }
}
```

# Leetcode[49. 字母异位词分组](https://leetcode.cn/problems/group-anagrams/)

给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。

字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

==排序+哈希==

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        Map<String, Integer> hashmap = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String key = new String(charArray);
            if (!hashmap.containsKey(key)) {
                hashmap.put(key, ans.size());
                ans.add(new ArrayList<>());
            }
            ans.get(hashmap.get(key)).add(str);
        }
        return ans;
    }
}
```

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
```

==计数+哈希==

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            int[] counts = new int[26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i) - 'a']++;
            }
            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0) {
                    sb.append((char) ('a' + i));
                    sb.append(counts[i]);
                }
            }
            String key = sb.toString();
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }
}
```

# Leetcode[50. Pow(x, n)](https://leetcode.cn/problems/powx-n/)

==快速幂==

```java
class Solution {
    public double myPow(double x, int n) {
        double res = 1;
        long b = n;
        if (b < 0) {
            x = 1.0 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) > 0)res = res * x;
            x = x * x;
            b >>= 1;
        }
        return res;
    }
}
```

# Leetcode[54. 螺旋矩阵](https://leetcode.cn/problems/spiral-matrix/)

给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素

示例 1：

输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]



选择元素个数为判断条件

```java
class Solution {
    public static List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> result = new LinkedList<>();
        if(matrix==null||matrix.length==0) return result;
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int numEle = matrix.length * matrix[0].length;
        while (numEle >= 1) {
            for (int i = left; i <= right && numEle >= 1; i++) {
                result.add(matrix[top][i]);
                numEle--;
            }
            top++;
            for (int i = top; i <= bottom && numEle >= 1; i++) {
                result.add(matrix[i][right]);
                numEle--;
            }
            right--;
            for (int i = right; i >= left && numEle >= 1; i--) {
                result.add(matrix[bottom][i]);
                numEle--;
            }
            bottom--;
            for (int i = bottom; i >= top && numEle >= 1; i--) {
                result.add(matrix[i][left]);
                numEle--;
            }
            left++;
        }
        return result;
    }
}
```

# Leetcode[56. 合并区间](https://leetcode.cn/problems/merge-intervals/)

以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。

==数组+排序==

`时间 O(nlogn) 空间O(n)`

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (v1, v2) -> v1[0] - v2[0]);
        int[][] ans = new int[intervals.length][2];
        int idx = -1;
        for (int[] ints : intervals) {
            if (idx == -1 || ints[0] > ans[idx][1]) {
                ans[++idx] = ints;
            } else {
                ans[idx][1] = Math.max(ans[idx][1], ints[1]);
            }
        }
        return Arrays.copyOf(ans, idx + 1);
    }
}
```

# Leetcode[59. 螺旋矩阵 II](https://leetcode.cn/problems/spiral-matrix-ii/)

给你一个正整数 `n` ，生成一个包含 `1` 到 `n2` 所有元素，且元素按顺时针顺序螺旋排列的 `n x n` 正方形矩阵 `matrix` 。

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/11/13/spiraln.jpg)

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int flag = n * n;
        int left = -1, right = n - 1, top = 0, bottom = n - 1;
        int temp = 1;
        int[][] res = new int[n][n];
        while (temp <= flag) {
            for (int i = ++left; i <= right && temp <= flag; i++) res[top][i] = temp++;
            for (int i = ++top; i <= bottom && temp <= flag; i++) res[i][right] = temp++;
            for (int i = --right; i >= left && temp <= flag; i--) res[bottom][i] = temp++;
            for (int i = --bottom; i >= top && temp <= flag; i--) res[i][left] = temp++;
        }
        return res;
    }
}
```

# Leetcode[61. 旋转链表](https://leetcode.cn/problems/rotate-list/)

给你一个链表的头节点 `head` ，旋转链表，将链表每个节点向右移动 `k` 个位置

```java
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        int length = 1;
        ListNode tail = head, res = null;
        while (tail.next != null) {
            length++;
            tail = tail.next;
        }
        tail.next = head;
        ListNode temp = head;
        for (int i = 1; i < length - k % length; i++) temp = temp.next;
        res = temp.next;
        tail.next = head;
        temp.next = null;
        return res;
    }
}
```



# Leetcode110-[平衡二叉树](https://leetcode.cn/problems/balanced-binary-tree/description/)

给定一个二叉树，判断它是否是高度平衡的二叉树。

本题中，一棵高度平衡二叉树定义为：

> 一个二叉树*每个节点* 的左右两个子树的高度差的绝对值不超过 1 。

同剑指Offer55II

# Leetcode199-[二叉树的右视图](https://leetcode.cn/problems/binary-tree-right-side-view/)

给定一个二叉树的 **根节点** `root`，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

`时间 O(n) 空间 O(n)`

==BFS==

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) {
            return new LinkedList<Integer>();
        }
        List<Integer> rights = new ArrayList<>();
        Queue<TreeNode> visited = new LinkedList<>();
        visited.offer(root);
        while (!visited.isEmpty()) {
            int len = visited.size();
            for (int i = 0; i < len; i++) {
                TreeNode temp = visited.poll();
                if (i == 0) {
                    rights.add(temp.val);
                }
                if (temp.right != null) {
                    visited.add(temp.right);
                }
                if (temp.left != null) {
                    visited.add(temp.left);
                }

            }
        }
        return rights;
    }
}
```

==DFS==

```java
class Solution {
    List<Integer> res = new ArrayList<>();

    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0); // 从根节点开始访问，根节点深度是0
        return res;
    }

    private void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        // 先访问 当前节点，再递归地访问 右子树 和 左子树。
        if (depth == res.size()) {   // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
            res.add(root.val);
        }
        depth++;
        dfs(root.right, depth);
        dfs(root.left, depth);
    }
}

```

# Leetcode[62. 不同路径](https://leetcode.cn/problems/unique-paths/)

一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。

机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。

问总共有多少条不同的路径？

==动态规划==

`时间O(mn) 空间O(mn)`

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; ++i) {
            f[i][0] = 1;
        }
        for (int j = 0; j < n; ++j) {
            f[0][j] = 1;
        }
        for (int i = 1; i < m; ++i) {
            for (int j = 1; j < n; ++j) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m - 1][n - 1];
    }
}
```

==数学规律==

```java
class Solution {
    public int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; ++x, ++y) {
            ans = ans * x / y;
        }
        return (int) ans;
    }
}
```

# Leetcode[64. 最小路径和](https://leetcode.cn/problems/minimum-path-sum/)

给定一个包含非负整数的 `*m* x *n*` 网格 `grid` ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。

**说明：**每次只能向下或者向右移动一步。

==动态规划==

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < n; i++) dp[0][i] = grid[0][i] + dp[0][i - 1];
        for (int i = 1; i < m; i++) dp[i][0] = grid[i][0] + dp[i - 1][0];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
} 
```

# Leetcode[70. 爬楼梯](https://leetcode.cn/problems/climbing-stairs/)

假设你正在爬楼梯。需要 `n` 阶你才能到达楼顶。

每次你可以爬 `1` 或 `2` 个台阶。你有多少种不同的方法可以爬到楼顶呢？

==动态规划==

```java
class Solution {
    public int climbStairs(int n) {
        int pre1 = 0, pre2 = 1;
        for (int i = 1; i <= n; i++) {
            int temp = pre2;
            pre2 = pre1 + pre2;
            pre1 = temp;
        }
        return pre2;
    }
}
```

```java
class Solution {
    public int climbStairs(int n) {
        if (n <= 2) return n;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2]; 
        }
        return dp[n - 1];
    }
}
```

# Leetcode[72. 编辑距离](https://leetcode.cn/problems/edit-distance/)

给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数  。

你可以对一个单词进行如下三种操作：

插入一个字符
删除一个字符
替换一个字符

==动态规划==

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // 有一个字符串为空串
        if (n * m == 0) {
            return n + m;
        }

        // DP 数组
        int[][] D = new int[n + 1][m + 1];

        // 边界状态初始化
        for (int i = 0; i < n + 1; i++) {
            D[i][0] = i;
        }
        for (int j = 0; j < m + 1; j++) {
            D[0][j] = j;
        }

        // 计算所有 DP 值
        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                int left = D[i - 1][j] + 1;
                int down = D[i][j - 1] + 1;
                int left_down = D[i - 1][j - 1];
                if (word1.charAt(i - 1) != word2.charAt(j - 1)) {
                    left_down += 1;
                }
                D[i][j] = Math.min(left, Math.min(down, left_down));
            }
        }
        return D[n][m];
    }
}
```



# Leetcode[75. 颜色分类](https://leetcode.cn/problems/sort-colors/)

给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。

必须在不使用库的sort函数的情况下解决这个问题。

==临时数组==

```java
class Solution {
    public void sortColors(int[] nums) {
        int[] copy = nums.clone();
        int len = nums.length;
        int left = 0, right = len - 1;
        for (int i = 0; i < len; i++) {
            if (copy[i] == 0) nums[left++] = 0;
            else if (copy[i] == 2) nums[right--] = 2;
        } 
        while (left <= right) {
            nums[left++] = 1;
        }
    }
}
```

==单指针+两次遍历==

```java
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int ptr = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
        for (int i = ptr; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[ptr];
                nums[ptr] = temp;
                ++ptr;
            }
        }
    }
}

```

==双指针==

```java
class Solution {
    public void sortColors(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;
        for (int i = 0; i < right + 1; i++) {
            if (nums[i] == 2) {
                swap(nums, i--, right--);
            } else if (nums[i] == 0) {
                swap(nums, i, left++);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

```java
class Solution {
    public void sortColors(int[] nums) {
        int len = nums.length;
        int left = 0, right = len - 1;
        for (int i = 0; i < right + 1; i++) {
            if (nums[i] == 2) {
                swap(nums, i--, right--);
            } else if (nums[i] == 0 && i >left) {
                swap(nums, i--, left++);
            }
        }
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

```java
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p1 = 0;
        for (int i = 0; i < n; ++i) {
            if (nums[i] == 1) {
                int temp = nums[i];
                nums[i] = nums[p1];
                nums[p1] = temp;
                ++p1;
            } else if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                if (p0 < p1) {
                    temp = nums[i];
                    nums[i] = nums[p1];
                    nums[p1] = temp;
                }
                ++p0;
                ++p1;
            }
        }
    }
}
```

```java
class Solution {
    public void sortColors(int[] nums) {
        int n = nums.length;
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }
}
```

# Leetcode[76. 最小覆盖子串](https://leetcode.cn/problems/minimum-window-substring/)

给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。

注意：

对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。

==双指针 动态窗口==

```java
class Solution {
    public String minWindow(String s, String t) {
        char[] chars = s.toCharArray(), chart = t.toCharArray();
        int n = chars.length, m = chart.length;

        int[] hash = new int[128];
        for (char ch : chart) hash[ch]--;

        String res = "";
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            hash[chars[i]]++;
            if (hash[chars[i]] <= 0) cnt++;
            while (cnt == m && hash[chars[j]] > 0) hash[chars[j++]]--;
            if (cnt == m)
                if (res.equals("") || res.length() > i - j + 1)
                    res = s.substring(j, i + 1);
        }
        return res;
    }
}
```

```java
class Solution {
    Map<Character, Integer> ori = new HashMap<Character, Integer>();
    Map<Character, Integer> cnt = new HashMap<Character, Integer>();

    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            ori.put(c, ori.getOrDefault(c, 0) + 1);
        }
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1;
        int sLen = s.length();
        while (r < sLen) {
            ++r;
            if (r < sLen && ori.containsKey(s.charAt(r))) {
                cnt.put(s.charAt(r), cnt.getOrDefault(s.charAt(r), 0) + 1);
            }
            while (check() && l <= r) {
                if (r - l + 1 < len) {
                    len = r - l + 1;
                    ansL = l;
                    ansR = l + len;
                }
                if (ori.containsKey(s.charAt(l))) {
                    cnt.put(s.charAt(l), cnt.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    public boolean check() {
        Iterator iter = ori.entrySet().iterator(); 
        while (iter.hasNext()) { 
            Map.Entry entry = (Map.Entry) iter.next(); 
            Character key = (Character) entry.getKey(); 
            Integer val = (Integer) entry.getValue(); 
            if (cnt.getOrDefault(key, 0) < val) {
                return false;
            }
        } 
        return true;
    }
}
```

```java
class Solution {
    public String minWindow(String s, String t) {
        int[] pattern = new int[128];
        int[] match = new int[128];
        int left = 0, right = 0, minLeft = -1,minRight = -1, cnt = 0, length = s.length() + 1;
        for (char c : t.toCharArray()) pattern[c]++;
        for (; right < s.length(); right++) {
            char c = s.charAt(right);
            if (pattern[c] == 0) continue;
            if (match[c] < pattern[c]) cnt++;
            match[c]++;
            if (cnt == t.length()) {
                for (; cnt == t.length(); left++) {
                    char temp = s.charAt(left);
                    if (pattern[temp] == 0) continue;
                    if (match[temp] == pattern[temp]) cnt--;
                    match[temp]--;  
                }
                if (right - left + 2 < length) {
                    length = right - left + 2;
                    minLeft = left - 1;
                    minRight = right;
                }
            }
        }
        if (minRight < 0) return "";
        return s.substring(minLeft, minRight + 1);
    }
}
```

# Leetcode[79. 单词搜索](https://leetcode.cn/problems/word-search/)

给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用

同剑指Offer12

==回溯==

```java
class Solution {
    public boolean exist(char[][] board, String word) {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board[i].length; ++j) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(board, word, i, j, 0)) return true;
                }
            }
        }
        return false;
    }
    public boolean dfs(char[][] board, String word, int i, int j, int k) {
        if (k == word.length()) {
            return true;
        }
        if (i < 0 || j < 0 || i >= board.length || j >= board[i].length) {
            return false;
        }
        
        if (word.charAt(k) != board[i][j]) {
            return false;
        }
        char t = board[i][j];
        board[i][j] = '0';
        boolean res = dfs(board, word, i + 1, j, k + 1) || 
        dfs(board, word, i - 1, j, k + 1) || 
        dfs(board, word, i, j + 1, k + 1) || 
        dfs(board, word, i, j - 1, k + 1);
        board[i][j] = t;
        return res;
    }
}
```

==自己的==

```java
class Solution {
    char[][] board;
    char[] wordArray;
    int m, n, len;
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.wordArray = word.toCharArray();
        this.m = board.length;
        this.n = board[0].length;
        this.len = word.length();
        return dfs(0, 0, 0); 
    }

    private boolean dfs(int x, int y, int index) {
        if (index == len) return true;
        if (x < 0 || x == m || y < 0 || y == n) return false;
        for (int i = x; i < m; i++) {
            for (int j = y; j < n; j++) {
                if (board[i][j] == wordArray[index]) {
                    index++;
                    char tmp = board[i][j];
                    board[i][j] = '#';
                    if (dfs(i - 1, j, index) || dfs(i + 1, j, index) || dfs(i, j - 1, index) || dfs(i, j + 1, index)) {
                        return true;
                    }
                    board[i][j] = tmp;
                    index--;
                }
                if (index > 0) return false;
            }
        }
        return false;
    }
}
```

# Leetcode[84. 柱状图中最大的矩形](https://leetcode.cn/problems/largest-rectangle-in-histogram/)

给定 *n* 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。

求在该柱状图中，能够勾勒出来的矩形的最大面积。

==栈==

`时间O(n) 空间O(n)`

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        
        Deque<Integer> mono_stack = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
                right[mono_stack.peek()] = i;
                mono_stack.pop();
            }
            left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
            mono_stack.push(i);
        }
        
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
        }
        return ans;
    }
}
```

# Leetcode[85. 最大矩形](https://leetcode.cn/problems/maximal-rectangle/)

给定一个仅包含 `0` 和 `1` 、大小为 `rows x cols` 的二维二进制矩阵，找出只包含 `1` 的最大矩形，并返回其面积。

==动态规划 栈==

`时间O(mn) 空间O(mn)`

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[][] pre = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = matrix[i - 1][j - 1] == '1' ? pre[i][j - 1] + 1 : 0;
            }
        }
        int ans = 0;
        for (int j = 1; j <= n; j++) {
            int[] up = new int[m + 1];
            int[] down = new int[m + 1];
            Arrays.fill(down, m + 1);
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 1; i <= m; i++) {
                while (!stack.isEmpty() && pre[stack.peek()][j] >= pre[i][j]) {
                    down[stack.peek()] = i;
                    stack.pop();
                }
                up[i] = stack.isEmpty() ? 0 : stack.peek();
                stack.push(i);
            }
            for (int i = 1; i <= m; i++) {
                ans = Math.max(ans, pre[i][j] * (down[i] - up[i] - 1));
            }
        }
        return ans;
    }
}
```

#  Leetcode[89. 格雷编码](https://leetcode.cn/problems/gray-code/)

**n 位格雷码序列** 是一个由 `2n` 个整数组成的序列，其中：

- 每个整数都在范围 `[0, 2n - 1]` 内（含 `0` 和 `2n - 1`）
- 第一个整数是 `0`
- 一个整数在序列中出现 **不超过一次**
- 每对 **相邻** 整数的二进制表示 **恰好一位不同** ，且
- **第一个** 和 **最后一个** 整数的二进制表示 **恰好一位不同**

给你一个整数 `n` ，返回任一有效的 **n 位格雷码序列** 。

==位运算==

```java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList<>(1 << n);
        int lastSize = 1;
        res.add(0, 0);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < lastSize; j++) {
                res.add(res.get(lastSize - j - 1) + lastSize);
            }
            lastSize <<= 1;        
        }
        return res;
    }
}
```

# Leetcode[96. 不同的二叉搜索树](https://leetcode.cn/problems/unique-binary-search-trees/)

给你一个整数 `n` ，求恰由 `n` 个节点组成且节点值从 `1` 到 `n` 互不相同的 **二叉搜索树** 有多少种？返回满足题意的二叉搜索树的种数。

==动态规划==

```java
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n + 1; i++) {
            for (int j = 1; j < i + 1; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

```

# Leetcode[98. 验证二叉搜索树](https://leetcode.cn/problems/validate-binary-search-tree/)

给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。

有效 二叉搜索树定义如下：

节点的左子树只包含 小于 当前节点的数。
节点的右子树只包含 大于 当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。

==递归==

`时间O(n) 空间O(n)`

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper) {
            return false;
        }
        return isValidBST(node.left, lower, node.val) && isValidBST(node.right, node.val, upper);
    }
}
```

==中序遍历==

`时间O(n) 空间O(n)`

```java
class Solution {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        double inorder = -Double.MAX_VALUE;

        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
              // 如果中序遍历得到的节点的值小于等于前一个 inorder，说明不是二叉搜索树
            if (root.val <= inorder) {
                return false;
            }
            inorder = root.val;
            root = root.right;
        }
        return true;
    }
}
```

```java
class Solution {
    long pre = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        if (root == null) {
            return true;
        }
        // 访问左子树
        if (!isValidBST(root.left)) {
            return false;
        }
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) {
            return false;
        }
        pre = root.val;
        // 访问右子树
        return isValidBST(root.right);
    }
}
```

# Leetcode[101. 对称二叉树](https://leetcode.cn/problems/symmetric-tree/)

给你一个二叉树的根节点 `root` ， 检查它是否轴对称。

==递归==

```java
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null)
            return right == null;
        if (right == null)
            return left == null;
        if (left.val != right.val) 
            return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.l=eft);
    }
}
```

==迭代==

```java
class Solution {
	public boolean isSymmetric(TreeNode root) {
		if(root==null || (root.left==null && root.right==null)) {
			return true;
		}
		//用队列保存节点
		LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
		//将根节点的左右孩子放到队列中
		queue.add(root.left);
		queue.add(root.right);
		while(queue.size()>0) {
			//从队列中取出两个节点，再比较这两个节点
			TreeNode left = queue.removeFirst();
			TreeNode right = queue.removeFirst();
			//如果两个节点都为空就继续循环，两者有一个为空就返回false
			if(left==null && right==null) {
				continue;
			}
			if(left==null || right==null) {
				return false;
			}
			if(left.val!=right.val) {
				return false;
			}
			//将左节点的左孩子， 右节点的右孩子放入队列
			queue.add(left.left);
			queue.add(right.right);
			//将左节点的右孩子，右节点的左孩子放入队列
			queue.add(left.right);
			queue.add(right.left);
		}
		
		return true;
	}
}

```

# Leetcode[102. 二叉树的层序遍历](https://leetcode.cn/problems/binary-tree-level-order-traversal/)

给你二叉树的根节点 `root` ，返回其节点值的 **层序遍历** 。 （即逐层地，从左到右访问所有节点）。

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        if (root != null) queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                temp.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            ans.add(temp);
        }
        return ans;
    }
} 
```

# Leetcode[104. 二叉树的最大深度](https://leetcode.cn/problems/maximum-depth-of-binary-tree/)

给定一个二叉树，找出其最大深度。

二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。

**说明:** 叶子节点是指没有子节点的节点。

```java
class Solution {
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }
    private int maxDepth(TreeNode root, int depth) {
        if (root == null) return depth;
        depth++;
        return Math.max(maxDepth(root.left), maxDepth(root.right));
    }

}
```

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```

# Leetcode[105. 从前序与中序遍历序列构造二叉树](https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。

见剑指OfferII07

# Leetcode [114. 二叉树展开为链表](https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/)

==借助缓存数组==

`时间O(n) 空间O(n)`

```java
class Solution {
    List<TreeNode> traverse = new ArrayList<>();
    public void flatten(TreeNode root) {
        dfs(root);
        for (int i = 1; i < traverse.size(); i++) {
            root.left = null;
            root = root.right = traverse.get(i);
        }
    }
    private void dfs(TreeNode node) {
        if (node == null) return ;
        traverse.add(node);
        dfs(node.left);
        dfs(node.right);
    }
}
```

==后序遍历==

```java
class Solution {
    private TreeNode pre = null;

    public void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
}
```

==先序遍历==

```java
class Solution {
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.left);
        TreeNode right = root.right;
        root.right = root.left;
        root.left = null;
        while (root.right != null) {
            root = root.right;
        }
        flatten(right);
        root.right = right;
    }
}
```

# Leetcode[121. 买卖股票的最佳时机](https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/)

你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。

返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。

==动态规划==

```java
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res = Math.max(res, prices[i] - prices[i - 1]);
            prices[i] = Math.min(prices[i - 1], prices[i]);
        }
        return res;
    }
}
```

```java
public class Solution {
    public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice) {
                minprice = prices[i];
            } else if (prices[i] - minprice > maxprofit) {
                maxprofit = prices[i] - minprice;
            }
        }
        return maxprofit;
    }
}
```

# Leetcode[124. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/)

路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列。同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点。

路径和 是路径中各节点值的总和。

给你一个二叉树的根节点 root ，返回其 最大路径和 。

```java
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
```

# Leetode [128. 最长连

# 续序列](https://leetcode.cn/problems/longest-consecutive-sequence/)

给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。

请你设计并实现时间复杂度为 O(n) 的算法解决此问题。

==哈希表==

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        Set<Integer> num_set = new HashSet<Integer>();
        for (int num : nums) {
            num_set.add(num);
        }

        int longestStreak = 0;

        for (int num : num_set) {
            if (!num_set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (num_set.contains(currentNum + 1)) {
                    currentNum += 1;
                    currentStreak += 1;
                }

                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
```

==哈希表记录右边界==

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        // key表示num，value表示num最远到达的连续右边界
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化每个num的右边界为自己
        for (int num : nums) {
            map.put(num, num);
        }

        int ans = 0;
        for (int num : nums) {
            if (!map.containsKey(num - 1)) {
                int right = map.get(num);
                // 遍历得到最远的右边界
                while (map.containsKey(right + 1)) {
                    right = map.get(right + 1);
                }
                // 更新右边界
                map.put(num, right);
                // 更新答案
                ans = Math.max(ans, right - num + 1);
            }
            
        }
        return ans;
    }
}
```

==哈希表 动态规划==

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        // key表示num，value表示num所在连续区间的长度
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int num : nums) {
            // 当map中不包含num，也就是num第一次出现
            if (!map.containsKey(num)) {
                // left为num-1所在连续区间的长度，更进一步理解为：左连续区间的长度
                int left = map.getOrDefault(num - 1, 0);
                // right为num+1所在连续区间的长度，更进一步理解为：右连续区间的长度
                int right = map.getOrDefault(num + 1, 0);
                // 当前连续区间的总长度
                int curLen = left + right + 1;
                ans = Math.max(ans, curLen);
                // 将num加入map中，表示已经遍历过该值。其对应的value可以为任意值。
                map.put(num, -1);
                // 更新当前连续区间左边界和右边界对应的区间长度
                map.put(num - left, curLen);
                map.put(num + right, curLen);
            }
        }
        return ans;
    }
}
```

==并查集==

```java
class UnionFind {
    // 记录每个节点的父节点
    private Map<Integer, Integer> parent;

    public UnionFind(int[] nums) {
        parent = new HashMap<>();
        // 初始化父节点为自身
        for (int num : nums) {
            parent.put(num, num);
        }
    }

    // 寻找x的父节点，实际上也就是x的最远连续右边界，这点类似于方法2
    public Integer find(int x) {
        // nums不包含x
        if (!parent.containsKey(x)) {
            return null;
        }
        // 遍历找到x的父节点
        while (x != parent.get(x)) {
            // 进行路径压缩，不写下面这行也可以，但是时间会慢些
            parent.put(x, parent.get(parent.get(x)));
            x = parent.get(x);
        }
        return x;
    }

    // 合并两个连通分量，在本题中只用来将num并入到num+1的连续区间中
    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) {
            return;
        }
        parent.put(rootX, rootY);
    }
}

class Solution {
    public int longestConsecutive(int[] nums) {
        UnionFind uf = new UnionFind(nums);
        int ans = 0;
        
        for (int num : nums) {
            // 当num+1存在，将num合并到num+1所在集合中
            if (uf.find(num + 1) != null) {
                uf.union(num, num + 1);
            }
        }

        for (int num : nums) {
            // 找到num的最远连续右边界
            int right = uf.find(num);
            ans = Math.max(ans, right - num + 1);
        }
        return ans;
    }
}

```



# Leetcode[136. 只出现一次的数字](https://leetcode.cn/problems/single-number/)

给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。

说明：

你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？

```java
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int i : nums) ans ^= i;
        return ans;
    }
}
```



# Leetcode[139. 单词拆分](https://leetcode.cn/problems/word-break/)

给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。

注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。

==字典树==

```java
class Solution {
    Trie root;
    boolean[] failed = new boolean[301];
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie root = new Trie();
        for (String str : wordDict) {
            Trie node = root;
            for (char c : str.toCharArray()) {
                node.putIfAbsent(c, new Trie());
                node = node.get(c);
            }
            node.put('#', new Trie());
        }
        this.root = root;
        return isWordBreak(root, s, 0);
    }
    private boolean isWordBreak(Trie trie, String s, int index) {
        if (failed[index] && trie == root) return false;
        if (index == s.length()) return trie.containsKey('#');
        boolean res = false;
        if (trie.containsKey('#')) {
            res = res || isWordBreak(root, s, index);
            failed[index] = !res;
        }
        if (res) return res;
        char c = s.charAt(index);
        if (trie.containsKey(c)) res = res || isWordBreak(trie.get(c), s, index + 1);
        return res;
    }
}
class Trie extends HashMap<Character, Trie> {
}
```



```java
class Solution {
    // 1ms
    public boolean wordBreak(String s, List<String> wordDict) {
    Trie trie = new Trie();

    for (String word : wordDict)
        trie.insert(word.toCharArray());

    return trie.find(s, 0);
    }

    class TrieNode {
    public TrieNode[] children = new TrieNode[26];
    public boolean isEndingChar = false;
    }

    class Trie {
    private TrieNode root = new TrieNode();
    boolean[] failed = new boolean[301]; // s.length <= 300

    public void insert(char[] text) {
        TrieNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                TrieNode newNode = new TrieNode();
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.isEndingChar = true;
    }

    public boolean find(String s, int i) {
        if (failed[i])
            return false;

        if (i >= s.length())
            return true;
        TrieNode p = root;
        for (; i < s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if (p.children[index] == null) {
                return false;
            }
            p = p.children[index];
            if (p.isEndingChar) {
                if (find(s, i + 1))
                    return true;
                failed[i + 1] = true;
            }
        }
        return false;
    }
    }
}
```

==动态规划==

```java
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
```

# Leetcode[141. 环形链表](https://leetcode.cn/problems/linked-list-cycle/)

与Leetcode202大同小异

==哈希集合==

`时间O(n) 空间O(n)`

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<ListNode>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}
```

==快慢指针==

`时间O(n) 空间O(1)`

```java
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }
}

public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return false;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
    }
}
```

# Leetcode[142. 环形链表 II](https://leetcode.cn/problems/linked-list-cycle-ii/)

给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。

如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。

不允许修改 链表。

==哈希集合==

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return head;
            }
            set.add(head);
            head = head.next;
        }
        return null;
    }
}
```

==快慢指针==

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode slow = head.next, fast = head.next.next;
        while (slow != null && fast != null && fast.next != null) {
            if (fast == slow) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return fast;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return null;
    }
}
```

```java
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
```

# Leetcode[143. 重排链表](https://leetcode.cn/problems/reorder-list/)

给定一个单链表 L 的头节点 head ，单链表 L 表示为：

L0 → L1 → … → Ln - 1 → Ln
请将其重新排列后变为：

L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

==栈==

```java
class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return ;
        ListNode temp = head.next, temp1 = null;
        int length = 0, count = 0;
        Deque<ListNode> stack = new ArrayDeque<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
            length++;
        }
        while (head.next != null) {
            if (count++ >= length / 2) break;
            temp = stack.pop();
            temp1 = head.next;
            head.next = temp;
            temp.next = temp1;
            head = temp1;
        }
        if (length % 2 == 0) head.next = null;
        else head.next.next = null;
    }
}
```

==快慢指针==

```java
class Solution {
    public void reorderList(ListNode head) {
        ListNode mid = middleNode(head);
        ListNode head2 = reverseList(mid);
        while (head2.next != null) {
            ListNode nxt = head.next;
            ListNode nxt2 = head2.next;
            head.next = head2;
            head2.next = nxt;
            head = nxt;
            head2 = nxt2;
        }
    }

    // 876. 链表的中间结点
    private ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // 206. 反转链表
    private ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode nxt = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nxt;
        }
        return pre;
    }
}
```



# Leetcode[146. LRU 缓存](https://leetcode.cn/problems/lru-cache/)

请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
实现 LRUCache 类：
LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。

==LinkedHashMap==

```java
class LRUCache extends LinkedHashMap<Integer, Integer>{
    private int capacity;
    
    public LRUCache(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        return super.getOrDefault(key, -1);
    }

    // 这个可不写
    public void put(int key, int value) {
        super.put(key, value);
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
        return size() > capacity; 
    }
}

```

```java
class LRUCache {
    Map<Integer, Integer> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new LinkedHashMap<>();
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.get(key);
            map.remove(key);
            map.put(key, value);
        }
        return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            map.remove(key);
            map.put(key, value);
        }
        else {
            if (map.size() == capacity) {
                Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
                int removeKey = iter.next().getKey();
                map.remove(removeKey);
            }
            map.put(key, value);
        }
    }
}
```

==哈希表+双向链表==

```java
public class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int _key, int _value) {key = _key; value = _value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }
}
```

# Leetcode[148. 排序链表](https://leetcode.cn/problems/sort-list/)

给你链表的头结点 `head` ，请将其按 **升序** 排列并返回 **排序后的链表** 。

==归并排序==

```java
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fast = head.next, slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }
}
```

# Leetcode[152. 乘积最大子数组](https://leetcode.cn/problems/maximum-product-subarray/)

给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。

测试用例的答案是一个 32-位 整数。

子数组 是数组的连续子序列。

==动态规划==

```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = Integer.MIN_VALUE, imax = 1, imin = 1;
        for(int i=0; i<nums.length; i++){
            if(nums[i] < 0){ 
              int tmp = imax;
              imax = imin;
              imin = tmp;
            }
            imax = Math.max(imax*nums[i], nums[i]);
            imin = Math.min(imin*nums[i], nums[i]);
            
            max = Math.max(max, imax);
        }
        return max;
    }
}
```

# Leetcode[155. 最小栈](https://leetcode.cn/problems/min-stack/)

设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。

实现 MinStack 类:

MinStack() 初始化堆栈对象。
void push(int val) 将元素val推入堆栈。
void pop() 删除堆栈顶部的元素。
int top() 获取堆栈顶部的元素。
int getMin() 获取堆栈中的最小元素。

==辅助栈==

```java
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
```

# Leetcode[160. 相交链表](https://leetcode.cn/problems/intersection-of-two-linked-lists/)

给你两个单链表的头节点 `headA` 和 `headB` ，请你找出并返回两个单链表相交的起始节点。如果两个链表不存在相交节点，返回 `null` 。

```java
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode tmpA = headA, tmpB = headB;
        while (!(headA == null && headB == null)) {
            if (headA == headB) return headA;
            headA = headA == null ? tmpB : headA.next;
            headB = headB == null ? tmpA : headB.next;
        }
        return null;
    }
}
```

# Leetcode169多数元素

同剑指Offer39

# Leetcode[198. 打家劫舍](https://leetcode.cn/problems/house-robber/)

你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。

给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。

==动态规划==

`时间O(n) 空间O(n)`

```java
class Solution {
    public int rob(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[1] = nums[0];
        for (int i = 2; i < len + 1; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[len];
    }
}
```

`时间O(n) 空间O(1)`

```java
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        if (length == 1) {
            return nums[0];
        }
        int first = nums[0], second = Math.max(nums[0], nums[1]);
        for (int i = 2; i < length; i++) {
            int temp = second;
            second = Math.max(first + nums[i], second);
            first = temp;
        }
        return second;
    }
}
```

# Leetcode[213. 打家劫舍 II](https://leetcode.cn/problems/house-robber-ii/)

你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都 **围成一圈** ，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，**如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警** 。

给定一个代表每个房屋存放金额的非负整数数组，计算你 **在不触动警报装置的情况下** ，今晚能够偷窃到的最高金额。

==动态规划==

```java
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];
        return Math.max(myRob(Arrays.copyOfRange(nums, 0, nums.length - 1)), 
                        myRob(Arrays.copyOfRange(nums, 1, nums.length)));
    }
    private int myRob(int[] nums) {
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}
```

# Leetcode[337. 打家劫舍 III](https://leetcode.cn/problems/house-robber-iii/)

小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为 `root` 。

除了 `root` 之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果 **两个直接相连的房子在同一天晚上被打劫** ，房屋将自动报警。

给定二叉树的 `root` 。返回 ***在不触动警报的情况下** ，小偷能够盗取的最高金额* 。

==动态规划 DFS==

```java
class Solution {
    public int rob(TreeNode root) {
        int[] rootStatus = dfs(root);
        return Math.max(rootStatus[0], rootStatus[1]);
    }

    public int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{0, 0};
        }
        int[] l = dfs(node.left);
        int[] r = dfs(node.right);
        int selected = node.val + l[1] + r[1];
        int notSelected = Math.max(l[0], l[1]) + Math.max(r[0], r[1]);
        return new int[]{selected, notSelected};
    }
}
```

# Leetcode [2560. 打家劫舍 IV](https://leetcode.cn/problems/house-robber-iv/)

沿街有一排连续的房屋。每间房屋内都藏有一定的现金。现在有一位小偷计划从这些房屋中窃取现金。

由于相邻的房屋装有相互连通的防盗系统，所以小偷 **不会窃取相邻的房屋** 。

小偷的 **窃取能力** 定义为他在窃取过程中能从单间房屋中窃取的 **最大金额** 。

给你一个整数数组 `nums` 表示每间房屋存放的现金金额。形式上，从左起第 `i` 间房屋中放有 `nums[i]` 美元。

另给你一个整数 `k` ，表示窃贼将会窃取的 **最少** 房屋数。小偷总能窃取至少 `k` 间房屋。

返回小偷的 **最小** 窃取能力。

==二分答案 动态规划==

```java
class Solution {
    public int minCapability(int[] nums, int k) {
        int left = 0, right = (int) 1e9;
        while (left < right) {
            int mid = (left + right) >>> 1;
            int f0 = 0, f1 = 0;
            for (int x : nums)
                if (x > mid) f0 = f1;
                else {
                    int tmp = f1;
                    f1 = Math.max(f1, f0 + 1);
                    f0 = tmp;
                }
            if (f1 >= k) right = mid;
            else left = mid + 1;
        }
        return right;
    }
}
```

```java
class Solution {
    public int minCapability(int[] nums, int k) {
        int left = 0, right = (int) 1e9;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            int f0 = 0, f1 = 0;
            for (int x : nums)
                if (x > mid) f0 = f1;
                else {
                    int tmp = f1;
                    f1 = Math.max(f1, f0 + 1);
                    f0 = tmp;
                }
            if (f1 >= k) right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }
}
```



# Leetcode[200. 岛屿数量](https://leetcode.cn/problems/number-of-islands/)

给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。

岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。

此外，你可以假设该网格的四条边均被水包围。

==DFS==

```java
class Solution {
    void dfs(char[][] grid, int r, int c) {
        int nr = grid.length;
        int nc = grid[0].length;

        if (r < 0 || c < 0 || r >= nr || c >= nc || grid[r][c] == '0') {
            return;
        }

        grid[r][c] = '0';
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        int num_islands = 0;
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    ++num_islands;
                    dfs(grid, r, c);
                }
            }
        }

        return num_islands;
    }
}
```

==并查集==

```java
class Solution {
    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int nr = grid.length;
        int nc = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int r = 0; r < nr; ++r) {
            for (int c = 0; c < nc; ++c) {
                if (grid[r][c] == '1') {
                    if (r - 1 >= 0 && grid[r-1][c] == '1') {
                        uf.union(r * nc + c, (r-1) * nc + c);
                    }
                    if (r + 1 < nr && grid[r+1][c] == '1') {
                        uf.union(r * nc + c, (r+1) * nc + c);
                    }
                    if (c - 1 >= 0 && grid[r][c-1] == '1') {
                        uf.union(r * nc + c, r * nc + c - 1);
                    }
                    if (c + 1 < nc && grid[r][c+1] == '1') {
                        uf.union(r * nc + c, r * nc + c + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }
}
```



# Leetcode[202. 快乐数](https://leetcode.cn/problems/happy-number/)

编写一个算法来判断一个数 n 是不是快乐数。

「快乐数」 定义为：

对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
如果这个过程 结果为 1，那么这个数就是快乐数。
如果 n 是 快乐数 就返回 true ；不是，则返回 false 。

==哈希集合==

  ```java
class Solution {
    private int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        Set<Integer> seen = new HashSet<>();
        while (n != 1 && !seen.contains(n)) {
            seen.add(n);
            n = getNext(n);
        }
        return n == 1;
    }
}
  ```

==快慢指针法==

```java
class Solution {

     public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappy(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}
```

# Leetcode[207. 课程表](https://leetcode.cn/problems/course-schedule/)

你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。

在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程  bi 。

例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。
请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。

==拓扑排序 DFS==

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> adjacency = new ArrayList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        int[] flags = new int[numCourses];
        for(int[] cp : prerequisites)
            adjacency.get(cp[1]).add(cp[0]);
        for(int i = 0; i < numCourses; i++)
            if(!dfs(adjacency, flags, i)) return false;
        return true;
    }
    private boolean dfs(List<List<Integer>> adjacency, int[] flags, int i) {
        if(flags[i] == 1) return false;
        if(flags[i] == -1) return true;
        flags[i] = 1;
        for(Integer j : adjacency.get(i))
            if(!dfs(adjacency, flags, j)) return false;
        flags[i] = -1;
        return true;
    }
}
```

==拓扑排序 BFS==

```java
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegrees = new int[numCourses];
        List<List<Integer>> adjacency = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++)
            adjacency.add(new ArrayList<>());
        // Get the indegree and adjacency of every course.
        for(int[] cp : prerequisites) {
            indegrees[cp[0]]++;
            adjacency.get(cp[1]).add(cp[0]);
        }
        // Get all the courses with the indegree of 0.
        for(int i = 0; i < numCourses; i++)
            if(indegrees[i] == 0) queue.add(i);
        // BFS TopSort.
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            numCourses--;
            for(int cur : adjacency.get(pre))
                if(--indegrees[cur] == 0) queue.add(cur);
        }
        return numCourses == 0;
    }
}
```

# Leetcode[208. 实现 Trie (前缀树)](https://leetcode.cn/problems/implement-trie-prefix-tree/)

Trie（发音类似 "try"）或者说 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键。这一数据结构有相当多的应用情景，例如自动补完和拼写检查。

请你实现 Trie 类：

Trie() 初始化前缀树对象。
void insert(String word) 向前缀树中插入字符串 word 。
boolean search(String word) 如果字符串 word 在前缀树中，返回 true（即，在检索之前已经插入）；否则，返回 false 。
boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false 。

==字典树==

```java
class Trie {
    Trie[] next;
    boolean isEnding;

    public Trie() {
        next = new Trie[26];
        isEnding = false;
    }
    
    public void insert(String word) {
        Trie temp = this;
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (temp.next[index] == null) temp.next[index] = new Trie();
            temp = temp.next[index]; 
        }
        temp.isEnding = true;
    }
    
    public boolean search(String word) {
        Trie temp = this;
        for (char c : word.toCharArray()) {
            if (temp.next[c - 'a'] == null) return false;
            temp = temp.next[c - 'a'];
        }
        return temp.isEnding;
    }
    
    public boolean startsWith(String prefix) {
        Trie temp = this;
        for (char c : prefix.toCharArray()) {
            if (temp.next[c - 'a'] == null) return false;
            temp = temp.next[c - 'a'];
        }
        return true;
    }
}
```

# Leetcode[215. 数组中的第K个最大元素](https://leetcode.cn/problems/kth-largest-element-in-an-array/)

给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。

请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。

你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

类似于剑指Offer40

==快速排序==

```java
class Solution {
    Random random = new Random();

    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quickSelect(int[] a, int l, int r, int index) {
        int q = randomPartition(a, l, r);
        if (q == index) {
            return a[q];
        } else {
            return q < index ? quickSelect(a, q + 1, r, index) : quickSelect(a, l, q - 1, index);
        }
    }

    public int randomPartition(int[] a, int l, int r) {
        int i = random.nextInt(r - l + 1) + l;
        swap(a, i, r);
        return partition(a, l, r);
    }

    public int partition(int[] a, int l, int r) {
        int x = a[r], i = l - 1;
        for (int j = l; j < r; ++j) {
            if (a[j] <= x) {
                swap(a, ++i, j);
            }
        }
        swap(a, i + 1, r);
        return i + 1;
    }

    public void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
```

# Leetcode[221. 最大正方形](https://leetcode.cn/problems/maximal-square/)

在一个由 `'0'` 和 `'1'` 组成的二维矩阵内，找到只包含 `'1'` 的最大正方形，并返回其面积。

与Leetcode84、Leetcode85类似之处，但LC84 85使用基于栈的方法来解决问题。

==动态规划==

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int max = 0;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '1') {
                    dp[i][j] = Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                    max = Math.max(max, dp[i][j]);
                }
            }
        }
        return max * max;
    }
}
```

# Leetcode[229. 多数元素 II](https://leetcode.cn/problems/majority-element-ii/)

给定一个大小为 *n* 的整数数组，找出其中所有出现超过 `⌊ n/3 ⌋` 次的元素。

==哈希表==

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (int num : nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int thre = nums.length / 3;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > thre) res.add(entry.getKey());
        }
        return res;
    }
}
```

==摩尔投票==

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        int n = nums.length;
        int a = 0, b = 0;
        int c1 = 0, c2 = 0;
        for (int i : nums) {
            if (c1 != 0 && a == i) c1++;
            else if (c2 != 0 && b == i) c2++;
            else if (c1 == 0 && ++c1 >= 0) a = i;
            else if (c2 == 0 && ++c2 >= 0) b = i;
            else {
                c1--; c2--;
            }
        }
        c1 = 0; c2 = 0;
        for (int i : nums) {
            if (a == i) c1++;
            else if (b == i) c2++;
        }
        List<Integer> ans = new ArrayList<>();
        if (c1 > n / 3) ans.add(a);
        if (c2 > n / 3) ans.add(b);
       return ans;
    }
}
```

# Leetcode[230. 二叉搜索树中第K小的元素](https://leetcode.cn/problems/kth-smallest-element-in-a-bst/)

给定一个二叉搜索树的根节点 `root` ，和一个整数 `k` ，请你设计一个算法查找其中第 `k` 个最小元素（从 1 开始计数）。

==递归==

```java
class Solution {
    int counts = 0;
    public int kthSmallest(TreeNode root, int k) {
        if (root == null) return -1;
        int tmp = kthSmallest(root.left, k);
        if (tmp >= 0) return tmp;
        counts++;
        if (counts == k) return root.val;
        tmp = kthSmallest(root.right, k);
        if (tmp >= 0) return tmp;
        return -1;
    }
}
```

==中序遍历==

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<TreeNode>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            --k;
            if (k == 0) {
                break;
            }
            root = root.right;
        }
        return root.val;
    }
}

```



# Leetcode[231. 2 的幂](https://leetcode.cn/problems/power-of-two/)

给你一个整数 n，请你判断该整数是否是 2 的幂次方。如果是，返回 true ；否则，返回 false 。

如果存在一个整数 x 使得 n == 2x ，则认为 n 是 2 的幂次方。

==快速幂==

```java
class Solution {
    public double myPow(double x, int n) {
        double res = 1;
        long b = n;
        if (b < 0) {
            x = 1.0 / x;
            b = -b;
        }
        while (b > 0) {
            if ((b & 1) > 0)res = res * x;
            x = x * x;
            b >>= 1;
        }
        return res;
    }
}
```

# Leetcode[235. 二叉搜索树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/)

见剑指Offer68

# Leetcode[236. 二叉树的最近公共祖先](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/)

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”

见剑指Offer68

# Leetcode[238. 除自身以外数组的乘积](https://leetcode.cn/problems/product-of-array-except-self/)

同剑指Offer66

# Leetcode[239. 滑动窗口最大值](https://leetcode.cn/problems/sliding-window-maximum/)

同剑指Offer59

# Leetcode[240. 搜索二维矩阵 II](https://leetcode.cn/problems/search-a-2d-matrix-ii/)

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

每行的元素从左到右升序排列。
每列的元素从上到下升序排列。


同剑指Offer04

#  Leetcode[279. 完全平方数](https://leetcode.cn/problems/perfect-squares/)

给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。

完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。

==动态规划==

```java
class Solution {
    public int numSquares(int n) {
        int[] f = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int minn = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                minn = Math.min(minn, f[i - j * j]);
            }
            f[i] = minn + 1;
        }
        return f[n];
    }
}
```

# Leetcode[283. 移动零](https://leetcode.cn/problems/move-zeroes/)

给定一个数组 `nums`，编写一个函数将所有 `0` 移动到数组的末尾，同时保持非零元素的相对顺序。

**请注意** ，必须在不复制数组的情况下原地对数组进行操作。

==双指针==

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int n = nums.length, left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                swap(nums, left, right);
                left++;
            }
            right++;
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
```

# Leetcode[287. 寻找重复数](https://leetcode.cn/problems/find-the-duplicate-number/)

给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。

假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。

你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。

==位图==

```java
class Solution {
    public int findDuplicate(int[] nums) {
         if (nums.length==2){
            return nums[0];
        }
        BitSet bitSet=new BitSet();
        for (int i = 0; i <nums.length ; i++) {
            int num = nums[i];
            if (bitSet.get(num)){
                return num;
            }else {
                bitSet.set(num,true);
            }
        }
        return 0;
    }
}
```

==快慢指针==

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do {
            slow = nums[slow];
            fast = nums[nums[fast]];
        } while (slow != fast);
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }
}
```

# Leetcode[292. 石子游戏 Nim 游戏](https://leetcode.cn/problems/nim-game/)

你和你的朋友，两个人一起玩 Nim 游戏：

桌子上有一堆石头。
你们轮流进行自己的回合， 你作为先手 。
每一回合，轮到的人拿掉 1 - 3 块石头。
拿掉最后一块石头的人就是获胜者。
假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。

```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```

# Leetcode[300. 最长递增子序列](https://leetcode.cn/problems/longest-increasing-subsequence/)

==动态规划==

```java
// Dynamic programming.
class Solution {
    public int lengthOfLIS(int[] nums) {
        if(nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        int res = 0;
        Arrays.fill(dp, 1);
        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) dp[i] = Math.max(dp[i], dp[j] + 1);
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

==动态规划+二分法==

```java
// Dynamic programming + Dichotomy.
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int res = 0;
        for(int num : nums) {
            int i = 0, j = res;
            while(i < j) {
                int m = (i + j) / 2;
                if(tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if(res == j) res++;
        }
        return res;
    }
}
```

# Leetcode[338. 比特位计数](https://leetcode.cn/problems/counting-bits/)

给你一个整数 `n` ，对于 `0 <= i <= n` 中的每个 `i` ，计算其二进制表示中 **`1` 的个数** ，返回一个长度为 `n + 1` 的数组 `ans` 作为答案。

==自己的==

`时间O(nlogn) 空间O(n)`

```java
class Solution {
    public int[] countBits(int n) {
        int[] res = new int[n + 1];
        for (int i = 0; i <= n; i++) res[i] = countBit(i);
        return res;
    }

    private int countBit(int num) {
        int ans = 0;
        for (int i = 0; i < 17; i++) {
            if ((num & 1) > 0) ans++;
            num >>= 1;
        }
        return ans;
    }
}
```

==Brian Kernighan 算法==

`时间O(nlogn) 空间O(n)`

```java
class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            bits[i] = countOnes(i);
        }
        return bits;
    }

    public int countOnes(int x) {
        int ones = 0;
        while (x > 0) {
            x &= (x - 1);
            ones++;
        }
        return ones;
    }
}
```

==动态规划==

`时间O(n) 空间O(1)`

```java
class Solution {
    public int[] countBits(int n) {
        int[] bits = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            bits[i] = bits[i & (i - 1)] + 1;
        }
        return bits;
    }
}
```

# Leetcode[448. 找到所有数组中消失的数字](https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/)

给你一个含 n 个整数的数组 nums ，其中 nums[i] 在区间 [1, n] 内。请你找出所有在 [1, n] 范围内但没有出现在 nums 中的数字，并以数组的形式返回结果。

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        boolean[] flags = new boolean[nums.length + 1];
        for (int i : nums) {
            flags[i] = true;
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!flags[i]) res.add(i);
        }
        return res;
    }
}
```

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new LinkedList<>();
        boolean[] flags = new boolean[nums.length + 1];
        for (int i : nums) {
            flags[i] = true;
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!flags[i]) res.add(i);
        }
        return res;
    }
}
```

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        int n = nums.length;
        for (int num : nums) {
            int x = (num - 1) % n;
            nums[x] += n;
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (nums[i] <= n) {
                ret.add(i + 1);
            }
        }
        return ret;
    }
}
```

 # Leetcode[455. 分发饼干](https://leetcode.cn/problems/assign-cookies/)

假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。

对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。

 ==贪心==

```java
class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int m = g.length, n = s.length;
        int count = 0;
        for (int i = 0, j = 0; i < m && j < n; i++, j++) {
            while (j < n && g[i] > s[j]) {
                j++;
            }
            if (j < n) {
                count++;
            }
        }
        return count;
    }
}
```

# Leetcode[462. 最小操作次数使数组元素相等 II](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/)

给你一个长度为 `n` 的整数数组 `nums` ，返回使所有数组元素相等需要的最小操作数。

在一次操作中，你可以使数组中的一个元素加 `1` 或者减 `1` 。

==中位数贪心==

```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, ret = 0, x = nums[n / 2];
        return Arrays.stream(nums).map(s -> Math.abs(s - x)).sum();
    }
}
```

# Leecode[486. 预测赢家](https://leetcode.cn/problems/predict-the-winner/)

给你一个整数数组 nums 。玩家 1 和玩家 2 基于这个数组设计了一个游戏。

玩家 1 和玩家 2 轮流进行自己的回合，玩家 1 先手。开始时，两个玩家的初始分值都是 0 。每一回合，玩家从数组的任意一端取一个数字（即，nums[0] 或 nums[nums.length - 1]），取到的数字将会从数组中移除（数组长度减 1 ）。玩家选中的数字将会加到他的得分上。当数组中没有剩余数字可取时，游戏结束。

如果玩家 1 能成为赢家，返回 true 。如果两个玩家得分相等，同样认为玩家 1 是游戏的赢家，也返回 true 。你可以假设每个玩家的玩法都会使他的分数最大化。

与Leetcode877类似。

==递归==

```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return total(nums, 0, nums.length - 1, 1) >= 0;
    }

    public int total(int[] nums, int start, int end, int turn) {
        if (start == end) {
            return nums[start] * turn;
        }
        int scoreStart = nums[start] * turn + total(nums, start + 1, end, -turn);
        int scoreEnd = nums[end] * turn + total(nums, start, end - 1, -turn);
        return Math.max(scoreStart * turn, scoreEnd * turn) * turn;
    }
}
```

==动态规划==

```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int length = nums.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] >= 0;
    }
}

```

```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = nums[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] >= 0;
    }
}
```

# Leetcode [494. 目标和](https://leetcode.cn/problems/target-sum/)

给你一个整数数组 `nums` 和一个整数 `target` 。

向数组中的每个整数前添加 `'+'` 或 `'-'` ，然后串联起所有整数，可以构造一个 **表达式** ：

- 例如，`nums = [2, 1]` ，可以在 `2` 之前添加 `'+'` ，在 `1` 之前添加 `'-'` ，然后串联起来得到表达式 `"+2-1"` 。

返回可以通过上述方法构造的、运算结果等于 `target` 的不同 **表达式** 的数目。

==回溯==

```java
class Solution {
    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        if (index == nums.length) {
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }
}
```

==动态规划== ==01背包==

```java
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int neg = diff / 2;
        int[] dp = new int[neg + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = neg; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[neg];
    }
}
```

# Leetcode [538. 把二叉搜索树转换为累加树](https://leetcode.cn/problems/convert-bst-to-greater-tree/)

给出二叉 搜索 树的根节点，该树的节点值各不相同，请你将其转换为累加树（Greater Sum Tree），使每个节点 node 的新值等于原树中大于或等于 node.val 的值之和。

提醒一下，二叉搜索树满足下列约束条件：

节点的左子树仅包含键 小于 节点键的节点。
节点的右子树仅包含键 大于 节点键的节点。
左右子树也必须是二叉搜索树。

```java
class Solution {
    int prev = 0;
    public TreeNode convertBST(TreeNode root) {
        dfs(root);
        return root;
    }

    private void dfs(TreeNode root) {
        if (root == null) return ;
        dfs(root.right);
        root.val = prev + root.val;
        prev = root.val;
        dfs(root.left);
        
    }
}
```



# Leetcode[543. 二叉树的直径](https://leetcode.cn/problems/diameter-of-binary-tree/)

给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。这条路径可能穿过也可能不穿过根结点。

参考[Leetcode124](#Leetcode[124. 二叉树中的最大路径和](https://leetcode.cn/problems/binary-tree-maximum-path-sum/))

```java
class Solution {
    int MaxPathSum = Integer.MIN_VALUE;;
    public int diameterOfBinaryTree(TreeNode root) {
        maxPath(root);
        return MaxPathSum;
    }
    private int maxPath(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int leftSum = maxPath(root.left);
        int rightSum = maxPath(root.right);
        MaxPathSum = Math.max(MaxPathSum, 2 + leftSum + rightSum);
        return Math.max(leftSum, rightSum) + 1;
    }
}
```

写法跟Leetcode124大同小异

```java
class Solution {
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        maxGain(root);
        return maxSum;
    }

    public int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        // 递归计算左右子节点的最大贡献值
        // 只有在最大贡献值大于 0 时，才会选取对应子节点
        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        // 节点的最大路径和取决于该节点的值与该节点的左右子节点的最大贡献值
        int priceNewpath = node.val + leftGain + rightGain;

        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        // 返回节点的最大贡献值
        return node.val + Math.max(leftGain, rightGain);
    }
}
```

# Leetcode[560. 和为 K 的子数组](https://leetcode.cn/problems/subarray-sum-equals-k/)

给你一个整数数组 `nums` 和一个整数 `k` ，请你统计并返回 *该数组中和为 `k` 的连续子数组的个数* 。

`时间 O(n) 空间O(n)`

==前缀和 哈希表==

```java
public class Solution {
    public int subarraySum(int[] nums, int k) {
        int count = 0, pre = 0;
        Map < Integer, Integer > mp = new HashMap < > ();
        mp.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (mp.containsKey(pre - k)) {
                count += mp.get(pre - k);
            }
            mp.put(pre, mp.getOrDefault(pre, 0) + 1);
        }
        return count;
    }
}
```



# Leetcode[581. 最短无序连续子数组](https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/)

给你一个整数数组 nums ，你需要找出一个 连续子数组 ，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。

请你找出符合题意的 最短 子数组，并输出它的长度。

==双指针==

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        int maxn = Integer.MIN_VALUE, right = -1;
        int minn = Integer.MAX_VALUE, left = -1;
        for (int i = 0; i < n; i++) {
            if (maxn > nums[i]) {
                right = i;
            } else {
                maxn = nums[i];
            }
            if (minn < nums[n - i - 1]) {
                left = n - i - 1;
            } else {
                minn = nums[n - i - 1];
            }
        }
        return right == -1 ? 0 : right - left + 1;
    }
}

```



# Leetcode[617. 合并二叉树](https://leetcode.cn/problems/merge-two-binary-trees/)

给你两棵二叉树： root1 和 root2 。

想象一下，当你将其中一棵覆盖到另一棵之上时，两棵树上的一些节点将会重叠（而另一些不会）。你需要将这两棵树合并成一棵新二叉树。合并的规则是：如果两个节点重叠，那么将这两个节点的值相加作为合并后节点的新值；否则，不为 null 的节点将直接作为新二叉树的节点。

返回合并后的二叉树。

注意: 合并过程必须从两个树的根节点开始。

```java
class Solution {
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return null;
        TreeNode temp = new TreeNode();
        if (root1 == null) {
            temp.val = root2.val;
            temp.left = mergeTrees(null, root2.left);
            temp.right = mergeTrees(null, root2.right);
        } else if (root2 == null) {
            temp.val = root1.val;
            temp.left = mergeTrees(root1.left, null);
            temp.right = mergeTrees(root1.right, null);
        } else {
            temp.val = root1.val + root2.val;
            temp.left = mergeTrees(root1.left, root2.left);
            temp.right  = mergeTrees(root1.right, root2.right);
        }
        return temp;
    }
}
```

```java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        TreeNode merged = new TreeNode(t1.val + t2.val);
        merged.left = mergeTrees(t1.left, t2.left);
        merged.right = mergeTrees(t1.right, t2.right);
        return merged;
    }
}
```

# Leetcode[621. 任务调度器](https://leetcode.cn/problems/task-scheduler/)

给你一个用字符数组 tasks 表示的 CPU 需要执行的任务列表。其中每个字母表示一种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。在任何一个单位时间，CPU 可以完成一个任务，或者处于待命状态。

然而，两个 相同种类 的任务之间必须有长度为整数 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。

你需要计算完成所有任务所需要的 最短时间 。

==贪心==

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        //统计每个任务出现的次数，找到出现次数最多的任务
        int[] hash = new int[26];
        for(int i = 0; i < tasks.length; ++i) {
            hash[tasks[i] - 'A'] += 1;
        }
        Arrays.sort(hash);
        //因为相同元素必须有n个冷却时间，假设A出现3次，n = 2，任务要执行完，至少形成AXX AXX A序列（X看作预占位置）
        //该序列长度为
        int minLen = (n+1) *  (hash[25] - 1) + 1;

        //此时为了尽量利用X所预占的空间（贪心）使得整个执行序列长度尽量小，将剩余任务往X预占的空间插入
        //剩余的任务次数有两种情况：
        //1.与A出现次数相同，比如B任务最优插入结果是ABX ABX AB，中间还剩两个空位，当前序列长度+1
        //2.比A出现次数少，若还有X，则按序插入X位置，比如C出现两次，形成ABC ABC AB的序列
        //直到X预占位置还没插满，剩余元素逐个放入X位置就满足冷却时间至少为n
        for(int i = 24; i >= 0; --i){
            if(hash[i] == hash[25]) ++ minLen;
        }
        //当所有X预占的位置插满了怎么办？
        //在任意插满区间（这里是ABC）后面按序插入剩余元素，比如ABCD ABCD发现D之间距离至少为n+1，肯定满足冷却条件
        //因此，当X预占位置能插满时，最短序列长度就是task.length，不能插满则取最少预占序列长度
        return Math.max(minLen, tasks.length);
    }
}
```

# Leetcode[647. 回文子串](https://leetcode.cn/problems/palindromic-substrings/)

给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。

回文字符串 是正着读和倒过来读一样的字符串。

子字符串 是字符串中的由连续字符组成的一个序列。

具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。

==动态规划==

```java
class Solution {
    public int countSubstrings(String s) {
        int n = s.length();
        boolean[] flags = new boolean[n];
        int res = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                flags[j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || flags[j - 1]);
                if (flags[j]) res++;
            }
        }
        return res;
    }
}
```

==中心扩散==

```java
class Solution {
    public int countSubstrings(String s) {
        int n = s.length(), ans = 0;
        for (int i = 0; i < 2 * n - 1; ++i) {
            int l = i / 2, r = i / 2 + i % 2;
            while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                --l;
                ++r;
                ++ans;
            }
        }
        return ans;
    }
}
```

# Leetcode[718. 最长重复子数组](https://leetcode.cn/problems/maximum-length-of-repeated-subarray/)

给两个整数数组 `nums1` 和 `nums2` ，返回 *两个数组中 **公共的** 、长度最长的子数组的长度* 。

==动态规划==

```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[][] dp = new int[m + 1][n + 1];
        int res = Integer.MIN_VALUE;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (nums1[i - 1] == nums2[j - 1]) dp[i][j] = dp[i - 1][j - 1] + 1;
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
```

```java
class Solution {
    public int findLength(int[] nums1, int[] nums2) {
        int[] dp = new int[nums2.length + 1];
        int result = 0;

        for (int i = 1; i <= nums1.length; i++) {
            for (int j = nums2.length; j > 0; j--) {
                if (nums1[i - 1] == nums2[j - 1]) {
                    dp[j] = dp[j - 1] + 1;
                } else {
                    dp[j] = 0;
                }
                result = Math.max(result, dp[j]);
            }
        }
        return result;
    }
}
```



==动态窗口==

```java
class Solution {
    public int findLength(int[] A, int[] B) {
        int n = A.length, m = B.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            int len = Math.min(m, n - i);
            int maxlen = maxLength(A, B, i, 0, len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(A, B, 0, i, len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    public int maxLength(int[] A, int[] B, int addA, int addB, int len) {
        int ret = 0, k = 0;
        for (int i = 0; i < len; i++) {
            if (A[addA + i] == B[addB + i]) {
                k++;
            } else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }
}
```



# Leetcode[739. 每日温度](https://leetcode.cn/problems/daily-temperatures/)

给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。

==栈==

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> stack = new ArrayDeque<>();
        int len = temperatures.length;
        int[] res = new int[len];
        for (int i = len - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peek()]) {
                stack.pop();
            }
            if (stack.isEmpty()) res[i] = 0;
            else res[i] = stack.peek() - i;
            stack.push(i);
        }
        return res;
    }
}
```

# Leetcode[766. 托普利茨矩阵](https://leetcode.cn/problems/toeplitz-matrix/)

==自己的==

```java
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int j = 0; j < n; j++) {
            int temp = matrix[0][j];
            int k = 1, l = j + 1;
            while (k < m && l < n) {
                if (temp != matrix[k++][l++]) return false;
            }
        }

        for (int i = 1; i < m; i++) {
            int temp = matrix[i][0];
            int k = i + 1, l = 1;
            while (k < m && l < n) {
                if (temp != matrix[k++][l++]) return false;
            }
        }

        return true;
    }
}
```

```java
class Solution {
    public boolean isToeplitzMatrix(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] != matrix[i - 1][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

# Leetcode[876. 链表的中间结点](https://leetcode.cn/problems/middle-of-the-linked-list/)

给定一个头结点为 `head` 的非空单链表，返回链表的中间结点。

如果有两个中间结点，则返回第二个中间结点。

==快慢指针==

```java
class Solution {
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode slow = head, quick = head.next;
        while (quick != null && quick.next != null) {
            slow = slow.next;
            quick = quick.next.next;
        }
        return quick == null ? slow : slow.next;
    }
}
```

 ```java
class Solution {
    public ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
 ```



# Leetcode[877. 石子游戏](https://leetcode.cn/problems/stone-game/)

Alice 和 Bob 用几堆石子在做游戏。一共有偶数堆石子，排成一行；每堆都有 正 整数颗石子，数目为 piles[i] 。

游戏以谁手中的石子最多来决出胜负。石子的 总数 是 奇数 ，所以没有平局。

Alice 和 Bob 轮流进行，Alice 先开始 。 每回合，玩家从行的 开始 或 结束 处取走整堆石头。 这种情况一直持续到没有更多的石子堆为止，此时手中 石子最多 的玩家 获胜 。

假设 Alice 和 Bob 都发挥出最佳水平，当 Alice 赢得比赛时返回 true ，当 Bob 赢得比赛时返回 false 。

==动态规划==

```java
class Solution {
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }
}
```

```java
class Solution {
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[] dp = new int[length];
        for (int i = 0; i < length; i++) {
            dp[i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] > 0;
    }
}
```

==数学==

```java
class Solution {
    public boolean stoneGame(int[] piles) {
        return true;
    }
}
```

# Leetcode[1002. 查找共用字符](https://leetcode.cn/problems/find-common-characters/)

给你一个字符串数组 words ，请你找出所有在 words 的每个字符串中都出现的共用字符（ 包括重复字符），并以数组形式返回。你可以按 任意顺序 返回答案。

==哈希表==

```java
class Solution {
    public List<String> commonChars(String[] words) {
        int[] minfreq = new int[26];
        Arrays.fill(minfreq, Integer.MAX_VALUE);
        for (String word : words) {
            int[] freq = new int[26];
            int length = word.length();
            for (int i = 0; i < length; ++i) {
                char ch = word.charAt(i);
                ++freq[ch - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                minfreq[i] = Math.min(minfreq[i], freq[i]);
            }
        }

        List<String> ans = new ArrayList<String>();
        for (int i = 0; i < 26; ++i) {
            for (int j = 0; j < minfreq[i]; ++j) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;
    }
}
```

# Leetcode[1143. 最长公共子序列](https://leetcode.cn/problems/longest-common-subsequence/)

给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。

一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。

例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。

类似：Leetcode718 Leetcode1002

==动态规划==

```java
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1]; // 先对dp数组做初始化操作
        for (int i = 1 ; i <= text1.length() ; i++) {
            char char1 = text1.charAt(i - 1);
            for (int j = 1; j <= text2.length(); j++) {
                char char2 = text2.charAt(j - 1);
                if (char1 == char2) { // 开始列出状态转移方程
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[text1.length()][text2.length()];
    }
}
```



# Leetcode[1224. 最大相等频率](https://leetcode.cn/problems/maximum-equal-frequency/)

给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：

从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。

==哈希表==

`时间O(n) 空间O(n)`

```java
class Solution {
    public int maxEqualFreq(int[] nums) {
        //使用哈希表count 记录数 x出现的次数 count[x]
        Map<Integer, Integer> freq = new HashMap<Integer, Integer>();
        //freq记录出现次数为 f 的数的数目为 freq[f]
        Map<Integer, Integer> count = new HashMap<Integer, Integer>();
        //maxFreq 表示最大出现次数
        int res = 0, maxFreq = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count.getOrDefault(nums[i], 0) > 0) {
                freq.put(count.get(nums[i]), freq.get(count.get(nums[i])) - 1);
            }
            count.put(nums[i], count.getOrDefault(nums[i], 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(nums[i]));
            freq.put(count.get(nums[i]), freq.getOrDefault(count.get(nums[i]), 0) + 1);
            boolean ok = maxFreq == 1 ||
                    freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1) == i + 1 && freq.get(maxFreq) == 1 ||
                    freq.get(maxFreq) * maxFreq + 1 == i + 1 && freq.get(1) == 1;
            if (ok) {
                res = Math.max(res, i + 1);
            }
        }
        return res;
    }
}
```

# Leetcode[1483. 树节点的第 K 个祖先](https://leetcode.cn/problems/kth-ancestor-of-a-tree-node/)

给你一棵树，树上有 `n` 个节点，按从 `0` 到 `n-1` 编号。树以父节点数组的形式给出，其中 `parent[i]` 是节点 `i` 的父节点。树的根节点是编号为 `0` 的节点。

树节点的第 *`k`* 个祖先节点是从该节点到根节点路径上的第 `k` 个节点。

实现 `TreeAncestor` 类：

- `TreeAncestor（int n， int[] parent）` 对树和父数组中的节点数初始化对象。
- `getKthAncestor``(int node, int k)` 返回节点 `node` 的第 `k` 个祖先节点。如果不存在这样的祖先节点，返回 `-1` 。

==树上倍增 位运算== 

```java
class TreeAncestor {
    private int[][] pa;

    public TreeAncestor(int n, int[] parent) {
        int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
        pa = new int[n][m];
        for (int i = 0; i < n; i++)
            pa[i][0] = parent[i];
        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int m = 32 - Integer.numberOfLeadingZeros(k); // k 的二进制长度
        for (int i = 0; i < m; i++) {
            if (((k >> i) & 1) > 0) { // k 的二进制从低到高第 i 位是 1
                node = pa[node][i];
                if (node < 0) break;
            }
        }
        return node;
    }

    // 另一种写法，不断去掉 k 的最低位的 1
    public int getKthAncestor2(int node, int k) {
        for (; k > 0 && node != -1; k &= k - 1)
            node = pa[node][Integer.numberOfTrailingZeros(k)];
        return node;
    }
}
```

拓展最近公共祖先(Least Common Ancestors, LCA)模板：

```java
class TreeAncestor {
    private int[] depth;
    private int[][] pa;

    public TreeAncestor(int[][] edges) {
        int n = edges.length + 1;
        int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
        List<Integer> g[] = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) { // 节点编号从 0 开始
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x);
        }

        depth = new int[n];
        pa = new int[n][m];
        dfs(g, 0, -1);

        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                pa[x][i + 1] = p < 0 ? -1 : pa[p][i];
            }
        }
    }

    private void dfs(List<Integer>[] g, int x, int fa) {
        pa[x][0] = fa;
        for (int y : g[x]) {
            if (y != fa) {
                depth[y] = depth[x] + 1;
                dfs(g, y, x);
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        for (; k > 0; k &= k - 1)
            node = pa[node][Integer.numberOfTrailingZeros(k)];
        return node;
    }

    public int getLCA(int x, int y) {
        if (depth[x] > depth[y]) {
            int tmp = y;
            y = x;
            x = tmp;
        }
        // 使 y 和 x 在同一深度
        y = getKthAncestor(y, depth[y] - depth[x]);
        if (y == x)
            return x;
        for (int i = pa[x].length - 1; i >= 0; i--) {
            int px = pa[x][i], py = pa[y][i];
            if (px != py) {
                x = px;
                y = py;
            }
        }
        return pa[x][0];
    }
}
```

# Leetcode[2439. 最小化数组中的最大值](https://leetcode.cn/problems/minimize-maximum-of-array/)

==二分答案 二分查找==

```java
class Solution {

    private boolean check(int[] nums, int limit) {
        long ex = 0;
        // 从后往前遍历将 nums[i] 多余的数加到前面
        // 为了不改变nums数组，用ex表示多余的部分累积的结果
        // i 减到1，计算出分给nums[0]的ex
        for (int i = nums.length-1; i > 0; i--) {
            ex = Math.max(nums[i] - limit + ex, 0);
        }

        return nums[0] + ex <= limit;
    }

    public int minimizeArrayValue(int[] nums) {
        int l = 0,r = (int)1e9+5;
        while (l<r) {
            // 防止超int，用long也行
			int limit = l+(r-l)/2;

			// 最左侧加上ex仍然小于等于limit，可能还有下降的余地，右侧靠拢，否则左侧靠拢    
			if(check(nums, limit)) r = limit;
			else l=limit+1;
		}
    	return r;
    }
}
```

# Leetcode [2845. 统计趣味子数组的数目](https://leetcode.cn/problems/count-of-interesting-subarrays/)

给你一个下标从 **0** 开始的整数数组 `nums` ，以及整数 `modulo` 和整数 `k` 。

请你找出并统计数组中 **趣味子数组** 的数目。

如果 **子数组** `nums[l..r]` 满足下述条件，则称其为 **趣味子数组** ：

- 在范围 `[l, r]` 内，设 `cnt` 为满足 `nums[i] % modulo == k` 的索引 `i` 的数量。并且 `cnt % modulo == k` 。

以整数形式表示并返回趣味子数组的数目。

**注意：**子数组是数组中的一个连续非空的元素序列。

==前缀和==

```java
class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        long res = 0;
        int[] temp = new int[nums.size() + 1];
        for (int i = 1; i < nums.size() + 1; i++) temp[i] = nums.get(i - 1) % modulo == k ? 1 : 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int i = 1; i < nums.size() + 1; i++) {
            temp[i] += temp[i - 1];
            temp[i] %= modulo;
            res += map.getOrDefault(temp[i] - k, 0);
            res += map.getOrDefault(temp[i] + modulo - k, 0);
            map.put(temp[i], map.getOrDefault(temp[i], 0) + 1);
        }
        return res;
    }
}
```

# Leetcode[2835. 使子序列的和等于目标的最少操作次数](https://leetcode.cn/problems/minimum-operations-to-form-subsequence-with-target-sum/)

给你一个下标从 **0** 开始的数组 `nums` ，它包含 **非负** 整数，且全部为 `2` 的幂，同时给你一个整数 `target` 。

一次操作中，你必须对数组做以下修改：

- 选择数组中一个元素 `nums[i]` ，满足 `nums[i] > 1` 。
- 将 `nums[i]` 从数组中删除。
- 在 `nums` 的 **末尾** 添加 **两个** 数，值都为 `nums[i] / 2` 。

你的目标是让 `nums` 的一个 **子序列** 的元素和等于 `target` ，请你返回达成这一目标的 **最少操作次数** 。如果无法得到这样的子序列，请你返回 `-1` 。

数组中一个 **子序列** 是通过删除原数组中一些元素，并且不改变剩余元素顺序得到的剩余数组。

==位运算 贪心==

```java
class Solution {
    public int minOperations(List<Integer> nums, int target) {
        long sum = 0;
        long[] cnt = new long[31];
        for (Integer num : nums) {
            sum += num;
            cnt[Integer.numberOfTrailingZeros(num)]++;
        }
        if (target > sum) return -1;
        int ans = 0, i = 0;
        long s = 0;
        while ((1L << i) <= target) {
            s += cnt[i] << i;
            int mask = (int)((1L << ++i) - 1);
            if (s >= (target & mask)) continue;
            ans++;
            while (cnt[i] == 0) {
                ans++;
                i++;
            }
        }
        return ans;
    }
}
```

# Leetcode[2836. 在传球游戏中最大化函数值](https://leetcode.cn/problems/maximize-value-of-function-in-a-ball-passing-game/)

给你一个长度为 `n` 下标从 **0** 开始的整数数组 `receiver` 和一个整数 `k` 。

总共有 `n` 名玩家，玩家 **编号** 互不相同，且为 `[0, n - 1]` 中的整数。这些玩家玩一个传球游戏，`receiver[i]` 表示编号为 `i` 的玩家会传球给编号为 `receiver[i]` 的玩家。玩家可以传球给自己，也就是说 `receiver[i]` 可能等于 `i` 。

你需要从 `n` 名玩家中选择一名玩家作为游戏开始时唯一手中有球的玩家，球会被传 **恰好** `k` 次。

如果选择编号为 `x` 的玩家作为开始玩家，定义函数 `f(x)` 表示从编号为 `x` 的玩家开始，`k` 次传球内所有接触过球玩家的编号之 **和** ，如果有玩家多次触球，则 **累加多次** 。换句话说， `f(x) = x + receiver[x] + receiver[receiver[x]] + ... + receiver(k)[x]` 。

你的任务时选择开始玩家 `x` ，目的是 **最大化** `f(x)` 。

请你返回函数的 **最大值** 。

**注意：**`receiver` 可能含有重复元素。

==树上倍增 位运算==

```java
class Solution {
    public long getMaxFunctionValue(List<Integer> receiver, long K) {
        int n = receiver.size();
        int m = 64 - Long.numberOfLeadingZeros(K); // K 的二进制长度
        var pa = new int[m][n];
        var sum = new long[m][n];
        for (int i = 0; i < n; i++) {
            pa[0][i] = receiver.get(i);
            sum[0][i] = receiver.get(i);
        }
        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[i][x];
                pa[i + 1][x] = pa[i][p];
                sum[i + 1][x] = sum[i][x] + sum[i][p]; // 合并节点值之和
            }
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long s = i;
            int x = i;
            for (long k = K; k > 0; k &= k - 1) {
                int ctz = Long.numberOfTrailingZeros(k);
                s += sum[ctz][x];
                x = pa[ctz][x];
            }
            ans = Math.max(ans, s);
        }
        return ans;
    }
}
```

# Leetcode[2846. 边权重均等查询](https://leetcode.cn/problems/minimum-edge-weight-equilibrium-queries-in-a-tree/)

现有一棵由 `n` 个节点组成的无向树，节点按从 `0` 到 `n - 1` 编号。给你一个整数 `n` 和一个长度为 `n - 1` 的二维整数数组 `edges` ，其中 `edges[i] = [ui, vi, wi]` 表示树中存在一条位于节点 `ui` 和节点 `vi` 之间、权重为 `wi` 的边。

另给你一个长度为 `m` 的二维整数数组 `queries` ，其中 `queries[i] = [ai, bi]` 。对于每条查询，请你找出使从 `ai` 到 `bi` 路径上每条边的权重相等所需的 **最小操作次数** 。在一次操作中，你可以选择树上的任意一条边，并将其权重更改为任意值。

**注意：**

- 查询之间 **相互独立** 的，这意味着每条新的查询时，树都会回到 **初始状态** 。
- 从 `ai` 到 `bi`的路径是一个由 **不同** 节点组成的序列，从节点 `ai` 开始，到节点 `bi` 结束，且序列中相邻的两个节点在树中共享一条边。

返回一个长度为 `m` 的数组 `answer` ，其中 `answer[i]` 是第 `i` 条查询的答案。

==DFS==(会超时)

```java
class Solution {
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        int[] res = new int[queries.length];
        Map<Integer, List<int[]>> map = new HashMap<>();
        for (int[] edge : edges) {
            map.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new int[]{edge[1], edge[2]});
            map.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(new int[]{edge[0], edge[2]});
        }
        for (int i = 0; i < queries.length; i++) {
            res[i] = dfs(map, queries[i][0], queries[i][1]);
        }
        return res;
    }

    public int dfs(Map<Integer, List<int[]>> map, int start, int end) {
        HashMap<Integer, Integer> res = new HashMap<>();
        _dfs(map, start, end, -1, res);
        int maxNum = 0, sum = 0;
        for (Map.Entry<Integer, Integer> entry : res.entrySet()) {
            maxNum = Math.max(maxNum, entry.getValue());
            sum += entry.getValue();
        }
        return sum - maxNum;
    }

    private boolean _dfs(Map<Integer, List<int[]>> map, int start, int end, int parent, HashMap<Integer, Integer> res) {
        if (start == end) return true;
        for (int[] ele : map.get(start)) {
            if (ele[0] == parent) continue;
            if (_dfs(map, ele[0], end, start, res)) {
                res.put(ele[1], res.getOrDefault(ele[1], 0) + 1);
                return true;
            }
        }
        return false;
    }
}
```

==树上倍增 LCA==

```java
class Solution {
    public int[] minOperationsQueries(int n, int[][] edges, int[][] queries) {
        List<int[]>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1], w = e[2] - 1;
            g[x].add(new int[]{y, w});
            g[y].add(new int[]{x, w});
        }

        int m = 32 - Integer.numberOfLeadingZeros(n); // n 的二进制长度
        var pa = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(pa[i], -1);
        }
        var cnt = new int[n][m][26];
        var depth = new int[n];
        dfs(0, -1, g, pa, cnt, depth);

        for (int i = 0; i < m - 1; i++) {
            for (int x = 0; x < n; x++) {
                int p = pa[x][i];
                if (p != -1) {
                    int pp = pa[p][i];
                    pa[x][i + 1] = pp;
                    for (int j = 0; j < 26; j++) {
                        cnt[x][i + 1][j] = cnt[x][i][j] + cnt[p][i][j];
                    }
                }
            }
        }

        var ans = new int[queries.length];
        for (int qi = 0; qi < queries.length; qi++) {
            int x = queries[qi][0], y = queries[qi][1];
            int pathLen = depth[x] + depth[y];
            var cw = new int[26];
            if (depth[x] > depth[y]) {
                int temp = x;
                x = y;
                y = temp;
            }

            // 让 y 和 x 在同一深度
            for (int k = depth[y] - depth[x]; k > 0; k &= k - 1) {
                int i = Integer.numberOfTrailingZeros(k);
                int p = pa[y][i];
                for (int j = 0; j < 26; ++j) {
                    cw[j] += cnt[y][i][j];
                }
                y = p;
            }

            if (y != x) {
                for (int i = m - 1; i >= 0; i--) {
                    int px = pa[x][i];
                    int py = pa[y][i];
                    if (px != py) {
                        for (int j = 0; j < 26; j++) {
                            cw[j] += cnt[x][i][j] + cnt[y][i][j];
                        }
                        x = px;
                        y = py; // x 和 y 同时上跳 2^i 步
                    }
                }
                for (int j = 0; j < 26; j++) {
                    cw[j] += cnt[x][0][j] + cnt[y][0][j];
                }
                x = pa[x][0];
            }

            int lca = x;
            pathLen -= depth[lca] * 2;
            int maxCw = 0;
            for (int i = 0; i < 26; i++) {
                maxCw = Math.max(maxCw, cw[i]);
            }
            ans[qi] = pathLen - maxCw;
        }
        return ans;
    }

    private void dfs(int x, int fa, List<int[]>[] g, int[][] pa, int[][][] cnt, int[] depth) {
        pa[x][0] = fa;
        for (var e : g[x]) {
            int y = e[0], w = e[1];
            if (y != fa) {
                cnt[y][0][w] = 1;
                depth[y] = depth[x] + 1;
                dfs(y, x, g, pa, cnt, depth);
            }
        }
    }
}
```

# Leetcode[2856. 删除数对后的最小数组长度](https://leetcode.cn/problems/minimum-array-length-after-pair-removals/)

==贪心 二分查找==

```java
class Solution {
    public int minLengthAfterRemovals(List<Integer> nums) {
        int n = nums.size();
        int x = nums.get(n / 2);
        int maxCnt = lowerBound(nums, x) - lowerBound(nums, x - 1);
        return Math.max(maxCnt * 2 - n, n % 2);
    }

    // 开区间写法
    private int lowerBound(List<Integer> nums, int target) {
        int left = 0, right = nums.size() - 1; // 开区间 (left, right)
        while (left <= right) { // 区间不为空
            // 循环不变量：
            // nums[left] < target
            // nums[right] >= target
            int mid = left + (right - left) / 2;
            if (nums.get(mid) <= target)
                left = mid + 1; // 范围缩小到 (mid, right)
            else
                right = mid - 1; // 范围缩小到 (left, mid)
        }
        return right; // 或者 left+1
    }
}
```

# Leetcode[2857. 统计距离为 k 的点对](https://leetcode.cn/problems/count-pairs-of-points-with-distance-k/)

==哈希表==

```java
class Solution {
    public int countPairs(List<List<Integer>> coordinates, int k) {
        int ans = 0;
        var cnt = new HashMap<Long, Integer>();
        for (var p : coordinates) {
            int x = p.get(0), y = p.get(1);
            for (int i = 0; i <= k; i++) {
                ans += cnt.getOrDefault((x ^ i) * 2000000L + (y ^ (k - i)), 0);
            }
            cnt.merge(x * 2000000L + y, 1, Integer::sum);
        }
        return ans;
    }
}
```



# Leetcode[2861. 最大合金数](https://leetcode.cn/problems/maximum-number-of-alloys/)

==二分答案==

```java
class Solution {
    public int maxNumberOfAlloys(int n, int k, int budget, List<List<Integer>> composition, List<Integer> stock, List<Integer> cost) {
        int res = 0;
        int rightStart = Collections.min(stock) + budget;
        for (List<Integer> com : composition) {
            int left = 0, right = rightStart;
            while (left <= right) {
                int mid = left + ((right - left) >> 1);
                if (check(mid, budget, com, stock, cost)) right = mid - 1;
                else left = mid + 1;
            }
            res = Math.max(res, right);
        }
        return res;
    }
    
    private boolean check(int mid, int budget, List<Integer> com, List<Integer> stock, List<Integer> cost) {
        long money = 0;
        for (int i = 0; i < com.size(); i++) {
            long temp = com.get(i) * 1L * mid;
            if (temp > stock.get(i)) money += (temp - stock.get(i)) * cost.get(i);
        }
        if (money > budget) return true;
        return false;
    }
}
```

# Leetcode[2862. 完全子集的最大元素和](https://leetcode.cn/problems/maximum-element-sum-of-a-complete-subset-of-indices/)

```java
class Solution {
    public long maximumSum(List<Integer> nums) {
        int[] a = nums.stream().mapToInt(i -> i).toArray();
        long res = 0;
        for (int i = 1; i <= a.length; i++) {
            long sum = 0;
            for (int j = 1; i * j * j <= a.length; j++) {
                sum += a[i * j * j - 1];
            }
            res = Math.max(res, sum);
        }
        return res;
    }
}
```

# Leetcode[8020. 字符串转换](https://leetcode.cn/problems/string-transformation/)

给你两个长度都为 `n` 的字符串 `s` 和 `t` 。你可以对字符串 `s` 执行以下操作：

- 将 `s` 长度为 `l` （`0 < l < n`）的 **后缀字符串** 删除，并将它添加在 `s` 的开头。
  比方说，`s = 'abcd'` ，那么一次操作中，你可以删除后缀 `'cd'` ，并将它添加到 `s` 的开头，得到 `s = 'cdab'` 。

给你一个整数 `k` ，请你返回 **恰好** `k` 次操作将 `s` 变为 `t` 的方案数。

由于答案可能很大，返回答案对 `109 + 7` **取余** 后的结果。

==KMP 动态规划 快速幂==

```java
class Solution {
    public int numberOfWays(String s, String t, long k) {
        int n = s.length();
        int c = kmpSearch(s + s.substring(0, n - 1), t);
        long[][] m = {
            {c - 1, c},
            {n - c, n - 1 - c},
        };
        m = pow(m, k);
        return s.equals(t) ? (int) m[0][0] : (int) m[0][1];
    }

    private int[] calcMaxMatch(String s) {
        int[] match = new int[s.length()];
        int c = 0;
        for (int i = 1; i < s.length(); i++) {
            char v = s.charAt(i);
            while (c > 0 && s.charAt(c) != v) {
                c = match[c - 1];
            }
            if (s.charAt(c) == v) {
                c++;
            }
            match[i] = c;
        }
        return match;
    }

    private int kmpSearch(String text, String pattern) {
        int[] match = calcMaxMatch(pattern);
        int lenP = pattern.length();
        int c = 0;
        int matchCnt = 0;
        for (int i = 0; i < text.length(); i++) {
            char v = text.charAt(i);
            while (c > 0 && pattern.charAt(c) != v) {
                c = match[c - 1];
            }
            if (pattern.charAt(c) == v) {
                c++;
            }
            if (c == lenP) {
                matchCnt++;
                c = match[c - 1];
            }
        }
        return matchCnt;
    }

    private static final long MOD = (long) 1e9 + 7;

    private long[][] multiply(long[][] a, long[][] b) {
        long[][] c = new long[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (a[i][0] * b[0][j] + a[i][1] * b[1][j]) % MOD;
            }
        }
        return c;
    }

    private long[][] pow(long[][] a, long n) {
        long[][] res = {{1, 0}, {0, 1}};
        for (; n > 0; n /= 2) {
            if (n % 2 > 0) {
                res = multiply(res, a);
            }
            a = multiply(a, a);
        }
        return res;
    }
}
```



# Leetcode[100030. 将石头分散到网格图的最少移动次数](https://leetcode.cn/problems/minimum-moves-to-spread-stones-over-grid/)

给你一个大小为 `3 * 3` ，下标从 **0** 开始的二维整数矩阵 `grid` ，分别表示每一个格子里石头的数目。网格图中总共恰好有 `9` 个石头，一个格子里可能会有 **多个** 石头。

每一次操作中，你可以将一个石头从它当前所在格子移动到一个至少有一条公共边的相邻格子。

请你返回每个格子恰好有一个石头的 **最少移动次数** 。

==枚举 DFS 回溯==

```java
class Solution {
    int ans=Integer.MAX_VALUE;
    int[][] grid;
    public int minimumMoves(int[][] grid) {
        this.grid=grid;
        int cnt=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(grid[i][j]==0){
                    cnt++;
                }
            }
        }
        dfs(0,cnt);
        return ans;
    }
    //sum记录移动方案对应的移动次数
    //cnt记录剩余石头数为0的格子数
    private void dfs(int sum,int cnt){
        if(cnt==0){
            ans=Math.min(sum,ans);
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(grid[i][j]==0){
                    for(int k=0;k<3;k++){
                        for(int l=0;l<3;l++){
                            if(grid[k][l]>1){
                                grid[k][l]--;
                                grid[i][j]=1;
                                dfs(sum+Math.abs(i-k)+Math.abs(j-l),cnt-1);
                                grid[k][l]++;
                                grid[i][j]=0;
                            }
                        }
                    }
                }
            }
        }
    }
}
```



# 311-[6182. 反转二叉树的奇数层](https://leetcode.cn/problems/reverse-odd-levels-of-binary-tree/)

```java
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) {
            return null;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            if (level % 2 == 1) {
                //反转该层的每一个节点的值
                List<TreeNode> nodes = new ArrayList<>(queue);
                for (int i = 0; i < nodes.size() / 2; i++) {
                    TreeNode treeNode = nodes.get(i);
                    TreeNode treeNode1 = nodes.get(nodes.size() - 1 - i);
                    int temp = treeNode.val;
                    treeNode.val = treeNode1.val;
                    treeNode1.val = temp;
                }
            }
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            level++;
        }
        return root;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
```

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode reverseOddLevels(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        List<TreeNode> tempList;
        queue.offer(root);
        int depth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            tempList = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode temp = queue.poll();
                tempList.add(temp);
                if (null != temp.left) {
                    queue.offer(temp.left);
                }
                if (null != temp.right) {
                    queue.offer(temp.right);
                }
            }
            size = tempList.size();
            if (depth % 2 == 1) {
                for (int j = 0; j < size / 2; j++) {
                    int t = tempList.get(j).val;
                    tempList.get(j).val = tempList.get(size - 1 - j).val;
                    tempList.get(size - 1 - j).val = t;
                }
            }
            depth++;
        }
        return root;
    }
}
```



# 311-[6183. 字符串的前缀分数和](https://leetcode.cn/problems/sum-of-prefix-scores-of-strings/)

给你一个长度为 n 的数组 words ，该数组由 非空 字符串组成。

定义字符串 word 的 分数 等于以 word 作为 前缀 的 words[i] 的数目。

例如，如果 words = ["a", "ab", "abc", "cab"] ，那么 "ab" 的分数是 2 ，因为 "ab" 是 "ab" 和 "abc" 的一个前缀。
返回一个长度为 n 的数组 answer ，其中 answer[i] 是 words[i] 的每个非空前缀的分数 总和 。

==字典树==

```java
class Solution {

	public int[] sumPrefixScores(String[] words) {
		Trie root = new Trie();
		for (String word : words) {
			Trie node = root;
			for (char c : word.toCharArray()) {
				(node = node.computeIfAbsent(c, t -> new Trie())).count++;
			}
		}
		int[] result = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			Trie node = root;
			for (char c : words[i].toCharArray()) {
				result[i] += (node = node.get(c)).count;
			}
		}
		return result;
	}

	private class Trie extends HashMap<Character, Trie> {
		private int count;
	}
}
```

# 312-[6188. 按身高排序](https://leetcode.cn/problems/sort-the-people/)

给你一个字符串数组 names ，和一个由 互不相同 的正整数组成的数组 heights 。两个数组的长度均为 n 。

对于每个下标 i，names[i] 和 heights[i] 表示第 i 个人的名字和身高。

请按身高 降序 顺序返回对应的名字数组 names 。

==IdentityHashMap==

```java
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int n = names.length;
        Map<String, Integer> map = new IdentityHashMap<>();
        for (int i = 0; i < n; ++i) {
            map.put(names[i], heights[i]);
        }
        Arrays.sort(names, (n1, n2) -> Integer.compare(map.get(n2), map.get(n1)));
        return names;
    }
}
```

==HashMap==

```java
class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        Integer[] height=new Integer[heights.length];
        Map<Integer,String> map=new HashMap<>();
        int i;
        for(i=0;i<heights.length;i++){
            map.put(heights[i],names[i]);
            height[i]=heights[i];
        }
        
        Arrays.sort(height,(o1,o2)->o2-o1);
        String[] res=new String[heights.length];
        for(i=0;i<height.length;i++){
            res[i]=map.get(height[i]);
        }
        return res;
    }
}
```

==构造类==

```java
class Solution {
    class Person {
        public String name;
        public int height;
        public Person(String name, int height) {
            this.name = name;
            this.height = height;
        }
    }
    public String[] sortPeople(String[] names, int[] heights) {
        List<Person> persons = new ArrayList<Person>();
        int len = heights.length;
        for (int i = 0; i < len; i++) {
            persons.add(new Person(names[i], heights[i]));
        }
        Collections.sort(persons, (v1, v2) -> v2.height - v1.height);
        String[] res = new String[len];
        for (int i = 0; i < len; i++) {
            res[i] = persons.get(i).name;
        }
        return res;
    }
}
```

# 312-[6189. 按位与最大的最长子数组](https://leetcode.cn/problems/longest-subarray-with-maximum-bitwise-and/)

给你一个长度为 n 的整数数组 nums 。

考虑 nums 中进行 按位与（bitwise AND）运算得到的值 最大 的 非空 子数组。

换句话说，令 k 是 nums 任意 子数组执行按位与运算所能得到的最大值。那么，只需要考虑那些执行一次按位与运算后等于 k 的子数组。
返回满足要求的 最长 子数组的长度。

数组的按位与就是对数组中的所有数字进行按位与运算。

子数组 是数组中的一个连续元素序列。

==动态规划-遍历两次==

```java
class Solution {
    public int longestSubarray(int[] nums) {
       int[] dp=new int[nums.length];
       int max=nums[0];
       dp[0]=nums[0];
       for(int i=1;i<nums.length;i++){
           dp[i]=Math.max(nums[i],dp[i-1]&nums[i]);
           max=Math.max(max,dp[i]);
       }
       int maxlen=0;
       int len=0;
       for(int i=0;i<nums.length;i++){
           if(dp[i]==max){
               len++;
           }else{
               len=0;
           }
           maxlen=Math.max(maxlen,len);
       }
       return maxlen;
    }
}
```

==求最大值-遍历两次==

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int num : nums) {
            max = Math.max(max, num);
        }
        for (int i = 0; i < nums.length; ++i) {
            nums[i] &= max;
        }
        int maxCount = 0;
        for (int i = 0; i < nums.length;++i) {
            int curCount = 0;
            while (i < nums.length && nums[i] == max) {
                ++curCount;
                maxCount = Math.max(maxCount, curCount);
                ++i;
            }
        }
        return maxCount;
    }
}
```

或者

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int max=Arrays.stream(nums).max().getAsInt();
        int ans=0;
        int temp=0;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==max){
                temp++;
                ans=Math.max(temp,ans);}
            else temp=0;
        }
        return ans;

    }
}
```

==求最大值-一次遍历==

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int max = nums[0];
        int t = 1;
        int max_t = 1;
        if (n == 1)
            return 1;
        for (int i = 1; i < n; i++) {
            if (max < nums[i]) {
                max = nums[i];
                max_t = 1;
                t = 1;
            }else if(max == nums[i])
                t++;
            else{
                max_t = Math.max(max_t,t);
                t = 0;
            }
        }
        max_t = Math.max(max_t,t);
        return max_t;
    }
}
```

# 312-[6190. 找到所有好下标](https://leetcode.cn/problems/find-all-good-indices/)

给你一个大小为 n 下标从 0 开始的整数数组 nums 和一个正整数 k 。

对于 k <= i < n - k 之间的一个下标 i ，如果它满足以下条件，我们就称它为一个 好 下标：

下标 i 之前 的 k 个元素是 非递增的 。
下标 i 之后 的 k 个元素是 非递减的 。
按 升序 返回所有好下标。

==暴力遍历==

会超时

==动态规划==

```java
class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        int[] dp = new int[nums.length];
        List<Integer> res = new ArrayList<>();
        Arrays.fill(dp,1);
        for(int i = nums.length - 2;i >= 1;i--){
            if(nums[i] <= nums[i + 1]){
                dp[i] = dp[i + 1] + 1;
            }
        }
        int incrSum = 1;
        for(int i = 1;i < nums.length - 1;i++){
            if(incrSum >= k && dp[i + 1] >= k){
                res.add(i);
            }
            if(nums[i] <= nums[i - 1]){
                incrSum++;
            }else{
                incrSum = 1;
            }
        }
        return res;
    }
}
```

==滑动窗口==

1.每次左窗口和右窗口分别整体向前滑动一格。
2.左窗口中若出现递增段，则不符合要求，要滑动时递推是否存在递增段。
3.可能存在多个递增段，但只要最右侧(最远下标)的递增段没有排除，就一定不符合非递增，因此只要记录最右侧递增段的下标即可。
4.若左窗口左边界已经越过记录的最右侧递增段下标，则视为满足非递增。
5.右窗口同理。
6.左右窗口同时满足条件，则为好下标。

```java
class Solution {
    public List<Integer> goodIndices(int[] nums, int k) {
        int n=nums.length,ll=0,lr=k,rl=k+1,rr=rl+k,lp=-1,rp=k;
        List<Integer> res=new ArrayList<>();
        for(int i=ll;i<lr;i++){
            if(i>0&&nums[i]>nums[i-1])lp=i-1;
        }
        for(int i=rl;i<rr;i++){
            if(i+1<Math.min(rr,n)&&nums[i]>nums[i+1])rp=i;
        }
        for(int i=k;i<n-k;i++){
            if(ll>lp&&rl>rp)res.add(i);
            if(nums[lr]>nums[lr-1])lp=lr-1;
            if(rr<n&&nums[rr-1]>nums[rr])rp=rr-1;
            ll++;
            lr++;
            rl++;
            rr++;
        }
        return res;
    }
}
```

# 312-[6191. 好路径的数目](https://leetcode.cn/problems/number-of-good-paths/)

你一棵 n 个节点的树（连通无向无环的图），节点编号从 0 到 n - 1 且恰好有 n - 1 条边。

给你一个长度为 n 下标从 0 开始的整数数组 vals ，分别表示每个节点的值。同时给你一个二维整数数组 edges ，其中 edges[i] = [ai, bi] 表示节点 ai 和 bi 之间有一条 无向 边。

一条 好路径 需要满足以下条件：

开始节点和结束节点的值 相同 。
开始节点和结束节点中间的所有节点值都 小于等于 开始节点的值（也就是说开始节点的值应该是路径上所有节点的最大值）。
请你返回不同好路径的数目。

注意，一条路径和它反向的路径算作 同一 路径。比方说， 0 -> 1 与 1 -> 0 视为同一条路径。单个节点也视为一条合法路径。

==并查集==

```java
class Solution {
    int[] fa;

    public int numberOfGoodPaths(int[] vals, int[][] edges) {
        var n = vals.length;
        List<Integer>[] g = new ArrayList[n];
        Arrays.setAll(g, e -> new ArrayList<>());
        for (var e : edges) {
            int x = e[0], y = e[1];
            g[x].add(y);
            g[y].add(x); // 建图
        }

        fa = new int[n];
        for (var i = 0; i < n; i++) fa[i] = i;
        // size[x] 表示节点值等于 vals[x] 的节点个数，如果按照节点值从小到大合并，size[x] 也是连通块内的等于最大节点值的节点个数
        var size = new int[n];
        Arrays.fill(size, 1);
        var id = IntStream.range(0, n).boxed().toArray(Integer[]::new);
        Arrays.sort(id, (i, j) -> vals[i] - vals[j]);

        var ans = n;
        for (var x : id) {
            int vx = vals[x], fx = find(x);
            for (var y : g[x]) {
                y = find(y);
                if (y == fx || vals[y] > vx) continue; // 只考虑最大节点值比 vx 小的连通块
                if (vals[y] == vx) { // 可以构成好路径
                    ans += size[fx] * size[y]; // 乘法原理
                    size[fx] += size[y]; // 统计连通块内节点值等于 vx 的节点个数
                }
                fa[y] = fx; // 把小的节点值合并到大的节点值上
            }
        }
        return ans;
    }

    int find(int x) {
        if (fa[x] != x) fa[x] = find(fa[x]);
        return fa[x];
    }
}
```

# D88-[6212. 删除字符使频率相同](https://leetcode.cn/problems/remove-letter-to-equalize-frequency/)

给你一个下标从 0 开始的字符串 word ，字符串只包含小写英文字母。你需要选择 一个 下标并 删除 下标处的字符，使得 word 中剩余每个字母出现 频率 相同。

如果删除一个字母后，word 中剩余所有字母的出现频率都相同，那么返回 true ，否则返回 false 。

注意：

字母 x 的 频率 是这个字母在字符串中出现的次数。
你 必须 恰好删除一个字母，不能一个字母都不删除。

==哈希表+排序==

`时间 O(nlogn) 空间 O(n)`

```java
class Solution {
    public boolean equalFrequency(String word) {
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> temp;
        for (char c : word.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        temp = new ArrayList<>(map.values());
        Collections.sort(temp);
        int size = temp.size();
        if (size == 1) return true;
        if (temp.get(size - 1) == temp.get(size - 2) + 1 && temp.get(0) == temp.get(size - 2)) return true;
        if (temp.get(0) == 1 && temp.get(size - 1) == temp.get(1)) return true;
        return false;
    }
}
```

==哈希表+暴力遍历==

`时间 O(n^2) 空间 O(n)`

```java
class Solution {
    public boolean equalFrequency(String word) {
        int n = word.length();
        for (int i = 0; i < n - 1; i++) {
            String temp =  word.substring(0, i) + word.substring(i + 1, n);
            // System.out.println("temp: " + temp);
            
            int[] cnt = new int[26];
            for (char c : temp.toCharArray()) {
                cnt[c - 'a']++;
            }
            Set<Integer> set = new HashSet<Integer>();
            for (int c : cnt) {
                if (c != 0) {
                    set.add(c);
                }
            }
            if (set.size() == 1) {
                return true;
            }
        }
        return false;
    }
}
```

==哈希表==

`时间 O(n) 空间 O(n)`

类似于Leetcode1224

```java
class Solution {
    public boolean equalFrequency(String word) {
        Map<Integer, Integer> freq = new HashMap<>();
        Map<Character, Integer> count = new HashMap<>();
        int res = 0, maxFreq = 0;
        for (Character c : word.toCharArray()) {
            if (count.getOrDefault(c, 0) > 0) {
                freq.put(count.get(c), freq.get(count.get(c)) - 1);
            }
            count.put(c, count.getOrDefault(c, 0) + 1);
            maxFreq = Math.max(maxFreq, count.get(c));
            freq.put(count.get(c), freq.getOrDefault(count.get(c), 0) + 1);

        }
        boolean ok = maxFreq == 1 ||
                    freq.get(maxFreq) * maxFreq + freq.get(maxFreq - 1) * (maxFreq - 1) == word.length() && freq.get(maxFreq) == 1 ||
                    freq.get(maxFreq) * maxFreq + 1 == word.length() && freq.get(1) == 1;

        return ok;
    }
}
```

# D88-[6197. 最长上传前缀](https://leetcode.cn/problems/longest-uploaded-prefix/)

给你一个 n 个视频的上传序列，每个视频编号为 1 到 n 之间的 不同 数字，你需要依次将这些视频上传到服务器。请你实现一个数据结构，在上传的过程中计算 最长上传前缀 。

如果 闭区间 1 到 i 之间的视频全部都已经被上传到服务器，那么我们称 i 是上传前缀。最长上传前缀指的是符合定义的 i 中的 最大值 。

请你实现 LUPrefix 类：

LUPrefix(int n) 初始化一个 n 个视频的流对象。
void upload(int video) 上传 video 到服务器。
int longest() 返回上述定义的 最长上传前缀 的长度。

```java
class LUPrefix {
    int[] temp;
    int cursor;
    int len;

    public LUPrefix(int n) {
        temp = new int[n];
        cursor = 0;
        len = n;
    }
    
    public void upload(int video) {
        temp[video - 1] = 1;
    }
    
    public int longest() {
        for (int i = cursor; i < len; i++) {
            if (temp[i] == 0) {
                break;
            }
            cursor++;
        }
        return cursor;
    }
}
```

# D88-[6213. 所有数对的异或和](https://leetcode.cn/problems/bitwise-xor-of-all-pairings/)

给你两个下标从 0 开始的数组 nums1 和 nums2 ，两个数组都只包含非负整数。请你求出另外一个数组 nums3 ，包含 nums1 和 nums2 中 所有数对 的异或和（nums1 中每个整数都跟 nums2 中每个整数 恰好 匹配一次）。

请你返回 nums3 中所有整数的 异或和 。

```java
class Solution {
    public int xorAllNums(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;
        int temp1 = 0, temp2 = 0;
        if (len2 % 2 == 1) {
            for (int i : nums1) {
                temp1 ^= i;
            }
        }
        if (len1 % 2 == 1) {
            for (int i : nums2) {
                temp2 ^= i;
            }
        }
        return temp1 ^ temp2;
    }
}
```

# D88-[6198. 满足不等式的数对数目](https://leetcode.cn/problems/number-of-pairs-satisfying-inequality/)

给你两个下标从 0 开始的整数数组 nums1 和 nums2 ，两个数组的大小都为 n ，同时给你一个整数 diff ，统计满足以下条件的 数对 (i, j) ：

0 <= i < j <= n - 1 且
nums1[i] - nums1[j] <= nums2[i] - nums2[j] + diff.
请你返回满足条件的 数对数目 。

==树状数组==

```java
class Solution {
    public long numberOfPairs(int[] a, int[] nums2, int diff) {
        var n = a.length;
        for (var i = 0; i < n; ++i)
            a[i] -= nums2[i];
        var b = a.clone();
        Arrays.sort(b); // 配合下面的二分，离散化

        var ans = 0L;
        var t = new BIT(n + 1);
        for (var x : a) {
            ans += t.query(lowerBound(b, x + diff + 1));
            t.add(lowerBound(b, x) + 1);
        }
        return ans;
    }

    private int lowerBound(int[] a, int x) {
        int left = 0, right = a.length;
        while (left < right) {
            var mid = left + (right - left) / 2;
            if (a[mid] < x) left = mid + 1;
            else right = mid;
        }
        return left;
    }
}

class BIT {
    private final int[] tree;

    public BIT(int n) {
        tree = new int[n];
    }

    public void add(int x) {
        while (x < tree.length) {
            ++tree[x];
            x += x & -x;
        }
    }

    public int query(int x) {
        var res = 0;
        while (x > 0) {
            res += tree[x];
            x &= x - 1;
        }
        return res;
    }
}
```

==归并排序==

# 313-[6192. 公因子的数目](https://leetcode.cn/problems/number-of-common-factors/)

给你两个正整数 `a` 和 `b` ，返回 `a` 和 `b` 的 **公** 因子的数目。

如果 `x` 可以同时整除 `a` 和 `b` ，则认为 `x` 是 `a` 和 `b` 的一个 **公因子** 。

```java
class Solution {
    public int commonFactors(int a, int b) {
        int label = Math.min(a, b);
        int ans = 0;
        for (int i = 1; i <= label; i++) {
            if (a % i == 0 && b % i == 0) {
                ans++;
            }
        }
        return ans;
    }
}
```

优化

```java
class Solution {
    public int commonFactors(int a, int b) {
        int g = gcd(a, b);
        int ans = 0, i = 1;
        while (i * i <= g) {
            if (g % i == 0) {
                ans++;
                if (i * i < g) {
                    ans++;
                }
            }
            i++;
        }
        return ans;
    }

    private int gcd(int m, int n) {
        return n > 0 ? gcd(n, m % n) : m;
    }
}
```

# 313-[6193. 沙漏的最大总和](https://leetcode.cn/problems/maximum-sum-of-an-hourglass/)

给你一个大小为 m x n 的整数矩阵 grid 。

按以下形式将矩阵的一部分定义为一个 沙漏 ：


返回沙漏中元素的 最大 总和。

注意：沙漏无法旋转且必须整个包含在矩阵中。

```java
class Solution {
    public int maxSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int max = 0;
        int[][] dp = new int[m - 2][n - 2];
        for (int i = 0; i <= m - 3; i++) {
            for (int j = 0; j <= n - 3; j++) {
                if (j == 0) {
                    dp[i][j] = grid[i][0] + grid[i][1] + grid[i][2] +
                        grid[i + 1][1] + grid[i + 2][0] + grid[i + 2][1] + grid[i + 2][2];
                } else {
                    dp[i][j] = dp[i][j - 1] - grid[i][j - 1] - grid[i + 2][j - 1]  - grid[i + 1][j] + grid[i][j + 2] + grid[i + 2][j + 2] + grid[i + 1][j + 1];
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return max;
    }
}
```

# 313-[6194. 最小 XOR](https://leetcode.cn/problems/minimize-xor/)

```java
class Solution {
    public int minimizeXor(int num1, int num2) {
        int[] temp = new int[31];
        int count1 = 0, count2 = 0;
        int sum = 0, count = 0, originalNum1 = num1;
        for (int i = 0; i < 31; i++) {
            if ((num2 & 1) > 0) {
                count2++;
            }
            num2 = num2 >> 1;
        }
        for (int j = 0; j < 31; j++) {
            if ((num1 & 1) > 0) {
                count1++;
                temp[j] = 1;
            }
            num1 = num1 >> 1;
        }
        if (count1 < count2) {
            for (int k = 0; k < 31; k++) {
                if (count == count2 - count1) return sum + originalNum1;
                if (temp[k] == 0) {
                    sum += 1 << k;
                    count++;
                }
            }
        }
        if (count1 > count2) {
            for (int k = 0; k < 31; k++) {
                if (count == count1 - count2) return originalNum1 - sum;
                if (temp[k] == 1) {
                    sum += 1 << k;
                    count++;
                }
            }
        }
        return originalNum1;
    }
}
```

==贪心+位运算==

具体思路参考：https://leetcode.cn/problems/minimize-xor/solution/by-flix-0phq/

```python
class Solution:
    def minimizeXor(self, num1: int, num2: int) -> int:
        c2 = num2.bit_count()
        c1 = num1.bit_count()
        while c2 < c1:
            num1 &= num1 - 1  # 1 变成 0
            c2 += 1
        while c2 > c1:
            num1 |= num1 + 1  # 0 变成 1
            c2 -= 1
        return num1
```

java中bit_count可以采用下面方式求解

```java
public static int bitCount(int n){
    int count = 0;
    while(n != 0){
        count += n & 1;
        n >>>= 1;
    }
    return count;
}
```

# 313-[6195. 对字母串可执行的最大删除数](https://leetcode.cn/problems/maximum-deletions-on-a-string/)

给你一个仅由小写英文字母组成的字符串 s 。在一步操作中，你可以：

删除 整个字符串 s ，或者
对于满足 1 <= i <= s.length / 2 的任意 i ，如果 s 中的 前 i 个字母和接下来的 i 个字母 相等 ，删除 前 i 个字母。
例如，如果 s = "ababc" ，那么在一步操作中，你可以删除 s 的前两个字母得到 "abc" ，因为 s 的前两个字母和接下来的两个字母都等于 "ab" 。

返回删除 s 所需的最大操作数。

==动态规划==

`时间 O(n^2) 空间 O(n^2)`

 ```java
class Solution {
    public int deleteString(String S) {
        var s = S.toCharArray();
        var n = s.length;
        var lcp = new int[n + 1][n + 1]; // lcp[i][j] 表示 s[i:] 和 s[j:] 的最长公共前缀
        for (var i = n - 1; i >= 0; --i)
            for (var j = n - 1; j > i; --j)
                if (s[i] == s[j])
                    lcp[i][j] = lcp[i + 1][j + 1] + 1;
        var f = new int[n];
        for (var i = n - 1; i >= 0; --i) {
            for (var j = 1; i + j * 2 <= n; ++j)
                if (lcp[i][i + j] >= j) // 说明 s[i:i+j] == s[i+j:i+j*2]
                    f[i] = Math.max(f[i], f[i + j]);
            ++f[i];
        }
        return f[0];
    }
}
 ```

# 314-[6202. 使用机器人打印字典序最小的字符串](https://leetcode.cn/problems/using-a-robot-to-print-the-lexicographically-smallest-string/)

给你一个字符串 s 和一个机器人，机器人当前有一个空字符串 t 。执行以下操作之一，直到 s 和 t 都变成空字符串：

删除字符串 s 的 第一个 字符，并将该字符给机器人。机器人把这个字符添加到 t 的尾部。
删除字符串 t 的 最后一个 字符，并将该字符给机器人。机器人将该字符写到纸上。
请你返回纸上能写出的字典序最小的字符串。

==贪心 栈==

`时间O(n) 空间O(n+26)`

```java
class Solution {
    public String robotWithString(String S) {
        var ans = new StringBuilder();
        var s = S.toCharArray();
        var cnt = new int[26];
        for (var c : s) ++cnt[c - 'a'];
        var min = 0; // 剩余最小字母
        var st = new ArrayDeque<Character>();
        for (var c : s) {
            --cnt[c - 'a'];
            while (min < 25 && cnt[min] == 0) ++min;
            st.push(c);
            while (!st.isEmpty() && st.peek() - 'a' <= min)
                ans.append(st.poll());
        }
        return ans.toString();
    }
}
```

# 314-[6203. 矩阵中和能被 K 整除的路径](https://leetcode.cn/problems/paths-in-matrix-whose-sum-is-divisible-by-k/)

给你一个下标从 0 开始的 m x n 整数矩阵 grid 和一个整数 k 。你从起点 (0, 0) 出发，每一步只能往 下 或者往 右 ，你想要到达终点 (m - 1, n - 1) 。

请你返回路径和能被 k 整除的路径数目，由于答案可能很大，返回答案对 109 + 7 取余 的结果。

 ==动态规划==

`时间O(mnk) 空间O(mnk)`

```java
class Solution {
    public int numberOfPaths(int[][] grid, int k) {
        final var mod = (int) 1e9 + 7;
        int m = grid.length, n = grid[0].length;
        var f = new int[m + 1][n + 1][k];
        f[0][1][0] = 1;
        for (var i = 0; i < m; ++i)
            for (var j = 0; j < n; ++j)
                for (var v = 0; v < k; ++v)
                    f[i + 1][j + 1][(v + grid[i][j]) % k] = f[i + 1][j][v] % mod + f[i][j + 1][v] % mod;
        return f[m][n][0];
    }
}
```

# 315-[6207. 统计定界子数组的数目](https://leetcode.cn/problems/count-subarrays-with-fixed-bounds/)

给你一个整数数组 nums 和两个整数 minK 以及 maxK 。

nums 的定界子数组是满足下述条件的一个子数组：

子数组中的 最小值 等于 minK 。
子数组中的 最大值 等于 maxK 。
返回定界子数组的数目。

子数组是数组中的一个连续部分

==双指针 滑动窗口==

```java
class Solution {
    public long countSubarrays(int[] nums, int minK, int maxK) {
        var ans = 0L;
        int n = nums.length, minI = -1, maxI = -1, i0 = -1;
        for (var i = 0; i < n; ++i) {
            var x = nums[i];
            if (x == minK) minI = i;
            if (x == maxK) maxI = i;
            if (x < minK || x > maxK) i0 = i; // 子数组不能包含 nums[i0]
            ans += Math.max(Math.min(minI, maxI) - i0, 0);
        }
        return ans;
    }
}
```

==ST表==

# 316-[2447. 最大公因数等于 K 的子数组数目](https://leetcode.cn/problems/number-of-subarrays-with-gcd-equal-to-k/)

给你一个整数数组 `nums` 和一个整数 `k` ，请你统计并返回 `nums` 的子数组中元素的最大公因数等于 `k` 的子数组数目。

**子数组** 是数组中一个连续的非空序列。

**数组的最大公因数** 是能整除数组中所有元素的最大整数。

==暴力==

```java
class Solution {
    public int subarrayGCD(int[] nums, int k) {
        int len = nums.length;
        int ans = 0;
        for (int i = 0; i < len; i++) {
            int temp = nums[i];
            for (int j = i; j < len; j++) {
                if (nums[j] % k != 0) break;
                temp = gcd(temp, nums[j]);
                if (temp == k) ans++;
            }
        }
        return ans;
    }
    private int gcd(int m, int n) {
        return n > 0 ? gcd(n, m % n) : m;
    }
}
```

==滑动窗口==

```java
class Solution {
    public int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] % k != 0) {//不是k的倍数
                left = i + 1;
            } else if (nums[i] == k) {//k
                res += i - left + 1;
            } else {//2k,3k...
               if (left != i) {
                    res += getCount(nums,k, left, i);
                }
            }
        }
        return res;
    }

    private int getCount(int[] nums, int k, int left, int right) {
        int res = 0;
       int gcd = nums[right];
        for (int i = right - 1; i >=left; i--) {
            gcd = gcd(gcd,nums[i]);
            if (gcd == k) {
                res++;
            }
            if(gcd < k){
                break;
            }
        }
        return res;
    }


    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}

```

==原地去重==

```java
class Solution {
    public int subarrayGCD(int[] nums, int k) {

        int res = 0;
        int len = nums.length;
        List<int[]> records = new ArrayList<>(); //[gcd, 相同gcd区间的右端点]
        int index0 = -1; //gcd 不为 k 的下标索引
        for(int i = 0; i < len; i++){
            int num = nums[i];
            if(num % k != 0) {
                index0 = i;
                records = new ArrayList<>();
            }else{
                records.add(new int[]{num, i});
                int j = 0
                    
                    //原地去重
                for(int[] record : records){
             		record[0] = gcd(num, record[0]);
                    if(records.get(j)[0] != record[0]){
                        records.set(++j, record);
                    }else{
                        //相同gcd 更新右端点
                        records.set(j, record);
                    }
                }
                records.subList(j + 1, records.size()).clear();
                int[] first = records.get(0);
                if(first[0] == k) res += first[1] - index0;
            }
        }
        return res;
    }
    public int gcd(int a, int b){

        return b == 0 ? a : gcd(b, a % b);
    }
}


```

# 316-[2448. 使数组相等的最小开销](https://leetcode.cn/problems/minimum-cost-to-make-array-equal/)

给你两个下标从 0 开始的数组 nums 和 cost ，分别包含 n 个 正 整数。

你可以执行下面操作 任意 次：

将 nums 中 任意 元素增加或者减小 1 。
对第 i 个元素执行一次操作的开销是 cost[i] 。

请你返回使 nums 中所有元素 相等 的 最少 总开销。

==中位数贪心==

```java
class Solution {
    public long minCost(int[] nums, int[] cost) {
        int n = nums.length;

        Integer[] ids = new Integer[n];
        long costSum = 0;
        for (int i = 0; i < n; i++) {
            ids[i] = i;
            costSum += cost[i];
        }

        Arrays.sort(ids, (a, b) -> {
            return nums[a] - nums[b];
        });

        // 计算加权中位数
        long cnt = 0;
        int mid = 0;
        for (int i = 0; i < n; i++) {
            cnt += cost[ids[i]];
            if (cnt >= costSum / 2) {
                mid = nums[ids[i]];
                break;
            }
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            ans += 1L * Math.abs(mid - nums[i]) * cost[i];
        }
        return ans;
    }
}
```

==枚举==

```java
class Solution:
    def minCost(self, nums: List[int], cost: List[int]) -> int:
        a = sorted(zip(nums, cost))
        ans = total = sum((x - a[0][0]) * c for x, c in a)
        sum_cost = sum(cost)
        for (x0, c), (x1, _) in pairwise(a):
            sum_cost -= c * 2
            total -= sum_cost * (x1 - x0)
            ans = min(ans, total)
        return ans
```

# 316-[2449. 使数组相似的最少操作次数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-arrays-similar/)

给你两个正整数数组 nums 和 target ，两个数组长度相等。

在一次操作中，你可以选择两个 不同 的下标 i 和 j ，其中 0 <= i, j < nums.length ，并且：

令 nums[i] = nums[i] + 2 且
令 nums[j] = nums[j] - 2 。
如果两个数组中每个元素出现的频率相等，我们称两个数组是 相似 的。

请你返回将 nums 变得与 target 相似的最少操作次数。测试数据保证 nums 一定能变得与 target 相似。

==邻项交换法==

```java
class Solution {
    public long makeSimilar(int[] nums, int[] target) {
        f(nums);
        f(target);
        var ans = 0L;
        for (var i = 0; i < nums.length; ++i)
            ans += Math.abs(nums[i] - target[i]);
        return ans / 4;
    }

    private void f(int[] a) {
        // 由于元素都是正数，把奇数变成相反数，这样排序后奇偶就自动分开了
        for (var i = 0; i < a.length; ++i)
            if (a[i] % 2 != 0) a[i] = -a[i];
        Arrays.sort(a);
    }
}
```



 