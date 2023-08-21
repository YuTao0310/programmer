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