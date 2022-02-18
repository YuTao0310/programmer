public class Offer11 {
    
    static class Solution1 {
        public int minArray(int[] numbers) {
            int max = numbers.length - 1;
            for (int i = max; i > 0; i--) {
                if (numbers[i - 1] > numbers[i]) {
                    return numbers[i];
                }
            }
            return numbers[0];
        }
    }
    static class Solution2 {
        public int minArray(int[] numbers) {
            int low = 0;
            int high = numbers.length - 1;
            while (low < high) {
                int pivot = low + (high - low) / 2;
                if (numbers[pivot] < numbers[high]) {
                    high = pivot;
                } else if (numbers[pivot] > numbers[high]) {
                    low = pivot + 1;
                } else {
                    high -= 1;
                }
            }
            return numbers[low];
        }
    }

}
