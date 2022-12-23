package hero;
public class Hero implements Comparable<Hero> {
    public String name; //姓名
     
    public float hp; //血量
     
    float armor; //护甲
     
    int moveSpeed; //移动速度

    public int damage;

    public Hero() {
        this.name = "default";
    }

    public Hero(String name) {
        this.name = name;
    }
    
    public Hero(String name, float hp) {
        this.name = name;
        this.hp = hp;
    }

    public Hero(String name, float hp, int damage) {
        this(name, hp);
        this.damage = damage;
    }

    public String getName() {
        return this.name;
    }
    //坑队友
    void keng(){
    	System.out.println("坑队友！");
    }

    //获取护甲值
    float getArmor(){
    	return armor;
    }
    
    //增加移动速度
    void addSpeed(int speed){
    	//在原来的基础上增加移动速度
    	moveSpeed = moveSpeed + speed;
    }
    
    //超神
    void legendary(){
        System.out.println("超神了！");
    }

    //获取血量
    public float getHp(){
        return hp;
    }

    //恢复血量
    void recover(int blood) {
        hp += blood;
    }

    public synchronized void recover() {
        /** 
         * 1、最大血量不超过1000 
         * 2、if改为while，保证只有hp不大于1000，程序才会往下执行
         * 3、也有直接舍弃wait步骤，直接进行hp的判断，如果不超过才加血，
         * 但这种思想不太符合recover函数的目的，recover应该是要实现加血的。
         */
        //if (hp == 1000) {
        while (hp >= 1000) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
  
        hp += 1;
        System.out.println(name + "加了1点血后，血量是" + hp);
        this.notifyAll();
    }

    public synchronized void hurt() {
        /** 
         * 1、最低血量不低于1 
         * 2、if改为while，保证只有hp大于1，程序才会往下执行
        */

        //if (hp == 1) {
        while (hp <= 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        hp -= 1;
        System.out.println(name + "减了1点血后，血量是" + hp);

        this.notifyAll();
    }

    public void attackHero(Hero h) throws EnemyHeroIsDeadException {
    	try {
    		/** 为了表示攻击需要时间，每次攻击暂停1000毫秒 */
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	h.hp -= damage;
    	System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n", name, h.name, h.name, h.hp);
    	
    	if(h.isDead())
    		System.out.println(h.name +"死了！");
    }

	public boolean isDead() {
		return 0 >= hp ? true : false;
	}

    public void adugen() {
        while (true) {
            for (int i = 0; i < 3; i++) {
                System.out.printf("波动拳第%d发%n", i + 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
 
            System.out.println("开始为时5秒的充能");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
 
    }

    public int compareHero(Hero h) {
        return this.getHp() >= h.getHp() ? this.getHp() == h.getHp() ? 0 : 1  : -1;
    }

    class EnemyHeroIsDeadException extends Exception {
        
    	public EnemyHeroIsDeadException(){
    		
    	}
        public EnemyHeroIsDeadException(String msg) {
            super(msg);
        }
    }

    @Override
    public String toString() {
        return "[name: " + name + ", hp: " + hp + "]\r\n";
    }

    // public Boolean equals(Hero hero) {
    //     return this.name.equals(hero.name);
    // }
    @Override
    public boolean equals(Object o) {
        if (o instanceof Hero) {
            Hero h = (Hero) o;
            return this.name.equals(h.name);
        }
        return false;
    }

    @Override
    public int compareTo(Hero h) {
        if (this.hp < h.hp) {
            return -1;
        } else if (this.hp == h.hp) {
            return 0;
        } else {
            return 1;
        }
    }

    public static void main(String[] args) {
        Hero garen = new Hero();
        garen.name= "盖伦";
        garen.hp  = 500;
        System.out.println(garen.name + " 当前的血量是 " +garen.hp);
        System.out.println("回血100");
        garen.recover(100);
        System.out.println("现在的血量是:" + garen.hp);
        Hero teemo = new Hero();
        teemo.hp = 0;
        teemo.name = "提莫";
        
        try {
            garen.attackHero(teemo);
            garen.adugen();
        } catch (EnemyHeroIsDeadException e) {
            System.out.println("异常的具体原因是" + e.getMessage());
            e.printStackTrace();
        }


	}
    
}
