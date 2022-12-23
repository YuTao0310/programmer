package charactor;

public class ADAPHero extends Hero implements AD, AP, Mortal {
    
    public ADAPHero(String name){
        super(name);
    }
    
    public ADAPHero(){
        super();
    }

    @Override
    public void physicAttack(){
        System.out.println("进行物理攻击");
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
