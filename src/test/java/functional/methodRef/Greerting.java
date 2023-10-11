package functional.methodRef;

public class Greerting {
    private String name;

    public Greerting() {

    }

    public Greerting(String s) {
    }

    public String hello(String name) {
        return "hello" + name;
    }

    public static String hi(String name) {
        return "hi" + name;
    }
}
