package isuue.version;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
@Disabled
public class VersionTest {
    @Test
    void versionStringTest() {
        MyObjectPool pool = new MyObjectPool(5);
        pool.getPool().forEach(myObject -> System.out.println(myObject.getVERSION().hashCode()));
        MyObject obj = pool.getPool().get(0);
        pool.getPool().forEach(myObject ->
                Assertions.assertThat(obj.getObj().hashCode()).isEqualTo(obj.getObj().hashCode()));
    }


}

