package charactor;

public class APHero extends Hero implements AP, Mortal{

    public APHero(String name){
        super(name);
    }

    public APHero(){
        super();
    }

    @Override
    public void magicAttack(){
        System.out.println("进行法术攻击");
    }

    @Override
    public void die(String name){
        System.out.println(name + "杀了" + this.name);
    }
}
