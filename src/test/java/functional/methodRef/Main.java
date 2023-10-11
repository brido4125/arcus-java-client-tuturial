package functional.methodRef;

import refactor.cancel.FutureCancel;

import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        UnaryOperator<String> op = (s) -> "hi" + s;
        UnaryOperator<String> op2 = Greerting::hi;

        Greerting g = new Greerting();

        UnaryOperator<String> op3 = g::hello; // instance method를 메소드 레퍼런스 사용할 경우

        //Supplier -> 입력값 없고 리턴값 존재
        Supplier<Greerting> newGreeting = Greerting::new;
        Greerting greerting = newGreeting.get();

        //String을 받는 인자를 사용하는 메소드 레퍼런스
        Function<String,Greerting> newGreetingByStr = Greerting::new;
    }
}
