package generic;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.concurrent.ConcurrentMap;
import java.util.zip.Adler32;

import charactor.ADHero;
import charactor.APHero;
import charactor.Hero;

public class GenericTest {

    public static void iterate(ArrayList<Hero> list) {
        for (Hero hero : list) {
            System.out.println(hero.name);
        }
    }

    public static void iterateAP(ArrayList<APHero> list) {
        for (Hero hero : list) {
            System.out.println(hero.name);
        }
    }

    public static void iterateAD(ArrayList<ADHero> list) {
        for (Hero hero : list) {
            System.out.println(hero.name);
        }
    }

    public static void iterateHero(ArrayList<? extends Hero> list) {
        for (Hero h : list) {
            System.out.println(h.name);
        }
    }

    public static void main(String[] args) {

        ArrayList<? super Hero> heroList = new ArrayList<Object>();

        // ? super Hero 表示 heroList的泛型是Hero或者其父类泛型

        // heroList 的泛型可以是Hero
        // heroList 的泛型可以是Object

        // 所以就可以插入Hero
        heroList.add(new Hero());
        // 也可以插入Hero的子类
        heroList.add(new APHero());
        heroList.add(new ADHero());

        System.out.println(heroList.get(0));

        // 但是，不能从里面取数据出来,因为其泛型可能是Object,而Object是强转Hero会失败
        // Hero h= heroList.get(0);
        ADHero a = new ADHero();
        Hero b = a;
        System.out.print(b == a);

    }

}
