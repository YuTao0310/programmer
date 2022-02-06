package character;
import java.util.ArrayList;

public class CompareString {

    static class Result{
        int count;
        ArrayList<String> type;
    }

    static String[] getRandomStringArray(int lengthArray, int lengthString){
        String[] stringArray = new String[lengthArray]; 
        for(int i = 0; i < lengthArray; i++){
            String newString = "";
            for(int j = 0; j < lengthString; j++){
                newString += RandomString.getRandomChar();
            }
            stringArray[i] = newString;
        }
        return stringArray;
    }
    //最简单的遍历方法
    static Result findDupicates1(String[] stringArray){
        ArrayList<String> duplicateList = new ArrayList<String>();
        int length = stringArray.length;
        int count = 0;
        for(int i = 0; i < length - 1; i++){
            int flag = 0;
            for(int j = i + 1; j < length; j++){
                if(stringArray[i].equals(stringArray[j]))
                    flag++;
            }
            if(flag == 1){
                count++;
                duplicateList.add(stringArray[i]);
            }
        }
        Result res = new Result();
        res.count = count;
        res.type = duplicateList;
        return res;  
    }

    static void printStringArray(String[] stringArray){
        for(int i = 0; i < stringArray.length; i++){
            System.out.print(stringArray[i] + " ");
            if(i % 20 == 19)
                System.out.println();
        }
    }
    public static void main(String[] args) {
        //String[] instance = getRandomStringArray(10, 1);
        String[] instance = new String[]{"1", "3", "3", "2", "2", "0", "0", "2", "3"};
        Result res = findDupicates1(instance);
        printStringArray(instance);
        System.out.format("共有%d种解法%n重复元素有%n%s%n", res.count, res.type);
    }
}
