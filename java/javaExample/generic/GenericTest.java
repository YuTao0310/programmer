package generic;

import java.util.ArrayList;

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
		ArrayList<Hero> hs = new ArrayList<>();
		ArrayList<APHero> aphs = new ArrayList<>();
		ArrayList<ADHero> adhs = new ArrayList<>();

		iterate(hs);
		iterateAP(aphs);
		iterateAD(adhs);
        iterateHero(hs);
        iterateHero(aphs);
        iterateHero(adhs);

        /** 子类泛型无法转为父类泛型 */
        //hs = adhs;

        /** 父类泛型无法转为子类泛型 */
        //adhs = hs;
	}

}
