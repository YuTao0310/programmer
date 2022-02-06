package quickStart;

public class FinalExample {
    public static void main(String[] args) {
        final byte a = 1;
        final byte b = 1;
        byte c=a+b;
        System.out.println(c);
    }
    /*  
    后者是先定义后赋值，常量池里就有两个空间，一个放a，一个放1（1是int），然后相加哪里加的其实是a里面的值（int的1），所以最后报错int不能转为byte.
    前者能运行的应该就像你问题里查的一样，1是放在a那个空间里的，还是byte类型，所以不需要强转。
    */
    /*
        final byte a;
        a=1;
        final byte b;
        b=2;
        byte c=a+b;
        System.out.println(c);
    */
}
