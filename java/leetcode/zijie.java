import java.util.HashMap;

public class zijie {
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5, 6};
        System.out.println(getSum(a, 7));
    }

    private static int getSum(int[] a, int sum) {
        int len = a.length;
        HashMap<Integer, Integer> h = new HashMap<>();
        // 先遍历一遍数组，将每个数存在hashmap中，对应的value=1；表示存在次数
        for (int i = 0; i < len; i++) {
            h.put(a[i], 1);
        }
        // 再遍历一遍数组，查看sum-i的是否在hashmap中，在则表示查找成功，不在则表示查找失败
        // 【若记录次数的话，每个数仅可用一次，则最后的cunt/2即为所求值】
        boolean flag = false;
        int count = 0;
        for (int i = 0; i < len; i++) {
            if (h.containsKey(sum - a[i])) {
                count++;
                flag = true;// 表示查找到了
            }
        }
        if (flag == false)
            return -1;
        else
            return count / 2;
    }
}
