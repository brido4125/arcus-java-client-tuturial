package functional.optional;

import functional.defaultInterface.OnlineClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Example {
    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(5, "rest api dev", false));

        Optional<OnlineClass> spring = springClasses.stream()
            .filter(oc -> oc.getTitle().startsWith("spring"))
            .findFirst();

        boolean present = spring.isPresent();
        System.out.println("present = " + present);

        //ifPresent -> Cousumer 인자로
        spring.ifPresent(oc -> {
            System.out.println("oc = " + oc);
        });

        //orElse -> 있으면 가져오고 없으면 새로운 인스턴스를 리턴
        //orElse는 이미 만들어진 상수 인스턴스를 반환시킬 때 적절함
        OnlineClass springBoot2 = spring.orElse(new OnlineClass(2, "spring boot2", true));

        //orElseGet -> 있으면 가져오고 없으면 Supplier 호출 (인자가 없고 리턴 타입 존재)
        //orElseGet은 동적으로 새로운 인스턴스를 생성해야하는 경우에 적절함
        spring.orElseGet(() -> new OnlineClass(2, "spring boot2", true));

        spring.orElseThrow(IllegalArgumentException::new);

        //stream의 중간 연산일 경우 Optional 리턴
        Optional<Integer> ids = spring.map(OnlineClass::getId);
    }
}
