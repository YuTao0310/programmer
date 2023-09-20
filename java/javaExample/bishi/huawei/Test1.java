import java.util.*;

public class Test1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = in.nextInt();
        int target = in.nextInt();
        int move = nums.length;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                move = i;
                break;
            }
        }
        int[] temp = new int[n];
        for (int i = move; i < nums.length; i++) {
            temp[i - move] = nums[i];
        }
        int a = nums.length - move;
        for (int i = 0; i < move; i++) {
            temp[i + a] = nums[i];
        }
        int right = search(temp, target);
        int left = search(temp, target - 1) + 1;
        if (left > right) {
            left = -1;
            right = -1;
        } else {
            left = left >= a ? left - a : move + left;
            right = right >= a ? right - a : move + right;
        }
        System.out.printf("%d %d\n", left, right);

    }

    private static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > target) right = mid - 1;
            else left = mid + 1;
        }
        return right;
    }
}