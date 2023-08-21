package bishi;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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
