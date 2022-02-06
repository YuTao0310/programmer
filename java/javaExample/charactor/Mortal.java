package charactor;

public interface Mortal {
    public void die(String name);

    default public void revive(){
        System.out.println("本英雄复活了");
    }
}
