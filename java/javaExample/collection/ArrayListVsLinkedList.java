package collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ArrayListVsLinkedList {
    
    public static void main(String[] args) {
		List<Integer> l;

		l = new ArrayList<>();
		insertFirst(l, "ArrayList");

		l = new LinkedList<>();
		insertFirst(l, "LinkedList");

        l = new ArrayList<>();
		modify(l, "ArrayList");

		l = new LinkedList<>();
		modify(l, "LinkedList");

        l = new ArrayList<>();
		insertLast(l, "ArrayList");

		l = new LinkedList<>();
		insertLast(l, "LinkedList");
        

        l = new ArrayList<>();
		insertMid(l, "ArrayList");

		l = new LinkedList<>();
		insertMid(l, "LinkedList");
        

	}

	private static void insertFirst(List<Integer> l, String type) {
		int total = 1000 * 100;
		final int number = 5;
		long start = System.currentTimeMillis();
		for (int i = 0; i < total; i++) {
			l.add(0, number);
		}
		long end = System.currentTimeMillis();
		System.out.printf("在%s 最前面插入%d条数据，总共耗时 %d 毫秒 %n%n", type, total, end - start);
	}

    private static void modify(List<Integer> l, String type) {
		int total = 100 * 1000;
		int index = total/2;
		final int number = 5;
		//初始化
		for (int i = 0; i < total; i++) {
			l.add(number);
		}
		
		long start = System.currentTimeMillis();

		for (int i = 0; i < total; i++) {
			 int n = l.get(index);
			 n++;
			 l.set(index, n);
		}
		long end = System.currentTimeMillis();
		System.out.printf("%s总长度是%d，定位到第%d个数据，取出来，加1，再放回去%n 重复%d遍，总共耗时 %d 毫秒 %n", type,total, index,total, end - start);
		System.out.println();
	}

    private static void insertLast(List<Integer> l, String type) {
        int total = 100 * 1000;
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(l.size(), 1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("在%s后面插入%d条数据，耗费时间为%d毫秒%n", type, total, end - start);
        System.out.println();
    }

    private static void insertMid(List<Integer> l, String type) {
        int total = 100 * 1000;
        int index = (int)(total / 2);

        for (int i = 0; i < total; i++) {
            l.add(i, 1);
        }
        long start = System.currentTimeMillis();
        for (int i = 0; i < total; i++) {
            l.add(index, 1);
        }
        long end = System.currentTimeMillis();
        System.out.printf("在长度为%d的%s中间插入%d条数据，耗费时间为%d毫秒%n", total, type, total, end - start);
        System.out.println();
    }
}