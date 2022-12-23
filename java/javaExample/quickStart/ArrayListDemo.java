package quickStart;

import java.util.ArrayList;
public class ArrayListDemo{
	public static void main(String[] args) {
		// 创建ArrayList集合
		ArrayList<String> list = new ArrayList<String>();
		// 向集合中添加元素
		list.add("stu1");
		list.add("stu2");
		list.add("stu3");
		list.add("stu4");
		// 获取集合中元素的个数
		System.out.println("集合的长度：" + list.size());
		// 取出并打印指定位置的元素
		System.out.println("第1个元素是：" + list.get(0));
		System.out.println("第2个元素是：" + list.get(1));
		System.out.println("第3个元素是：" + list.get(2));
		System.out.println("第4个元素是：" + list.get(3));
        System.out.println(list);
	}
}