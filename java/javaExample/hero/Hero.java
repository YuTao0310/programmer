package hero;
public class Hero implements Comparable<Hero> {
    public String name; //����
     
    public float hp; //Ѫ��
     
    float armor; //����
     
    int moveSpeed; //�ƶ��ٶ�

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
    //�Ӷ���
    void keng(){
    	System.out.println("�Ӷ��ѣ�");
    }

    //��ȡ����ֵ
    float getArmor(){
    	return armor;
    }
    
    //�����ƶ��ٶ�
    void addSpeed(int speed){
    	//��ԭ���Ļ����������ƶ��ٶ�
    	moveSpeed = moveSpeed + speed;
    }
    
    //����
    void legendary(){
        System.out.println("�����ˣ�");
    }

    //��ȡѪ��
    public float getHp(){
        return hp;
    }

    //�ָ�Ѫ��
    void recover(int blood) {
        hp += blood;
    }

    public synchronized void recover() {
        /** 
         * 1�����Ѫ��������1000 
         * 2��if��Ϊwhile����ֻ֤��hp������1000������Ż�����ִ��
         * 3��Ҳ��ֱ������wait���裬ֱ�ӽ���hp���жϣ�����������ż�Ѫ��
         * ������˼�벻̫����recover������Ŀ�ģ�recoverӦ����Ҫʵ�ּ�Ѫ�ġ�
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
        System.out.println(name + "����1��Ѫ��Ѫ����" + hp);
        this.notifyAll();
    }

    public synchronized void hurt() {
        /** 
         * 1�����Ѫ��������1 
         * 2��if��Ϊwhile����ֻ֤��hp����1������Ż�����ִ��
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
        System.out.println(name + "����1��Ѫ��Ѫ����" + hp);

        this.notifyAll();
    }

    public void attackHero(Hero h) throws EnemyHeroIsDeadException {
    	try {
    		/** Ϊ�˱�ʾ������Ҫʱ�䣬ÿ�ι�����ͣ1000���� */
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	h.hp -= damage;
    	System.out.format("%s ���ڹ��� %s, %s��Ѫ����� %.0f%n", name, h.name, h.name, h.hp);
    	
    	if(h.isDead())
    		System.out.println(h.name +"���ˣ�");
    }

	public boolean isDead() {
		return 0 >= hp ? true : false;
	}

    public void adugen() {
        while (true) {
            for (int i = 0; i < 3; i++) {
                System.out.printf("����ȭ��%d��%n", i + 1);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
 
            System.out.println("��ʼΪʱ5��ĳ���");
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
        garen.name= "����";
        garen.hp  = 500;
        System.out.println(garen.name + " ��ǰ��Ѫ���� " +garen.hp);
        System.out.println("��Ѫ100");
        garen.recover(100);
        System.out.println("���ڵ�Ѫ����:" + garen.hp);
        Hero teemo = new Hero();
        teemo.hp = 0;
        teemo.name = "��Ī";
        
        try {
            garen.attackHero(teemo);
            garen.adugen();
        } catch (EnemyHeroIsDeadException e) {
            System.out.println("�쳣�ľ���ԭ����" + e.getMessage());
            e.printStackTrace();
        }


	}
    
}
