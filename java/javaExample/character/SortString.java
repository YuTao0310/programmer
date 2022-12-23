package character;
import java.util.Scanner;

public class SortString {
    //类似于s1.equalsIgnoreCase
    static boolean compareString(String s1, String s2){
        s1 = s1.toUpperCase();
        s2 = s2.toUpperCase();
        if(s1.charAt(0) >= s2.charAt(0))
            return true;
        else
            return false;
    }

    static void printStringArray(String[] array){
        for(int i = 0; i < array.length; i++)
            System.out.print(array[i] + ", ");
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        System.out.println("input string array length: ");
        int arrayLength = s.nextInt();
        System.out.println("input length per string: ");
        int stringLength = s.nextInt();
        s.close();

        //生成随机字符串数组
        String[] stringArray = new String[arrayLength];
        char[] charArray = new char[stringLength];
        for(int i = 0; i < arrayLength; i++){
            for(int j = 0; j < stringLength; j++){
                charArray[j] = RandomString.getRandomChar();
            }
            stringArray[i] = new String(charArray);
        }
        
        printStringArray(stringArray);
        System.out.println("\narray sorted is :");

        //对字符串数组进行排序
        for(int i = 0; i < arrayLength - 1; i++)
            for(int j = 0; j < arrayLength - 1 - i; j++){
                if(compareString(stringArray[j], stringArray[j+1])){
                    String temp = stringArray[j];
                    stringArray[j] = stringArray[j+1];
                    stringArray[j+1] = temp;
                }
        }

        printStringArray(stringArray);

    }
}
