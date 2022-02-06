/** 为了更加清晰继承的原理 */
package collection;

import java.util.ArrayList;
import java.util.List;

import hero.Hero;

public class HeroNode extends Node {

    /** compared单独列出来是为了不需要对add方法进行重写 */
    @Override
    public boolean compare(Object v1, Object v2) {
        Hero i1 = (Hero)v1;
        Hero i2 = (Hero)v2;
        return i1.getHp() - i2.getHp() <= 0;
    }    

    /** 采用右-中-左遍历方式，结果为由大到小排序 */
    public List<Object> sortedValues() {
            List<Object> values = new ArrayList<>();
    
    
            if (rightNode != null) {
                values.addAll(rightNode.values());
            }
    
            values.add(value);
    
            if (leftNode != null) {
                values.addAll(leftNode.values());
            }
            
            return values;
    }

    static void printValue(List<Object> values) {
        for (Object o : values) {
            Hero h = (Hero)o;
            System.out.println(h.getName() + " " + h.getHp());
        }
    }

    public void special() {};
    public static void main(String[] args) {
        Node n = new HeroNode();
        n.compare(new Hero("hh", 12), new Hero("yy", 13));
        HeroNode hRoots = new HeroNode();
        float[] hps = new float[] {988, 809, 786, 566
                                  ,748, 178, 569, 572
                                  ,448, 679};
        for (int i = 0; i < hps.length; i++) {
            hRoots.add(new Hero("hero " + i, hps[i]));
            System.out.println("hero" + i + "  " + hps[i]);
        }
        System.out.println("Hp由到大到小的结果：");
        printValue(hRoots.sortedValues());
        

    }
}
