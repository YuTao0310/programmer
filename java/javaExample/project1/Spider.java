package project1;

public class Spider extends Animals {
    public Spider(){
        super(8);
    }

    public Spider(int legs){
        super(legs);
    }

    public void eat(){
        System.out.println("spider eats insects");  
    }
}
