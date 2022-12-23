package project1;

public abstract class Animals {
    protected int legs;
    
    protected Animals(){
        this.legs = 4;
    }

    protected Animals(int legs){
        this.legs = legs;
    }

    public abstract void eat();

    public void walk(){
        System.out.println("使用" + this.legs + "腿走路");
    }

    public static void main(String[] args) {
        Cat cat = new Cat("cat");
        Spider spider = new Spider();
        Fish fish = new Fish();

        Animals ani = cat;
        ani.eat();
        ani.walk();
        ani = spider;
        ani.eat();
        ani.walk();
        ani = fish;
        ani.eat();
        ani.walk();
        Pet pet = cat;
        pet.play();
        System.out.println(pet.getName()); 
        pet = fish;
        pet.play();
        System.out.println(pet.getName());
    }
}
