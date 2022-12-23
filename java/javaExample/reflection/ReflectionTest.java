package reflection;

import hero.Hero;

public class ReflectionTest {

	public static void main(String[] args) {
			String className = "charactor.Hero";
			try {
				Class<?> pClass1=Class.forName(className);
				Class<?> pClass2=Hero.class;
				Class<?> pClass3=new Hero().getClass();
				System.out.println(pClass1==pClass2);
				System.out.println(pClass1==pClass3);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
	}
}
