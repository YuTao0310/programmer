import java.util.*;
import java.util.stream.IntStream;


class SolutionL {
    public int candy(int[] ratings) {
        int[] temp = new int[ratings.length];
        for (int i = 1; i < ratings.length; i++) {
            if (ratings[i] > ratings[i - 1]) temp[i] = temp[i - 1] + 1;
        }

        for (int i = ratings.length - 2; i >= 0; i++) {
            if (ratings[i] > ratings[i + 1]) temp[i] = Math.max(temp[i], temp[i + 1] + 1);
        }

        int res = 0;
        for (int num : temp) res += num;
        return res + ratings.length;
    }

}

public class LeetCodeTest {
    public static void main(String[] args) {
        new SolutionL().candy(new int[]{1, 0, 2});
    }
}




