package isuue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.concurrent.atomic.AtomicReference;


@Disabled
public class AtomicReferenceTest {

    @Test
    void atomicRefTest() {
        AtomicReference<Integer> atomic = new AtomicReference<>();
        AtomicReference<String> atomicString = new AtomicReference<>("test");

        Assertions.assertThat(atomic.get()).isNull();
        Assertions.assertThat(atomicString.get()).isEqualTo("test");

    }

    @Test
    void casTest() {
        int expected = 20;
        int actual = 10;
        AtomicReference<Integer> atomic = new AtomicReference<>(actual);
        atomic.compareAndSet(actual, expected);
        Assertions.assertThat(atomic.get()).isEqualTo(expected);
    }
}
