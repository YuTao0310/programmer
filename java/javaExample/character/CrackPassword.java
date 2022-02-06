package character;
import java.util.Arrays;
import java.util.Scanner;

public class CrackPassword {

    //使用for循环来进行破解
    static char[] enumerationCrack(char[] s){

        char[] codeCrack = new char[s.length];
        for(int k = 0; k < s.length; k++)
            for(short i = '0'; i <= 'z'; i++){
                if(i == s[k]){              //可以使用==来比较short和char，可以直接使用char类型进行基本运算
                    codeCrack[k] = (char)i; //short转char可以自动转，但char不能自动转short
                    break;
                }
            }
        return codeCrack;

    }

    //也可以直接用System.out.println
    static void printArray(char[] array){
        for(char each : array){
            System.out.print(each + ", ");
        }
        System.out.println(' ');
    }

    //使用递归方法来进行破解
    static char[] recursionCrack(char[] s){
        char findChar = '0';
        for(short i = '0'; i < 'z'; i++)
            if(i == s[0]){
                findChar = (char)i;
                break;
            }

        char[] findCharArray = new char[s.length];
        findCharArray[0] = findChar;

        if(s.length > 1)   
        {
            char[] newArray =  Arrays.copyOfRange(s, 1, s.length);
            newArray = recursionCrack(newArray);
            System.arraycopy(newArray, 0, findCharArray, 1, s.length - 1); 
        }
        return findCharArray;
    }
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("input code length: ");
        int length = s.nextInt();
        s.close();

        char[] charArray = new char[length];
        for(int i = 0; i < length; i++){
            charArray[i] = RandomString.getRandomChar();
        }

        System.out.println(charArray);

        char[] enumeCode = enumerationCrack(charArray);
        System.out.println(enumeCode);

        char[] recursionCode = recursionCrack(charArray);
        System.out.println(recursionCode);
    }
}
