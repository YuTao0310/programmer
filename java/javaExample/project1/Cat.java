package project1;

public class Cat extends Animals implements Pet{

    private String name;

    protected Cat(){
        super(4);
        this.name = "undefined";
    }

    protected Cat(String name){
        super(4);
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public void eat(){
        System.out.println("cat eats fish and spider");
    }

    public void play(){
        System.out.println("cat plays ");
    }
}
