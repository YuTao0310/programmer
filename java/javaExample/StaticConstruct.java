class Father {
    static {
        System.out.println("Father static ");
    }

    public Father() {
        System.out.println("Father constructor");
    }
}

class Son extends Father {
    static {
        System.out.println("Son static");
    }

    public Son() {
        System.out.println("Son constructor");
    }
}
public class StaticConstruct {
    public static void main(String[] args) {
        new Son();
    }
}
