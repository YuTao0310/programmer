package lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

import hero.Hero;
    
public class LambdaTest {
    public static void main(String[] args) {
        Random r =new Random();
        List<Hero> heros = new ArrayList<Hero>();
           
        for (int i = 0; i < 10; i++) {
            //ͨ�����ֵʵ����hero��hp��damage
            heros.add(new Hero("hero "+ i, r.nextInt(100), r.nextInt(100)));
        }
        System.out.println("��ʼ����ļ��ϣ�");
        System.out.println(heros);
           
        //ֱ�ӵ���sort����ֱ��������ΪHero�и�������
        //���װ����������Խ��бȽϣ�CollectionsҲ��֪������ȷ��������û����
        //Collections.sort(heros);
           
        /** 1�������� */
        Comparator<Hero> c = new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                //����hp��������
                if(h1.getHp() >= h2.getHp())
                    return 1;  //������ʾh1��h2Ҫ��
                else
                    return -1;
            }
        };
        Collections.sort(heros,c);
        System.out.println("---------1��������---------");
        System.out.println(heros);

        /** 2�� ���������ȥ��*/
        c = (Hero h1, Hero h2) -> {
            if (h1.getHp() >= h2.getHp()) 
                return 1;
            else
                return -1;
        };
        Collections.sort(heros,c);
        System.out.println("---------2�����������ȥ��---------");
        System.out.println(heros);  

        /** 3����return��{}ȥ�� */
        c = (Hero h1, Hero h2) -> h1.getHp() >= h2.getHp() ? h1.getHp() == h2.getHp() ? 0 : 1  : -1;
        Collections.sort(heros,c);
        System.out.println("---------3����returnȥ��---------");
        System.out.println(heros);  

        /** 4���Ѳ�������ȥ�� */
        c = (h1, h2) -> h1.getHp() >= h2.getHp() ? h1.getHp() == h2.getHp() ? 0 : 1  : -1;
        Collections.sort(heros,c);
        System.out.println("---------4���Ѳ�������ȥ��---------");
        System.out.println(heros);  

        /** 5��ֱ��ʹ��lambda���� */
        Collections.sort(heros, (h1, h2) -> h1.getHp() >= h2.getHp() ? h1.getHp() == h2.getHp() ? 0 : 1  : -1);
        System.out.println("---------5��ֱ��ʹ��---------");
        System.out.println(heros);  

        /** 6��ʹ�þ�̬����ʵ�� */
        Collections.sort(heros, LambdaTest::compare);
        System.out.println("---------6��ʹ�þ�̬����---------");
        System.out.println(heros);  

        /** 7��ʹ�������еĶ��󷽷�ʵ�� */
        //����Heroû��ʵ�������ӿڣ�ֻ�ǵ�����Hero��Ҳһ����ʹ�ù���������
        Collections.sort(heros, Hero::compareHero);
        System.out.println("---------7��ʹ�������еĶ��󷽷�---------");
        System.out.println(heros);

        /** 8�����ù��������� */
        System.out.println("---------8��ʹ�ù���������---------");
        insertFirst(ArrayList::new, "ArrayList");
        insertFirst(LinkedList::new, "LinkedList");

    }

    public static int compare(Hero h1, Hero h2) {
        return h1.getHp() >= h2.getHp() ? h1.getHp() == h2.getHp() ? 0 : 1  : -1;
    }

    private static void insertFirst(List<Integer> l, String type) {
        int total = 1000 * 100;
        final int number = 5;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(0, number);
        }
        long end = System.currentTimeMillis();
        System.out.printf("��%s ��ǰ�����%d�����ݣ��ܹ���ʱ %d ���� %n", type, total, end - start);
    }

    private static void insertFirst(Supplier<List<Integer>> s, String type) {
        List<Integer> l = s.get();
        insertFirst(l, type);
    }
}

