package bishi;
import java.util.Arrays;

public class ZijieTest {
    public static void main(String[] args) {
        System.out.println(build(500, new int[]{5, 6, 8, 7}));
    }
    public static int build(int num, int[] arr) {
        //初始化参数
        String str = String.valueOf(num - 1);
        str = str + str.charAt(str.length() - 1);
        char[] res = new char[str.length() - 1];
        Arrays.fill(res, '0');
        Arrays.sort(arr);
        int len = res.length;
        boolean flag = false;
        for (int i = 0; i < len; i++) {//从高位到低位遍历原数字
            int index = flag ? arr.length - 1 : search(str, i, arr);
            res[i] = index != -1 ? (char) (arr[index] + '0') : '0';
            if (res[i] < str.charAt(i)) {
                flag = true;
            }
        }
        return Integer.parseInt(new String(res).substring(0,res.length));
    }
    public static int search(String str, int i, int[] arr) {
        int find = str.charAt(i + 1) - '0' >= arr[0] ? str.charAt(i) - '0' : str.charAt(i) - '0' - 1;
        int left = 0, right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (find > arr[mid]) {
                left = mid + 1;
            } else if (find < arr[mid]) {
                right = mid - 1;
            } else {
                index = mid;
                break;
            }
        }
        return index == left + ((right - left) >> 1) ? index : right;
    }
}
