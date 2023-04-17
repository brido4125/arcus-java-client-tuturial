package brido.arcus.java.client;

import org.junit.jupiter.api.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloArcusTest {

    private HelloArcus helloArcus;
    private final String FOO_VAL = "Brido";
    private final String TEST_KEY = "test";

    @BeforeEach
    public void set() {
        helloArcus = new HelloArcus("127.0.0.1:2191", "brido");
    }

    @AfterEach
    public void after() {
        helloArcus.deleteData(TEST_KEY);
    }

    @Test
    public void store() {
        boolean b = helloArcus.setHello(TEST_KEY,"Brido");
        assertThat(b).isTrue();
    }

    @Test
    void listenHello() {
        //given
        helloArcus.setHello(TEST_KEY,"Brido");
        //when
        String s = helloArcus.getData(TEST_KEY);
        //then
        assertThat(s).isEqualTo(FOO_VAL);
    }

    @Test
    void valueTimeOutTest() throws InterruptedException {
        helloArcus.setHello("test","Brido");
        Thread.sleep(6000);
        String s = helloArcus.getData(TEST_KEY);
        assertThat(s).isEqualTo(null);
    }

    @Test
    @DisplayName("Collection Get OP Test")
    void collectionGetTest() {
        helloArcus.createBop(TEST_KEY);
        helloArcus.insertBop(TEST_KEY, 1L, "TEST-VALUE");
        String s = helloArcus.getBop(TEST_KEY, 1L);
        assertThat(s).isEqualTo("TEST-VALUE");
    }

    @Test
    @DisplayName("중복키 저장 - 저장 실패")
    void dupKeySave() {
        helloArcus.setHello(TEST_KEY, "Brido");
        boolean b = helloArcus.addData("test", "Brido");
        assertThat(b).isFalse();
    }

    @Test
    @DisplayName("BTree Collection 생성")
    void createBop() {
        helloArcus.createBop(TEST_KEY);
        boolean b = helloArcus.insertBop(TEST_KEY, 1L, "TEST-VALUE");
        assertThat(b).isTrue();
    }
}