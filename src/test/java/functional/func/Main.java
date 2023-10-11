package functional.func;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        //Fuction의 T와 R이 동일하면 UnaryOperator로 사용 가능
        Function<Integer, Integer> addTen = x -> x + 100;
        Function<Integer, Integer> divide = x -> x - 4;
        // 함수 조합용 Default Method인 andThen, compose 제공
        // compose는 invoker가 나중에 호출됨, 여기서는 addTen이 먼저 호출 후, 빼기
        //10 + 100 -> 110 - 4 = 106
        Integer res = divide.compose(addTen).apply(10);
        System.out.println("res = " + res);

        // 10 - 4 -> 6 + 100 = 106
        Integer res2 = divide.andThen(addTen).apply(10);
        System.out.println("res2 = " + res2);
    }
}
