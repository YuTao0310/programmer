package digit;
import java.util.Scanner;

public class CountPrimes {

    public static  boolean isPrime(int number){
        if(number == 1)
            return false;

        int n = (int)Math.sqrt(number);
        for(int i = 2; i <=n; i++)
            if(number % i ==0)
                return false;

        return true;
    }                                                                                                                    
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("the range number is: ");
        int range = s.nextInt();
        s.close();
        int sumOfPrimes = 0;
        for(int i = 1; i <= range; i++)
            if(isPrime(i))
                sumOfPrimes++;
        System.out.println("total primes between 1 and " + range + " is: " + sumOfPrimes);
    }
}
