package project1;

public class Fish extends Animals implements Pet{
    private String name;

    protected Fish(){
        super(0);
        this.name = "fish";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void play() {
        System.out.println("fish plays");
    }

    @Override
    public void eat() {
       System.out.println("fish eats grass"); 
    }   

    @Override
    public void walk(){
        System.out.println("fish can't walk and do'nt have legs");
    }
    
}
