package functional.myinterface;

@FunctionalInterface
public interface MyInterface {
    int doAdd(int x);

    static void doStatic() {
        System.out.println("MyInterface.doStatic");
    }

    default void doDefault() {
        System.out.println("MyInterface.doDefault");
    }
}
