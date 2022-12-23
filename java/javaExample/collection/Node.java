package collection;

import java.util.ArrayList;
import java.util.List;


public class Node {
    public Node leftNode;
    public Node rightNode;
    public Object value;

    public void add(Object v) {
        if (value == null) {
            value = v;
        } else {
            if (compare(v, value)) {
                if (leftNode == null) {
                    // leftNode = new Node();
                    /** �����ǲ��÷��䷽������ */
                    try {
                        leftNode = getClass().getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                leftNode.add(v);
            } else {
                if (rightNode == null) {
                    // rightNode= new Node(); 
                    try {
                        rightNode = getClass().getDeclaredConstructor().newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                rightNode.add(v);   
            }
        }
    }

    /** ����������нڵ� */
    public List<Object> values() {
        List<Object> values = new ArrayList<>();

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
    public boolean compare(Object v1, Object v2) {
        Integer i1 = (Integer)v1;
        Integer i2 = (Integer)v2;
        return i1 - i2 <= 0;
    }
    public static void main(String[] args) {
        int randoms[] = new int[] {67, 7, 30, 73, 10, 0, 78, 81, 10, 74};

        Node roots = new Node();
        for (int number : randoms) {
            roots.add(number);
        }
        System.out.println(roots.values());
    }
}
