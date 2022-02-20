package collection;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author CRR
 */
public class TestTreeMap {
    public static void main(String[] args) {
        Map<String, String> map = new TreeMap<>();
        map.put("b", "b");
        map.put("a", "a");
        map.put("c", "c");
        System.out.println(map);

        TreeSet<String> set = new TreeSet<>();
    }
}
