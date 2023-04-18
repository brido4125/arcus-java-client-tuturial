import custom.ArcusKeyValue;
import net.spy.memcached.ops.StatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArcusKeyValueTest {
    private ArcusKeyValue arcusClient;
    private final String FOO_VAL = "Brido";
    private final String REPLACE_VAL = "Jam2in";
    private final String TEST_KEY = "KEY_VALUE";
    private final String REPLACE_KEY = "REP_KEY";

    @BeforeEach
    public void set() {
        arcusClient = new ArcusKeyValue("127.0.0.1:2191", "brido");
    }

    @AfterEach
    public void after() {
        arcusClient.deleteData(TEST_KEY);
    }

    @Test
    @DisplayName("Key-Value SET API 저장 성공 테스트")
    public void dataSetTest() {
        StatusCode statusCode = arcusClient.setData(TEST_KEY, 3, FOO_VAL);
        assertThat(statusCode).isEqualTo(StatusCode.SUCCESS);
    }

    @Test
    @DisplayName("중복 데이터 Key-Value SET API 저장 성공 테스트")
    public void dupDataSetTest() {
        //given
        arcusClient.setData(TEST_KEY, 3, FOO_VAL);
        //when
        StatusCode statusCode2 = arcusClient.setData(TEST_KEY, 3, FOO_VAL);
        //then
        assertThat(statusCode2).isEqualTo(StatusCode.SUCCESS);
    }

    @Test
    @DisplayName("Key-Value ADD API 저장 성공 테스트")
    void dataAddTest() {
        StatusCode statusCode = arcusClient.addData("test", 3, "Brido");
        assertThat(statusCode).isEqualTo(StatusCode.SUCCESS);
    }

    @Test
    @DisplayName("중복 데이터 Key-Value ADD API 저장 실패 테스트")
    void dupDataAddTest() {
        //given
        arcusClient.addData("test", 3, "Brido");
        //when
        StatusCode statusCode = arcusClient.addData("test", 3, "Brido");
        //then
        assertThat(statusCode).isEqualTo(StatusCode.ERR_NOT_STORED);
    }


    @Test
    @DisplayName("Key-Value Replace API 저장 성공 테스트")
    void dataReplaceTest() {
        //given
        arcusClient.setData(TEST_KEY, 3, FOO_VAL);
        //when
        StatusCode statusCode = arcusClient.replaceData(TEST_KEY, 3, REPLACE_VAL);
        //then
        assertThat(statusCode).isEqualTo(StatusCode.SUCCESS);
    }

    @Test
    @DisplayName("Key-Value Replace API 저장 실패 테스트")
    void dataReplaceFailTest() {
        //given
        arcusClient.setData(TEST_KEY, 3, FOO_VAL);
        //when
        StatusCode statusCode = arcusClient.replaceData(REPLACE_KEY, 3, REPLACE_VAL);
        //then
        assertThat(statusCode).isEqualTo(StatusCode.ERR_NOT_STORED);
    }

    @Test
    void valueTimeOutTest() throws InterruptedException {
        //given
        arcusClient.setData(TEST_KEY,1,FOO_VAL);
        //when
        Thread.sleep(1000);
        String s = arcusClient.getData(TEST_KEY);
        //then
        assertThat(s).isEqualTo(null);
    }

    @Test
    void listenHello() {
        //given
        arcusClient.setData(TEST_KEY,6,"Brido");
        //when
        String s = arcusClient.getData(TEST_KEY);
        //then
        assertThat(s).isEqualTo(FOO_VAL);
    }
}