import java.util.*;

public class Test {
    public static void main(String[] args) {
        dfs(0, 10);
    }

    static int[] vals = {1, -1};
    static List<Integer> temp = new ArrayList<>();

    public static void dfs(int start, int dim) {
        if (start == dim) {
            // do something
            // compute
            return;
        }

        for (int val : vals) {
            temp.add(val);
            dfs(start + 1, dim);
            temp.remove(temp.size() - 1);
        }
    }
}
/*  */