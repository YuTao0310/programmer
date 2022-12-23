/** 性能远远不及HashMap，这是后期需要思考的问题 */
package collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hero.Hero;

public class MyHashMap implements IHashMap {

    @SuppressWarnings("unchecked")
    private static final List<Entry>[] os = new List[2000]; // 1、Error:private static final List<Entry>[] os = new List<Entry>[2000]

    @Override
    public void put(String key, Object object) {
        int hash = hashCode(key);
        if (os[hash] == null) {
            os[hash] = new LinkedList<Entry>();
            os[hash].add(new Entry(key, object));
        } else {
            os[hash].add(new Entry(key, object));
        }
        
    }

    @Override
    public Object get(String key) {
        int hash = hashCode(key);
        if (os[hash] != null) {
            for (Entry e : os[hash]) {
                if (key.equals((String)e.key)) {
                    return e.value;
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String str = "";
        for (List<Entry> l : os) {
            if (l != null) {
                for (Entry e : l) {
                    str += e.toString() + " ";
                }
            }
        }
        return str;
    }

    public boolean containsKey(String key) {
        int hash = hashCode(key);
        if (os[hash] != null) {
            for (Entry e : os[hash]) {
                if (key.equals((String)e.key)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int hashCode(String str) {
        char[] cs = str.toCharArray();
        int hash = 0;
        for (char c : cs) {
            hash += (int)c;
        }
        hash = hash * 23;
        if (hash < 0) {
            hash = -1 * hash;
        }
        if (hash >= 2000) {
            hash = hash % 2000;
        }
        return hash;
    }

    private static char getRandomChar(){
        double rand0 = Math.random();
        int rand;
        if(rand0 < 1.0/3){
            rand = (int)(Math.random() * 4 + 48);
        }else if(rand0 < 2.0/3){
            rand = (int)(Math.random() * 26 + 65);
        }else{
            rand = (int)(Math.random() * 26 + 97);
        }
        return (char)rand;
    }

    private static String getRandomString(int length) {
        String str = "";
        for (int i = 0; i < length; i++) {
            str += getRandomChar();
        }
        return str;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 0; i++) {
            int length = (int)(Math.random() * 9 + 2);
            String str = getRandomString(length);
            int hash = hashCode(str);
            System.out.println("String : " + str + " hash: " + hash);
        }

        MyHashMap m = new MyHashMap();
        m.put("hh", "yyy");
        m.put("hhhh", "ttt");
        System.out.println(m.get("hh"));
        System.out.println(m);

        long start, end;
        int length = 3000000;
        Hero[] hs = new Hero[length];
        for (int i = 0; i < length; i++) {
            int number = (int)(Math.random()*10000);
            hs[i] = new Hero("hero-" + number);
        }
        /** 1、for循环查找 */
        start = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            if (hs[i].getName().equals("hero-5555")) {
                System.out.println("Find it in index " + i);
            }
        }
        end = System.currentTimeMillis();
        long forTime = end - start;

        /** 2、HashMap查找 */
        start = System.currentTimeMillis();
        MyHashMap hm = new MyHashMap();
        for (int i = 0; i < length; i++) {
            if (hm.containsKey(hs[i].getName())) {
                @SuppressWarnings("unchecked")
                List<Hero> l = (List<Hero>) hm.get(hs[i].getName()); //2、downcast
                l.add(hs[i]);
            } else {
                List<Hero> lh = new ArrayList<>();
                lh.add(hs[i]);
                hm.put(hs[i].getName(), lh);
            }
        }
        end = System.currentTimeMillis();
        long createHashMapTime = end - start;

        start = System.currentTimeMillis();
        if (hm.get("hero-5555") != null) {
            System.out.println("Find it " + hm.get("hero-5555"));
        } else {
            System.out.println("Not find it");
        }
        end = System.currentTimeMillis();
        long hashMapTime = end - start;
        System.out.println("For循环时间：" + forTime);
        System.out.println("MyHashMap创建时间：" + createHashMapTime);
        System.out.println("MyHashMap查找时间：" + hashMapTime);

    }
}
