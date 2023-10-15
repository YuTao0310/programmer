interface InterfaceSubclass {
    default void say() {
        
    }
    public static void sayHello() {

    }
}
public class InterfaceSubclassTest implements InterfaceSubclass {
    public static void main(String[] args) {
        InterfaceSubclass.sayHello();
        InterfaceSubclassTest.sayHello();
        InterfaceSubclassTest t1 = new InterfaceSubclassTest();
        InterfaceSubclass t2 = new InterfaceSubclass() {
            
        };
        t1.say();
        t2.say();
    }
}
