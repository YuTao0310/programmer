package collection;

import java.util.ArrayList;

import charactor.Hero;
import property.Item;

public class ArrayListHeroItem<E> extends ArrayList<E> {
     
    private boolean canAdd(E o) {
        if ((o instanceof Hero) || (o instanceof Item)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean add(E o) {
        return canAdd(o) && super.add(o);
    }

    @Override
    public void add(int pos, E o) {
        if (canAdd(o)) {
            super.add(pos, o);
        }
    }

    public static void main(String[] args) {
        // ArrayListHeroItem a = new ArrayListHeroItem();
        // a.add(new Hero("ty"));
        // a.add(new Item());
        // a.add("hhhh");
        // a.add(12);
    }

    //另外一个角度：也可以考虑Item和Hero继承同一个父类或者同一个接口，然后再声明为ArrayList<父类>、ArrayList<接口>
}