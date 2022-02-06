package digit;

public class CalculateE {
    public static void main(String[] args) {
        int N = (int)1E2;
        double eCalc = 0;
        double eTrue = Math.E;
        for(int n = 1; n <= N; n++){
            eCalc = Math.pow(1 + 1.0/n, n);
            System.out.println("n is " + n + ", error between eCalc and eTrue is " + (eCalc - eTrue)); 
        }

    }
}
