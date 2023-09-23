import java.util.*;

public class Test2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());
        String[] strs = in.nextLine().split(",");
        int[] indegs = new int[num];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        String[] temp;
        int next, prev;
        for (String str : strs) {
            temp = str.split(":");
            next = Integer.parseInt(temp[0]);
            prev = Integer.parseInt(temp[1]);
            graph.computeIfAbsent(prev, k -> new ArrayList<>()).add(next);
            indegs[next]++;
        }
        Deque<Integer> queue = new LinkedList<>();
        for (int i : indegs) 
            if (i == 0)
                queue.add(i);
        while(!queue.isEmpty()) {
            num--;
            int t = queue.poll();
            if (!graph.containsKey(t)) continue;
            for (Integer n : graph.get(t))
                if (--indegs[n] == 0)
                    queue.offer(n);
        }

        System.out.println(num == 0 ? 1 : 0);
    }
}
