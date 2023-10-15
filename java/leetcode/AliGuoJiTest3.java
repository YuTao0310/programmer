import java.util.*;

public class AliGuoJiTest3 {

    private static class Node {
        int index;
        int val;
        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Node[][] path = new Node[n + 1][2];
        for (int i = 0; i < n; i++) {
            int x = in.nextInt(), y = in.nextInt(), val = in.nextInt();
            if (path[x][0] == null) path[x][0] = new Node(y, val);
            else path[x][1] = new Node(y, val);
            if (path[y][0] == null) path[y][0] = new Node(x, -val);
            else path[y][1] = new Node(x, -val);
        }
        int temp1 = 0, temp2 = 0;
        int next = 1, prev = 0;
        do {
            Node node = path[next][0].index != prev ? path[next][0] : path[next][1];
            prev = next;
            next = node.index;
            if (node.val > 0) temp1 += node.val;
            else temp2 += node.val;
        } while (next != 1);

        System.out.println(Math.min(temp1, -temp2));

    }
}
