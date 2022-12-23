import java.util.Scanner;

public class zijie2 {
    static int[] powers;
    static int[] states;
    static int N;
    static boolean isFinal(int start) {
        if (start >= N) {
            return true;
        }
        if (states[start] == -1) {
            return false;
        }
        int power = powers[start];
        for (int i = 1; i <= power; i++) {
            if (isFinal(start + i)) {
                return true;
            }
        }
        states[start] = -1;
        return false;
    }
   public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       N = input.nextInt();
       powers = new int[N];
       states = new int[N];
       input.nextLine();
       String[] strs = input.nextLine().toString().split(" ");
       for (int i = 0; i < N; i++) {
           powers[i] = Integer.parseInt(strs[i]);
       }
       boolean ans = isFinal(0);
       System.out.println(ans ? "TRUE" :"FALSE");
       input.close();
   } 
}
