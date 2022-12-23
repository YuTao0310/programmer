import java.util.ArrayList;
import java.util.List;

public class ArrayListFailFast {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        // list.add("four");
        for (String s : list) {
            if ("one".equals(s)) {
                list.remove(s);
            }
        }
        System.out.println(list);
    }
}
