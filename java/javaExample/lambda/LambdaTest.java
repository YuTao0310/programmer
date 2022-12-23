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
            //通过随机值实例化hero的hp和damage
            heros.add(new Hero("hero "+ i, r.nextInt(100), r.nextInt(100)));
        }
        System.out.println("初始化后的集合：");
        System.out.println(heros);
           
        //直接调用sort会出现编译错误，因为Hero有各种属性
        //到底按照哪种属性进行比较，Collections也不知道，不确定，所以没法排
        //Collections.sort(heros);
           
        /** 1、匿名类 */
        Comparator<Hero> c = new Comparator<Hero>() {
            @Override
            public int compare(Hero h1, Hero h2) {
                //按照hp进行排序
                if(h1.getHp() >= h2.getHp())
                    return 1;  //正数表示h1比h2要大
                else
                    return -1;
            }
        };
        Collections.sort(heros,c);
        System.out.println("---------1、匿名类---------");
        System.out.println(heros);

        /** 2、 把外面壳子去掉*/
        c = (Hero h1, Hero h2) -> {
            if (h1.getHp() >= h2.getHp()) 
                return 1;
            else
                return -1;
        };
        Collections.sort(heros,c);
        System.out.println("---------2、把外面壳子去掉---------");
        System.out.println(heros);  

        /** 3、把return和{}去掉 */
        c = (Hero h1, Hero h2) -> h1.getHp() >= h2.getHp() ? h1.getHp() == h2.getHp() ? 0 : 1  : -1;
        Collections.sort(heros,c);
        System.out.println("---------3、把return去掉---------");
        System.out.println(heros);  

        /** 4、把参数类型去掉 */
        c = (h1, h2) -> h1.getHp() >= h2.getHp() ? h1.getHp() == h2.getHp() ? 0 : 1  : -1;
        Collections.sort(heros,c);
        System.out.println("---------4、把参数类型去掉---------");
        System.out.println(heros);  

        /** 5、直接使用lambda参数 */
        Collections.sort(heros, (h1, h2) -> h1.getHp() >= h2.getHp() ? h1.getHp() == h2.getHp() ? 0 : 1  : -1);
        System.out.println("---------5、直接使用---------");
        System.out.println(heros);  

        /** 6、使用静态方法实现 */
        Collections.sort(heros, LambdaTest::compare);
        System.out.println("---------6、使用静态方法---------");
        System.out.println(heros);  

        /** 7、使用容器中的对象方法实现 */
        //就算Hero没有实现容器接口，只是单纯的Hero，也一样能使用构造器方法
        Collections.sort(heros, Hero::compareHero);
        System.out.println("---------7、使用容器中的对象方法---------");
        System.out.println(heros);

        /** 8、引用构造器方法 */
        System.out.println("---------8、使用构造器方法---------");
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
        System.out.printf("在%s 最前面插入%d条数据，总共耗时 %d 毫秒 %n", type, total, end - start);
    }

    private static void insertFirst(Supplier<List<Integer>> s, String type) {
        List<Integer> l = s.get();
        insertFirst(l, type);
    }
}

