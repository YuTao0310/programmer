package generic;

import java.util.ArrayList;
import java.util.List;

import hero.Hero;


public class Node<T extends Comparable<T>> {
    public Node<T> leftNode;
    public Node<T> rightNode;
    public T value;

    public void add(T v) {
        if (value == null) {
            value = v;
        } else {
            if (v.compareTo(value) == -1) {
                if (leftNode == null) {
                    // leftNode = new Node();
                    /** �����ǲ��÷��䷽������ */
                    try {
                        @SuppressWarnings("unchecked")
                        Node<T> n = getClass().getDeclaredConstructor().newInstance();
                        leftNode = n;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                leftNode.add(v);
            } else {
                if (rightNode == null) {
                    // rightNode= new Node(); 
                    try {
                        @SuppressWarnings("unchecked")
                        Node<T> n = getClass().getDeclaredConstructor().newInstance();
                        rightNode = n;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                rightNode.add(v);   
            }
        }
    }

    /** ����������нڵ� */
    public List<T> values() {
        List<T> values = new ArrayList<>();

        if (leftNode != null) {
            values.addAll(leftNode.values());
        }

        values.add(value);

        if (rightNode != null) {
            values.addAll(rightNode.values());
        }
        
        return values;
    }
    /** v1��v2С������true */
    public boolean compare(T v1, T v2) {
        Integer i1 = (Integer)v1;
        Integer i2 = (Integer)v2;
        return i1 - i2 <= 0;
    }
    public static void main(String[] args) {
        int randoms[] = new int[] {67, 7, 30, 73, 10, 0, 78, 81, 10, 74};

        Node<Integer> roots = new Node<>();
        for (int number : randoms) {
            roots.add(number);
        }
        System.out.println(roots.values());

        Node<Hero> hRoots = new Node<>();
        float[] hps = new float[] {988, 809, 786, 566
                                  ,748, 178, 569, 572
                                  ,448, 679};
        for (int i = 0; i < hps.length; i++) {
            hRoots.add(new Hero("hero " + i, hps[i]));
            System.out.println("hero" + i + "  " + hps[i]);
        }
        System.out.println("Hp�ɵ�С����Ľ����");
        System.out.print(hRoots.values());
    }
}
