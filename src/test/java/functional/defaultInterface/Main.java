package functional.defaultInterface;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<String> name = new LinkedList<>();
        name.add("hong");
        name.add("song");
        name.add("daebak");

        //Consumer -> return value가 없음
        name.forEach(System.out::println);

        Spliterator<String> spliterator = name.spliterator();//쪼갤 수 있는 iterator
        Spliterator<String> half = spliterator.trySplit();
        while (spliterator.tryAdvance(System.out::println));
        while (half.tryAdvance(System.out::println));

        System.out.println("=============");
        name.removeIf(s -> s.startsWith("h"));
        System.out.println(name);

        System.out.println("=============");
        Comparator<String> compareToIgnoreCase = String::compareToIgnoreCase;
        name.sort(compareToIgnoreCase.reversed());//역순으로 string 정렬
        name.sort(compareToIgnoreCase.reversed().thenComparing(compareToIgnoreCase));

    }
}
