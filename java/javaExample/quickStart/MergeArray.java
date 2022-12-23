package quickStart;

public class MergeArray {

    static void initiateArray(int[] array){
        for(int i = 0; i < array.length; i++){
            array[i] = (int)(Math.random() * 100);
        }
    }

    static void printArray(int[] array){
        for(int each : array){
            System.out.print(each + ", ");
        }
        System.out.println(' ');
    }
    public static void main(String[] args) {
        int lengthA = (int)(Math.random() * 5) + 6;
        int lengthB = (int)(Math.random() * 5) + 6;
        int[] arrayA = new int [lengthA];
        int[] arrayB = new int [lengthB];
        int[] arrayMerge = new int [lengthA + lengthB];

        initiateArray(arrayA);
        initiateArray(arrayB);

        System.arraycopy(arrayA, 0, arrayMerge, 0, lengthA);
        System.arraycopy(arrayB, 0, arrayMerge, lengthA, lengthB);

        System.out.println("array A is :");
        printArray(arrayA);
        System.out.println("array B is :");
        printArray(arrayB);
        System.out.println("array merged is :");
        printArray(arrayMerge);   

        /*
        arrayC与arrayB同样指向同一个数组
        若修改arrayC[0]的内容，
        arrayB的内容同样被修改。
        */
        int[] arrayC = arrayB;
        arrayC[0] = 111;
        System.out.println(" ");
        printArray(arrayB);
        printArray(arrayC);
   }
}
