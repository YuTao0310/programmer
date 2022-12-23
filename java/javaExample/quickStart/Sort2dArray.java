package quickStart;

import java.util.Arrays;

public class Sort2dArray {

    static void initiate2dArray(int[][] array) {
        for (int i = 0; i < array.length; i++)
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = (int) (Math.random() * 100);
            }
    }
/*
注释方法可以用System.out.println(Arrays.toString(array)代替)。
Arrays.toString输出的是一维形式，不是二维形式。
*/
/*
    static void print2dArray(int[][] array) {
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + ", ");
            }
           System.out.println(""); 
        }
    }
*/
static void print2dArray(int[][] array) {
    for (int i = 0; i < array.length; i++){
        System.out.println(Arrays.toString(array[i]));
    }
}    

/*
将每一行中的数组进行排序
*/
    static void sort2dArray(int[][] array){
        for(int i = 0; i < array.length; i++){
            Arrays.sort(array[i]);            
        }
    }

/*
将二维数组中的所有元素进行排序
*/
    static void sort2dAll(int[][] array){

        int lengthAll = 0;
        for(int i = 0; i < array.length; i++)
            lengthAll += array[i].length;
        int[] arrayAll = new int[lengthAll];
        int destPos = 0;
        for(int i = 0; i < array.length; i++){
            System.arraycopy(array[i], 0, arrayAll, destPos, array[i].length);
            destPos += array[i].length;
        }
        Arrays.sort(arrayAll);

        int from = 0;
        for(int i = 0; i < array.length; i++){
            int to = from + array[i].length;
            array[i] = Arrays.copyOfRange(arrayAll, from, to);
            from += array[i].length;
        }
           
    }
    public static void main(String[] args) {
        int[][] array = new int[5][8];
        initiate2dArray(array);
        System.out.println("array is :");
        print2dArray(array);
        
        System.out.println("array sorted by line is :");
        sort2dArray(array);
        print2dArray(array);

        System.out.println("array sorted by all is :");
        sort2dAll(array);
        print2dArray(array);
    }
}