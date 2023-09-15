import java.util.*;

public class Test3 {

    public static class Node {
        int index;
        int val;
        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), t = in.nextInt();
        int[] p = new int[n];
        for (int i = 0; i < n; i++) p[i] = in.nextInt();
        int[] originalStatus = count(p);
        List<Node> nodes = new ArrayList<>(p.length);
        for (int i = 0; i < n; i++) nodes.add(new Node(i, p[i] + t * in.nextInt()));
        Collections.sort(nodes, (t1, t2) -> t1.val - t2.val);
        int res = 0;
        int status = 1;
        if (status < originalStatus[nodes.get(p.length - 1).index]) res++;
        for (int i = p.length - 2; i >= 0; i--) {
            Node temp = nodes.get(i), prev = nodes.get(i + 1);
            if (temp.val != prev.val) status = p.length - i;
            if (status < originalStatus[temp.index]) res++;
        }
        System.out.println(res);
    }

    public static int[] count(int[] p) {
        int[] res = new int[p.length];
        int status = 1;
        res[p.length - 1] = 1;
        for (int i = p.length - 2; i >= 0; i--) {
            if (p[i] != p[i + 1]) status = p.length - i;
            res[i] = status;
        }
        return res;
    }
}
