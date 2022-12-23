package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import hero.Hero;
 
public class AggregateTest {
 
    public static void main(String[] args) {
        Random r = new Random();
        List<Hero> heros = new ArrayList<Hero>();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        //����һ���ظ�����
        heros.add(heros.get(0));
        System.out.println("��ʼ�����Ϻ������ (���һ�������ظ�)��");
        System.out.println(heros);
        System.out.println("��������hp>100&&damage<50������");
         
        heros
            .stream()
            .filter(h->h.hp>100&&h.damage<50)
            .forEach(h->System.out.print(h));
         
        System.out.println("ȥ���ظ������ݣ�ȥ����׼�ǿ�equals");
        heros
            .stream()
            .distinct()
            .forEach(h->System.out.print(h));
        System.out.println("����Ѫ������");
        heros
            .stream()
            .sorted((h1,h2)->h1.hp>=h2.hp?1:-1)
            .forEach(h->System.out.print(h));
         
        System.out.println("����3��");
        heros
            .stream()
            .limit(3)
            .forEach(h->System.out.print(h));
         
        System.out.println("����ǰ3��");
        heros
            .stream()
            .skip(3)
            .forEach(h->System.out.print(h));
         
        System.out.println("ת��Ϊdouble��Stream");
        heros
            .stream()
            .mapToDouble(Hero::getHp)
            .forEach(h -> System.out.println(h));

        System.out.println("ת���������͵�Stream");
        heros
            .stream()
            .map((h) -> h.name + " - " + h.hp + " - " + h.damage)
            .forEach(h -> System.out.println(h));

        System.out.println("���������е�ÿ������");
        heros
            .stream()
            .forEach(h -> System.out.print(h));
        System.out.println("����һ������");
        Object[] hs = heros
            .stream()
            .toArray();
        System.out.println(Arrays.toString(hs));
        System.out.println("�����˺���͵��Ǹ�Ӣ��");
        Hero minDamageHero = 
                heros
                .stream()
                .min((h1, h2) -> h1.damage - h2.damage)
                .get();
        System.out.print(minDamageHero);
        System.out.println("�����˺���ߵ��Ǹ�Ӣ��");

        Hero mxnDamageHero = 
                heros
                .stream()
                .max((h1, h2) -> h1.damage - h2.damage)
                .get();
        System.out.print(mxnDamageHero);

        System.out.println("�������ݵ�����");
        long count = heros
                .stream()
                .count();
        System.out.println(count);

        System.out.println("��һ��Ӣ��");
        Hero firstHero = heros
                .stream()
                .findFirst()
                .get();

        System.out.println(firstHero);

        /** ��ӡhp�����ߵ�Ӣ�� */
        heros.clear();
        for (int i = 0; i < 10; i++) {
            heros.add(new Hero("hero " + i, r.nextInt(1000), r.nextInt(100)));
        }
        heros
            .stream()
            .sorted()
            .forEach(h -> System.out.println(h));
        /** 1����ͳ��ʽ */
        System.out.println("��ͳ��ʽ");
        Collections.sort(heros, Hero::compareHero);
        System.out.println(heros.get(2));
        /** 2���ۺϷ�ʽ */
        System.out.println("�ۺϷ�ʽ");
        Object[] sortHeros = 
                heros
                .stream()
                .sorted(Hero::compareHero)
                .toArray();

        String name = heros
                .stream()
                .sorted((h1, h2) -> h1.hp < h2.hp ? -1 : 1)
                .skip(2)
                .map(h -> h.getName())
                .findFirst()
                .get();

        System.out.println(sortHeros[2]);
        System.out.println(name);

    }
}
