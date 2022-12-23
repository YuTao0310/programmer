package quickStart;

public class ThisInConstructor {
        
    String name; //姓名
        
    float hp; //血量
        
    float armor; //护甲
        
    int moveSpeed; //移动速度
        
    //带一个参数的构造方法
    public ThisInConstructor(String name){
        System.out.println("一个参数的构造方法");
        this.name = name;
    }
      
    //带两个参数的构造方法
    public ThisInConstructor(String name,float hp){
        this(name);
        System.out.println("两个参数的构造方法");
        this.hp = hp;
    }

    //利用this调用之前定义的构造方法
    public ThisInConstructor(String name, float hp, float armor, int moveSpeed){
        this(name, hp);
        this.armor = armor;
        this.moveSpeed = moveSpeed;
    }
 
    public static void main(String[] args) {
        ThisInConstructor teemo =  new ThisInConstructor("提莫", 383, 70, 78);
         
        System.out.println(teemo.name);
         
    }
      
}