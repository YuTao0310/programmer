
package collection;

/*
import java.util.ArrayList;
import java.util.Iterator;
import hero.Hero;

public class ArrayListTest {

	public static void main(String[] args) {
		ArrayList<Hero> heros = new ArrayList<Hero>();
		for (int i = 0; i < 100; i++) { // ??100��������뵽ArrayList??
			heros.add(new Hero("hero " + i));
		}
		System.out.println(heros.toString());
		System.out.println("--------ʹ��while��iterator-------");
		Iterator<Hero> it = heros.iterator();// ���������÷�

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

        //1��for������ʽ
        //ArrayList��ɾ��һ��Ԫ�غ󣬺����Ԫ���Զ���ǰ�ƶ����ȱ��size()�����һ,�ر�ע����ѭ���У����ܻ�����ٱ��������
       
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
            } while (flag && i != heros.size()); //һ��Ҫ����i != heros.size()���������������±�Խ������
                                                 //(��ɾ��������ǡ��Ϊ��������һ������ʱ)
        }
       

        //����һ��forѭ����ʽ
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
        

        //����������ǿfor�������������޷�ʵ��
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

        //2������iterator.remove()
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

        //1������for����
        for (int i = 0; i < heros.size(); i++) {
            Hero h = heros.get(i);
            System.out.print(h + " ");
        }
        System.out.println();

        //2�����õ���������
        //while��д��
        Iterator<Hero> it = heros.iterator();
        while (it.hasNext()) {
            Hero h = it.next();
            System.out.print(h + " ");
        }
        System.out.println();
        //for��д��
        for (Iterator<Hero> it1 = heros.iterator(); it1.hasNext();) {
            Hero h = it1.next();
            System.out.print(h + " ");
        }
        System.out.println();

        //3����ǿ��for
        for (Hero h : heros) {
            System.out.print(h + " ");
        }
        System.out.println();
    }
}


