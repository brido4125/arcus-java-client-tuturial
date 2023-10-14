package functional.defaultInterface;

public interface DefaultInferface {
    default String hello() {
        return "hello";
    }
}
