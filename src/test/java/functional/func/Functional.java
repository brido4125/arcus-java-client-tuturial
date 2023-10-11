package functional.func;

import java.util.function.Function;

public class Functional implements Function<Integer, Integer> {
    @Override
    public Integer apply(Integer integer) {
        return integer + 100;
    }
}
