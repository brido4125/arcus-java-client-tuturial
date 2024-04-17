package functional.defaultInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamEx {

    private static List<OnlineClass> mergerList(List<OnlineClass>... lists) {
        return Stream.of(lists)
            .flatMap(List::stream)
            .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> mergedEvents = new ArrayList<>();
        mergedEvents.add(springClasses);
        mergedEvents.add(javaClasses);

        System.out.println("spring 으로 시작하는 수업");
        // TODO
        List<OnlineClass> spring = springClasses.stream()
            .filter(s -> s.getTitle().startsWith("spring"))
            .collect(Collectors.toList());
        System.out.println("spring = " + spring);

        System.out.println("close 되지 않은 수업");
        List<OnlineClass> notClosed = mergerList(springClasses, javaClasses)
            .stream()
//            .filter(Predicate.not(OnlineClass::isClosed))
            .collect(Collectors.toList());
        System.out.println("notClosed = " + notClosed);


        System.out.println("수업 이름만 모아서 스트림 만들기");
        // TODO


        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        // TODO Flat Map -> 한 List 내에 들어있는 List들에 대한 연산 진행 List::stream으로 OnlienClass에 대한 Stream 생성
        mergedEvents.stream().flatMap(List::stream).forEach(oc -> System.out.println(oc.getId()));

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        // TODO
        Stream.iterate(10, i -> i + 1)
            .skip(10)
            .limit(10)
            .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        // TODO
        boolean test = javaClasses.stream()
            .anyMatch(oc -> oc.getTitle().contains("test"));
        System.out.println("test = " + test);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 제목만 모아서 List로 만들기");
        // TODO
        List<String> titles = springClasses.stream()
            .map(OnlineClass::getTitle)
            .filter(title -> title.contains("spring"))
            .collect(Collectors.toList());

    }
}
