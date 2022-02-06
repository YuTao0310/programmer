
package collection;

/*
import java.util.ArrayList;
import java.util.Iterator;
import hero.Hero;

public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<Hero> heros = new ArrayList<Hero>();
		for (int i = 0; i < 100; i++) { // ??100个对象加入到ArrayList??
			heros.add(new Hero("hero " + i));
		}
		System.out.println(heros.toString());
		System.out.println("--------使用while的iterator-------");
		Iterator<Hero> it = heros.iterator();// 迭代器的用法

		while (it.hasNext()) {
			Hero h = it.next();
			String[] a = h.getName().split(" ");
			if (Integer.parseInt(a[1]) % 8 == 0) {
				it.remove();
			}
		}
		System.out.println(heros.toString());

	}
}
*/


import java.util.ArrayList;
import java.util.Iterator;

import hero.Hero;

public class ArrayListTest {
    
    private static Boolean containsValueOf(ArrayList<Hero> heros, Hero hero) {
        for (int i = 0; i < heros.size(); i++) {
            Hero h = (Hero)heros.get(i);
            if (hero.getName().equals(h.getName())) {
                return true;
            }
        }
        return false;
    }

    private static void deleteTimes(ArrayList<Hero> heros) {

        //1、for遍历方式
        //ArrayList在删除一个元素后，后面的元素自动向前移动填补空缺，size()立马减一,特别注意在循环中，可能会出现少遍历的情况
       
        for (int i = 0; i < heros.size(); i++) {
            boolean flag;
            do {
                flag = false;
                Hero h = heros.get(i);
                String str = h.getName().substring(4);
                int number = Integer.parseInt(str);
                if (number % 3 == 0 || number % 5 == 0) {
                    //heros.remove(h);
                    heros.remove(i);
                    flag = true;
                }
            } while (flag && i != heros.size()); //一定要加上i != heros.size()，否则会出现数组下标越界的情况
                                                 //(当删除的数据恰好为数组的最后一个数据时)
        }
       

        //另外一种for循环方式
        /*
        int length = heros.size();
        int deleteNum = 0;
        for (int i = 0; i < length; i++) {
            Hero h = heros.get(i - deleteNum);
            String str = h.getName().substring(4);
            int number = Integer.parseInt(str);
            if (number % 3 == 0 || number % 5 == 0) {
                heros.remove(i - deleteNum);
                deleteNum++;
            }
        }
        */
        

        //迭代器、增强for采用下述操作无法实现
        // for (Iterator<Hero> it = heros.iterator(); it.hasNext();) {
        //     Hero h = it.next();
        //     String str = h.getName().substring(4);
        //     int number = Integer.parseInt(str);
        //     if (number % 3 == 0 || number % 5 == 0) {
        //         heros.remove(h);
        //     }    
        // }     

        // for (Hero h : heros) {
        //     String str = h.getName().substring(4);
        //     int number = Integer.parseInt(str);
        //     if (number % 3 == 0 || number % 5 == 0) {
        //         heros.remove(h);
        //     }
        // }

        //2、利用iterator.remove()
        /*
        for (Iterator<Hero> it = heros.iterator(); it.hasNext();) {
            Hero h = it.next();
            String str = h.getName().substring(4);
            int number = Integer.parseInt(str);
            if (number % 3 == 0 || number % 5 == 0) {
                it.remove();
            }    
        }  
        */
        
    }

    public static void main(String[] args) {

        ArrayList<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 100; i++) {
            heros.add(new Hero("hero" + i));
        }

        System.out.println(containsValueOf(heros, new Hero("hero1")));

        deleteTimes(heros);

        //1、采用for遍历
        for (int i = 0; i < heros.size(); i++) {
            Hero h = heros.get(i);
            System.out.print(h + " ");
        }
        System.out.println();

        //2、采用迭代器遍历
        //while的写法
        Iterator<Hero> it = heros.iterator();
        while (it.hasNext()) {
            Hero h = it.next();
            System.out.print(h + " ");
        }
        System.out.println();
        //for的写法
        for (Iterator<Hero> it1 = heros.iterator(); it1.hasNext();) {
            Hero h = it1.next();
            System.out.print(h + " ");
        }
        System.out.println();

        //3、增强型for
        for (Hero h : heros) {
            System.out.print(h + " ");
        }
        System.out.println();
    }
}


