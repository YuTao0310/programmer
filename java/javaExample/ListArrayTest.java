import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListArrayTest {
    public static void main(String[] args) {
        // List<Integer>[] list = new ArrayList[5];
        List<String> list = new ArrayList<>(Arrays.asList("123", "456", "789"));
        String[] str = list.toArray(new String[0]);
        for (String s : str) System.out.println(s);
    }
}
