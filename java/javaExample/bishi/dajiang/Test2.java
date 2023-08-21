import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Solution {

    /* Write Code Here */
    public int car_plan(int[][] paths, int dis, int a, int b, int[] charge) {


    }
}

public class Test2 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;
    
        int paths_rows = 0;
        int paths_cols = 0;
        paths_rows = in.nextInt();
        paths_cols = in.nextInt();
        
        int[][] paths = new int[paths_rows][paths_cols];
        for(int paths_i=0; paths_i<paths_rows; paths_i++) {
            for(int paths_j=0; paths_j<paths_cols; paths_j++) {
                paths[paths_i][paths_j] = in.nextInt();
            }
        }

        if(in.hasNextLine()) {
          in.nextLine();
        }

        
        int dis;
        dis = Integer.parseInt(in.nextLine().trim());
        
        int a;
        a = Integer.parseInt(in.nextLine().trim());
        
        int b;
        b = Integer.parseInt(in.nextLine().trim());
        
        int charge_size = 0;
        charge_size = in.nextInt();
        int[] charge = new int[charge_size];
        for(int charge_i = 0; charge_i < charge_size; charge_i++) {
            charge[charge_i] = in.nextInt();
        }

        if(in.hasNextLine()) {
          in.nextLine();
        }

        res = new Solution().car_plan(paths, dis, a, b, charge);
        System.out.println(String.valueOf(res));    

    }
}
