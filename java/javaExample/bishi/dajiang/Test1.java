import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution {

    /* Write Code Here */
    public int circle_fly(int[] charge, int[] cost) {
        int length = charge.length;
        for (int i = 0; i < length; i++) {
            int balary = charge[i];
            int index = i, k = 0;
            for (; k < length; k++) {
                int next = format(index + 1, length);
                if (balary < cost[index]) break;
                balary += charge[next] - cost[index];
                index = next;
            }
            if (k == length) return i;
        }
        return -1;

    }

    private int format(int x, int n) {
        return x < n ? x : x - n ;
    }
}

public class Test1 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;
            
        int charge_size = 0;
        charge_size = in.nextInt();
        int[] charge = new int[charge_size];
        for(int charge_i = 0; charge_i < charge_size; charge_i++) {
            charge[charge_i] = in.nextInt();
        }

        if(in.hasNextLine()) {
          in.nextLine();
        }
        
        int cost_size = 0;
        cost_size = in.nextInt();
        int[] cost = new int[cost_size];
        for(int cost_i = 0; cost_i < cost_size; cost_i++) {
            cost[cost_i] = in.nextInt();
        }

        if(in.hasNextLine()) {
          in.nextLine();
        }

        res = new Solution().circle_fly(charge, cost);
        System.out.println(String.valueOf(res));    

    }
}
