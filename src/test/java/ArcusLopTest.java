import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.ops.CollectionOperationStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class ArcusLopTest {
    private ArcusLop arcusClient;
    private final String TEST_VAL = "Brido";
    private final String TEST_KEY = "TEST:KEY";

    private final String TEST_PREFIX = "TEST";


    @BeforeEach
    public void set() {
        arcusClient = new ArcusLop("127.0.0.1:2181", "test");
    }

    @AfterEach
    public void clear() {
        boolean b = arcusClient.deleteAll(TEST_PREFIX);
    }

    @Test
    @DisplayName("LOP 일괄 삽입 - 하나의 key, 여러개 요소")
    void pipedInsertionLop() {

        CollectionAttributes attributesForCreate = new CollectionAttributes();

        List<Object> elements = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            elements.add(TEST_VAL + i);
        }
        Map<Integer, CollectionOperationStatus> rv = arcusClient.insertLopPipedBulk(TEST_KEY, elements, attributesForCreate);
    }

    @Test
    @DisplayName("LOP 일괄 삽입 - 여러개의 key, 하나의 요소")
    void insertionBulkLop() {
        CollectionAttributes attributesForCreate = new CollectionAttributes();

        List<String> keyList = new ArrayList<>();
        for (int i = 0; i <= 500; i++) {
            keyList.add(TEST_PREFIX + i);
        }

        Map<String, CollectionOperationStatus> rv = arcusClient.insertLopBulk(keyList, TEST_VAL, attributesForCreate);

        //then
        Assertions.assertThat(rv.size()).isEqualTo(0);
        for (int i = 0; i <= 500; i++) {
            Assertions.assertThat(arcusClient.getLop(TEST_PREFIX + i, 0).get(0)).isEqualTo(TEST_VAL);
        }

    }
}
