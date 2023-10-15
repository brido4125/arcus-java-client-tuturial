package functional.optional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

public class Main {
    public static void main(String[] args) {

        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "rest api development", false));

        OnlineClass springBoot = new OnlineClass(1, "springboot", true);
        Optional<Progress> progress = springBoot.getProgress();

        //primitive type은 boxing unboxing이 성능 저하
        OptionalDouble od = OptionalDouble.of(1.2);

        //Collection, Map, Stream, Array, Optional은 Optional로 감싸지 않을것
        //이미 컨테이너 성격의 자료구조이기 때문
    }
}
