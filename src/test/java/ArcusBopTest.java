import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.collection.Element;
import net.spy.memcached.collection.ElementFlagFilter;
import net.spy.memcached.collection.ElementValueType;
import net.spy.memcached.ops.CollectionOperationStatus;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class ArcusBopTest {

    private ArcusBop arcusClient;
    private final String TEST_VAL = "Brido";
    private final String TEST_KEY = "TEST:KEY";

    private final String TEST_PREFIX = "TEST";



    @BeforeEach
    public void set() {
        arcusClient = new ArcusBop("127.0.0.1:2191", "brido");
    }

    @AfterEach
    public void clear(){
        boolean b = arcusClient.deleteAll(TEST_PREFIX);
    }

    @Test
    @DisplayName("BOP Create API 성공 테스트")
    void createBOP() {
        boolean ret = arcusClient.createBop(TEST_KEY, ElementValueType.STRING);
        assertThat(ret).isTrue();
    }

    @Test
    @DisplayName("BOP가 있을 경우 Insert API 성공 테스트")
    void insertWithBOPSuccessTest() {
        //given
        arcusClient.createBop(TEST_KEY, ElementValueType.INTEGER);
        //when
        arcusClient.insertBop(TEST_KEY, 1L, 30, null);
        //then
        Map<Long, Element<Object>> data = arcusClient.getBop(TEST_KEY, 1L, ElementFlagFilter.DO_NOT_FILTER, false, false);
        assertThat(data.get(1L).getValue()).isEqualTo(30);
    }


    @Test
    @DisplayName("BOP가 없을 경우 Insert API 성공 테스트")
    void insertWithoutBOP() {
        //given
        CollectionAttributes collectionAttributes = new CollectionAttributes();
        collectionAttributes.setExpireTime(10);
        //when
        boolean ret = arcusClient.insertBop(TEST_KEY, 1L, TEST_VAL, collectionAttributes);
        //then
        Map<Long, Element<Object>> data = arcusClient.getBop(TEST_KEY, 1L, ElementFlagFilter.DO_NOT_FILTER, false, false);
        assertThat(data.get(1L).getValue()).isEqualTo(TEST_VAL);
        assertThat(ret).isTrue();
    }

    @Test
    @DisplayName("BOP Piped Insert API 성공 테스트")
    void insertPipedBOPSuccessTest() {
        //given
        CollectionAttributes collectionAttributes = new CollectionAttributes();
        collectionAttributes.setExpireTime(10);

        List<Element<Object>> elements = new ArrayList<>();
        elements.add(new Element<>(1L, "VALUE1", (byte[]) null));
        elements.add(new Element<>(2L, "VALUE2", (byte[]) null));
        elements.add(new Element<>(3L, "VALUE3", (byte[]) null));
        //when
        int ret = arcusClient.insertBopPipedBulk(TEST_KEY, elements, collectionAttributes);
        //then
        assertThat(ret).isZero();
        Map<Long, Element<Object>> data1 = arcusClient.getBop(TEST_KEY, 1L, ElementFlagFilter.DO_NOT_FILTER, false, false);
        Map<Long, Element<Object>> data2 = arcusClient.getBop(TEST_KEY, 2L, ElementFlagFilter.DO_NOT_FILTER, false, false);
        Map<Long, Element<Object>> data3 = arcusClient.getBop(TEST_KEY, 3L, ElementFlagFilter.DO_NOT_FILTER, false, false);
        assertThat(data1.get(1L).getValue()).isEqualTo("VALUE1");
        assertThat(data2.get(2L).getValue()).isEqualTo("VALUE2");
        assertThat(data3.get(3L).getValue()).isEqualTo("VALUE3");
    }

    @Test
    @DisplayName("BOP Piped Insert API 실패 테스트")
    void insertPipedBOPFailTest() {
        //given
        CollectionAttributes collectionAttributes = new CollectionAttributes();
        collectionAttributes.setExpireTime(10);

        List<Element<Object>> elements = new ArrayList<>();
        elements.add(new Element<>(1L, "VALUE1", (byte[]) null));
        elements.add(new Element<>(1L, "VALUE2", (byte[]) null));
        //when
        int ret = arcusClient.insertBopPipedBulk(TEST_KEY, elements, collectionAttributes);
        //then
        assertThat(ret).isEqualTo(1);
    }

    @Test
    @DisplayName("BOP Sort Merge API 성공 테스트")
    void sortMergeBOPSuccessTest() {

        CollectionAttributes collectionAttributes = new CollectionAttributes();
        collectionAttributes.setExpireTime(10);
        //given
        arcusClient.insertBop(TEST_PREFIX + ":A", 0L, "VALUE1", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":A", 7L, "VALUE2", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":A", 14L, "VALUE3", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":A", 21L, "VALUE4", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":A", 28L, "VALUE5", collectionAttributes);

        arcusClient.insertBop(TEST_PREFIX + ":B", 1L, "VALUE1", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":B", 5L, "VALUE2", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":B", 10L, "VALUE3", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":B", 15L, "VALUE4", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":B", 20L, "VALUE5", collectionAttributes);

        arcusClient.insertBop(TEST_PREFIX + ":C", 3L, "VALUE1", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":C", 6L, "VALUE2", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":C", 9L, "VALUE3", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":C", 12L, "VALUE4", collectionAttributes);
        arcusClient.insertBop(TEST_PREFIX + ":C", 16L, "VALUE5", collectionAttributes);

        List<String> keyList = new ArrayList<String>() {
            {
                add(TEST_PREFIX + ":A");
                add(TEST_PREFIX + ":B");
                add(TEST_PREFIX + ":C");
            }
        };

        long bKeyFrom = 30L;
        long bKeyTo = 10L;
        int count = 20; // 정렬되는 elem의 수보다는 커야함, from - to 값으로 설정

        //when
        int ret = arcusClient.getSortMergeBOP(keyList, bKeyFrom, bKeyTo, count);
        //then
        assertThat(ret).isEqualTo(8);
    }
}
