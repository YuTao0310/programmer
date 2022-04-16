import java.util.Arrays;
import java.util.Scanner;
public class meituan2 {
    static int[] indexs;
    static int[] heights;
    static String[] names;
    public static int[] sort(int[] sourceArray){
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        if (arr.length < 2) {
            return arr;
        }
        int middle = (int) Math.floor(arr.length / 2);

        int[] left = Arrays.copyOfRange(arr, 0, middle);
        int[] right = Arrays.copyOfRange(arr, middle, arr.length);

        return merge(sort(left), sort(right));
    }
    protected static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        while (left.length > 0 && right.length > 0) {
            if (compare(right[0], left[0])) {
                result[i++] = left[0];
                left = Arrays.copyOfRange(left, 1, left.length);
            } else {
                result[i++] = right[0];
                right = Arrays.copyOfRange(right, 1, right.length);
            }
        }
        while (left.length > 0) {
            result[i++] = left[0];
            left = Arrays.copyOfRange(left, 1, left.length);
        }
       while (right.length > 0) {
            result[i++] = right[0];
            right = Arrays.copyOfRange(right, 1, right.length);
        }
        return result;
    }
    protected static boolean compare(int i, int j) {
        if (heights[i] > heights[j]) return true;
        if (heights[i] < heights[j]) return false;
        if (names[i].compareTo(names[j]) >= 0) return true;
        else return false;
    }
    public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        indexs = new int[n];
        heights = new int[n];
        names = new String[n];
        sc.nextLine();
		String str = sc.nextLine().toString();
		String[] arr = str.split(" ");
		for(int j = 0; j < heights.length; j++){
			heights[j] = Integer.parseInt(arr[j]);
        }
        str = sc.nextLine().toString();
        names = str.split(" ");
        for (int i = 0; i < n; i++) {
            indexs[i] = i;
        }
        int ans[] = sort(indexs);
        for (int i = 0; i < n; i++) {
            System.out.print(names[ans[i]] + " ");
        }
        sc.close();
    }
}
