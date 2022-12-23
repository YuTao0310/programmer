import java.util.ArrayList;
import java.util.List;

public class Offer41 {
    class MedianFinder {

        /** initialize your data structure here. */
        private List<Integer> list;
        public MedianFinder() {
            list = new ArrayList<>(); 
        }
        
        public void addNum(int num) {
            int i ;
            for (i = list.size() - 1; i >= 0; i--) {
                if (list.get(i) < num) {
                    break;
                }
            }
            list.add(i + 1, num);
        }
        
        public double findMedian() {
            int length = list.size();
            if (length % 2 == 1) {
                return list.get(length / 2);
            } else {
                return (list.get(length / 2 - 1) + list.get(length / 2)) / 2.0;
            }
        }
    }
}
