import custom.ArcusBop;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArcusBopTest {

    private ArcusBop arcusClient;
    private final String FOO_VAL = "Brido";
    private final String TEST_KEY = "test";

    @BeforeEach
    public void set() {
        arcusClient = new ArcusBop("127.0.0.1:2191", "brido");
    }
    @Test
    @DisplayName("BTree Collection 생성")
    void createBop() {
        arcusClient.createBop(TEST_KEY);
        boolean b = arcusClient.insertBop(TEST_KEY, 1L, "TEST-VALUE");
        assertThat(b).isTrue();
    }
}
