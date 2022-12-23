package generic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import hero.Hero;

public class MyHashMapByGeneric<K,V> implements IHashMap<K,V> {

    @SuppressWarnings("unchecked")
    private final List<Entry<K,V>>[] os = new List[2000]; // 1、Error:private static final List<Entry>[] os = new List<Entry>[2000]

    @Override
    public void put(K key, V object) {
        int hash = hashCode(key);
        if (os[hash] == null) {
            os[hash] = new LinkedList<Entry<K,V>>();
            os[hash].add(new Entry<K,V>(key, object));
        } else {
            os[hash].add(new Entry<K,V>(key, object));
        }
        
    }

    @Override
    public V get(K key) {
        int hash = hashCode(key);
        if (os[hash] != null) {
            for (Entry<K,V> e : os[hash]) {
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
        for (List<Entry<K,V>> l : os) {
            if (l != null) {
                for (Entry<K,V> e : l) {
                    str += e.toString() + " ";
                }
            }
        }
        return str;
    }

    public boolean containsKey(K key) {
        int hash = hashCode(key);
        if (os[hash] != null) {
            for (Entry<K,V> e : os[hash]) {
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

    public int hashCode(K key) {
        if (key instanceof String) {
            String str = (String)key;
            return MyHashMapByGeneric.hashCode(str);
        }
        return Objects.hashCode(key);
    }

    public static void main(String[] args) {

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
        MyHashMapByGeneric<String, List<Hero>> hm = new MyHashMapByGeneric<>();
        for (int i = 0; i < length; i++) {
            if (hm.containsKey(hs[i].getName())) {
                List<Hero> l = hm.get(hs[i].getName());
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