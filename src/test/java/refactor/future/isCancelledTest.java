package refactor.future;

import net.spy.memcached.ops.Operation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Disabled
public class isCancelledTest {


    @Test
    void testLogicAllTrue() {
        List<Boolean> list = Arrays.asList(true, true, true, true);
        Assertions.assertThat(isCancelled1(list)).isEqualTo(isCancelled2(list));
    }

    @Test
    void testLogicAllFalse() {
        List<Boolean> list = Arrays.asList(false, false, false, false);
        Assertions.assertThat(isCancelled1(list)).isEqualTo(isCancelled2(list));
    }

    @Test
    void testLogicRandom() {
        List<Boolean> list = Arrays.asList(false, true, false, true);
        Assertions.assertThat(isCancelled1(list)).isEqualTo(isCancelled2(list));
    }

    @Test
    void testLogic() {
        Random random = new Random();
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int target = random.nextInt(100);
            list.add(target % 2 == 0);
        }
        System.out.println("주어진 boolean list = " + list);
        System.out.println("isCancelled1()의 결과 : " + isCancelled1(list));
        System.out.println("isCancelled2()의 결과 : " + isCancelled2(list));

        Assertions.assertThat(isCancelled1(list)).isEqualTo(isCancelled2(list));
    }

    @Test
    void timeTestLogic1() {
        Random random = new Random();
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            int target = random.nextInt(100);
            list.add(target % 2 == 0);
        }
        long start = System.currentTimeMillis();
        isCancelled1(list);
        long end = System.currentTimeMillis();
        System.out.println("isCancelled2()의 실행 시간 : " + (end - start));
    }

    @Test
    void timeTestLogic2() {
        Random random = new Random();
        List<Boolean> list = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            int target = random.nextInt(100);
            list.add(target % 2 == 0);
        }
        long start = System.currentTimeMillis();
        isCancelled2(list);
        long end = System.currentTimeMillis();
        System.out.println("isCancelled1()의 실행 시간 : " + (end - start));
    }

    @Test
    void testLogic2() {
        List<Boolean> list = Arrays.asList(false, true, false, false);
        isCancelled2(list);
    }


    /*
    * op가 false이면 계속 Loop 돌면서 true가 있는지 확인한다.
    * 만약 true가 있으면 true를 바로 리턴한다.
    * */
    public boolean isCancelled1(List<Boolean> ops) {
        for (Boolean op : ops) {
            if (op) {
                return true;
            }
        }
        return false;
    }


    /*
    * ops 중 하나라도 true가 있으면 true를 리턴한다.
    * true를 찾아도 계속 Loop를 돌아야 한다.
    * */
    public boolean isCancelled2(List<Boolean> ops) {
        boolean rv = false;
        for (Boolean op : ops) {
            rv |= op;
        }
        return rv;
    }
}
