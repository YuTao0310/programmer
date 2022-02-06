package collection;

public class SortingAlgorithmComparision {
    public static void main(String[] args) {
        int length = 40000;
        int[] array1 = new int[length];
        int[] array2 = new int[length];
        int[] array3 = new int[length];
        for(int i = 0; i < length; i++) {
            array1[i] = (int)(Math.random() * length);
            array2[i] = (int)(Math.random() * length);
            array3[i] = (int)(Math.random() * length);
        }

        long selectionSortTime = 0;
        long bubbleSortTime = 0;
        long binaryTreeTime = 0;
        /** 1、比较法排序 */
        long start = System.currentTimeMillis();
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (array1[i] <= array1[j]) {
                    int temp = array1[i];
                    array1[i] = array1[j];
                    array1[j] = temp;
                }
            }
        }
        long end = System.currentTimeMillis();
        selectionSortTime = end - start;

        /** 2、冒泡法排序 */
        start = System.currentTimeMillis();
        for (int i = length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array2[j] > array2[j + 1]) {
                    int temp = array2[j];
                    array2[j] = array2[j + 1];
                    array2[j + 1] = temp;
                }
            }
        }
        end = System.currentTimeMillis();
        bubbleSortTime = end - start;

        /** 3、二叉排序 */
        start = System.currentTimeMillis();
        Node bTree = new Node();
        for (int i = 0; i < length; i++) {
            bTree.add(array3[i]);
        }
        bTree.values();
        end = System.currentTimeMillis();
        binaryTreeTime = end - start;
        System.out.println("1、选择排序时间:\t" + selectionSortTime + "\n"
                          +"2、冒泡排序时间:\t" + bubbleSortTime + "\n"
                          +"3、二叉排序时间:\t" + binaryTreeTime);
    }
}
