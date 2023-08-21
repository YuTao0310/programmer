package bishi;
public class BaiduTest {

    static int res;
    public static void main(String[] args) {
        int[] nums = new int[] {3, 2, 1, 2, 4};
        int k = 3;
        findKth(nums, k, 0, nums.length - 1);
        System.out.println(res);
    }

    public static boolean findKth(int[] nums, int k, int left, int right) {
        int pivot = left, i = left + 1, j = right;
        if (i > j) return false;
        while (i <= j) {
            if (nums[i] > nums[pivot]) {
                swap(nums, i, j);
                j--;
            } else {
                i++;
            }
        }
        swap(nums, pivot, j);
        if (!findKth(nums, k, left, j - 1)) findKth(nums, k, j + 1, right);
        if (j >= k - 1) {
            res = nums[k - 1];
            return true;
        }
        return false;
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
