package charactor;
 
public class ADHero extends Hero implements AD, Mortal{
 
    public ADHero(String name) {
        super(name);
        System.out.println("ADHero的有参数构造");
    }

    public ADHero(){
        super();
    }

    @Override
    public void physicAttack() {
        System.out.println("进行物理攻击");
    }

    @Override
    public void die(String name){
        System.out.println(name + "杀了" + this.name);
    }
 
    public static void battleWin(){
        System.out.println("ad hero battle win");
    }
    
    public static void main(String[] args) {
        Hero.battleWin();
        ADHero.battleWin();

        // Hero h0 = new ADHero();
        // h0.battleWin();

        ADHero h = new ADHero("delaiwen");
        System.out.println(h);

        h.revive();


    }
}