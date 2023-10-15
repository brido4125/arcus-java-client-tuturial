package functional.defaultInterface;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class StreamMain {
    public static void main(String[] args) {
        List<String> name = new LinkedList<>();
        name.add("hong");
        name.add("song");
        name.add("daebak");

        //Stream은 데이터를 담고 있는 저장소가 아니다.
        List<String> result = name.stream().map(String::toUpperCase).collect(Collectors.toList());
        //stream이 제공하는 api는 크게 두가지, 중계 or 종료(terminal)
        //중계 operation(api) ex) map 들은 기본적으로 lazy하게 동작한다. ->
        //중계 operation은 stream을 반환한다.
        //종료 operation은 stream을 반환하지 않는다.
        //lazy하게 동작한다? -> 아래 map의 코드 조각은 출력은 곧바로 실행시키지 않는다.
        // 중계 op들은 종료 op가 호출되기 전까지 해당 lambda를 실행시키지 않는다!
        // 아래 예제의 map은 종료 operation이 호출되지 않아서 내부 lambda가 동작하지 않음
        name.stream().map(s -> {
            System.out.println(s);
            return s.toUpperCase();
        });
        System.out.println("==============");
        name.forEach(System.out::println);
    }
}
