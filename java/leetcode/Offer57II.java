import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Offer57II {

}
class Solution {
    public int[][] findContinuousSequence(int target) {
        List<Integer> possibleStarts = new ArrayList<>();
        Queue<Integer> possibleNums = new LinkedList<>();
        for (int num = target / 2; num >= 2; num--) {
            int mid = target / num, remainder = target % num;
            int temp = (num - 1) /2 + 1;
            int start = mid - temp + 1;
            if (start > 0) 
                if ((num % 2 == 0 && remainder == temp) ||(num % 2 != 0 && remainder == 0)) {
                    possibleStarts.add(start);
                    possibleNums.offer(num);
                }
        }
        int[][] ans = new int[possibleStarts.size()][];
        for (int k = 0; k < possibleStarts.size(); k++) {
            int[] temp = new int[possibleNums.poll()];
            temp[0] = possibleStarts.get(k);
            for (int i = 1; i < temp.length; i++) {
                temp[i] = temp[i - 1] + 1;
            }
            ans[k] = temp;
        }
        return ans;
    }
}