package functional.consumer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.DoubleConsumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;

public class Main {
  public static void main(String[] args) {
    Consumer<String> c1 = t -> System.out.println("t = " + t);
    c1.accept("테스트");

    BiConsumer<String, Integer> c2 = (a, b) -> System.out.println("a = " + a + " b = " + b);
    c2.accept("Bi", 4);

    IntConsumer c3 = v -> System.out.println("v = " + v);
    c3.accept(1000);

    // double 값을 받아 출력하는 함수 정의
    DoubleConsumer c4 = a -> System.out.println("입력값 : "+ a);
    c4.accept(100.01);

    // long 값을 받아 출력하는 함수 정의
    LongConsumer c5 = a -> System.out.println("입력값 : "+ a);
    c5.accept(2100000000);
  }
}
