package charactor;


public class Hero {
    public String name;
    protected float hp;

    public void kill(Mortal m){
        m.die(this.name);
    }

    public static void battleWin(){
        System.out.println("hero battle win");
    }

    public Hero(){
        this.name = "undefined";
        // System.out.println("Hero的无参构造");
    }

    public Hero(String name){
        this.name = name;
        System.out.println("Hero的有参构造");
    }

    public String toString(){
        return this.name;
    }

    public boolean equals(Object o){
        if(o instanceof Hero){
            Hero h = (Hero) o;
            return this.hp == h.hp;
        }
        return false;
    }

    public void finalize(){
        System.out.println("这个英雄正在被回收");
    }
    public static void main(String[] args) {
         
        Hero h = new Hero();
         
        ADHero ad = new ADHero();

        Support s = new Support();
         
        //类型转换指的是把一个引用所指向的对象的类型，转换为另一个引用的类型
         
        //把ad引用所指向的对象的类型是ADHero
        //h引用的类型是Hero
        //把ADHero当做Hero使用，一定可以
         
        h = ad;
        ad.physicAttack();
        ad = (ADHero)h;
        ad.physicAttack();

        h = s;
        // ad = (ADHero)h; //报错
        s = (Support)h;
        s.heal();

        //多态示例
        Hero garen = new Hero("garen");
        ADHero adc = new ADHero("后裔");
        APHero ap = new APHero("妲己");
        ADAPHero adap = new ADAPHero("夏侯惇");

        garen.kill(adc);
        garen.kill(ap);
        garen.kill(adap);

        //隐藏示例
        APHero.battleWin();

        //toString示例
        System.out.println(garen + " " +adc);

        //垃圾回收示例
        /*
        for (int i = 0; i < 1000000; i++) {
            //不断生成新的对象
            //每创建一个对象，前一个对象，就没有引用指向了
            //那些对象，就满足垃圾回收的条件
            //当，垃圾堆积的比较多的时候，就会触发垃圾回收
            //一旦这个对象被回收，它的finalize()方法就会被调用
            h = new Hero();
        }
        */
        //equals示例
        Hero h1= new Hero();
        h1.hp = 300;
        Hero h2= new Hero();
        h2.hp = 400;
        Hero h3= new Hero();
        h3.hp = 300;
         
        System.out.println(h1.equals(h2));
        System.out.println(h1.equals(h3));

        System.out.println(h1==h2);
        System.out.println(h1==h3);
    }
}