package functional.defaultInterface;

public interface Another extends DefaultInferface {

    // default method를 재정의 할 경우, default 구현을 사용하지 않게됨
    String hello();
}
