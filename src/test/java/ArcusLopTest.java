import custom.ArcusKeyValue;
import custom.ArcusLop;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ArcusLopTest {
    private ArcusLop arcusClient;
    private final String FOO_VAL = "Brido";
    private final String TEST_KEY = "test";

    @BeforeEach
    public void set() {
        arcusClient = new ArcusLop("127.0.0.1:2191", "brido");
    }

    @Test
    @DisplayName("Collection Get OP Test")
    void collectionGetTest() {
        boolean b = arcusClient.insertLop(TEST_KEY, "TEST VALUE");
        assertThat(b).isTrue();
        String lop = arcusClient.getLop(TEST_KEY, 0);
        assertThat(lop).isEqualTo("TEST VALUE");
    }
}
