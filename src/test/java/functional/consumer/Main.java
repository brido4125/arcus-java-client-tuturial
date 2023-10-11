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

    int baseNumber = 10;

    //java8 이전에는 baseNumber가 항상 final 키워드가 존재해야 아래와 같이 사용 가능
    //write 하려고 하면 compile error 발생은 동일함
    IntConsumer c3 = v -> {
//      int baseNumber = 11; -> lambda의 경우 내부에서 람다가 구현된 메서드의 로컬 변수와 동일한 변수를 선언할 수 없다.
      // 반면, 익명 클래스나 로컬 내부 클래스는 로컬 변수와 동일한 변수를 선언할 수 있다.
      System.out.println("v = " + v + baseNumber);
    };
    c3.accept(1000);

    // double 값을 받아 출력하는 함수 정의
    DoubleConsumer c4 = a -> System.out.println("입력값 : "+ a);
    c4.accept(100.01);

    // long 값을 받아 출력하는 함수 정의
    LongConsumer c5 = a -> System.out.println("입력값 : "+ a);
    c5.accept(2100000000);
  }
}
