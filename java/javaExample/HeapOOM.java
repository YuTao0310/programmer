import java.util.ArrayList;
import java.util.List;

/**
 * VM args: -Xms20m -Xmx20m
 */
public class HeapOOM {
    static class t {

    }
    public static void main(String[] args) {
        test();
    }

    private static void test() {
        List<String> list = new ArrayList<>();
        while (true) {
            list.add("23");
        }
    }
}
