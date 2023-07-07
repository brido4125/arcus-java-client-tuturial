import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

public class TimeoutTest {

  private ArcusKeyValue arcusClient;
  private final String FOO_VAL = "Brido";
  private final String REPLACE_VAL = "Jam2in";
  private final String TEST_KEY = "KEY_VALUE";
  private final String REPLACE_KEY = "REP_KEY";

  @BeforeEach
  public void set() {
    arcusClient = new ArcusKeyValue("127.0.0.1:2191", "brido");
  }

  @Test
  void successTest() {
    arcusClient.setData(TEST_KEY, 3, FOO_VAL);
    for (int i = 0; i < 1000; i++) {
      arcusClient.getDataWithTimeOut(TEST_KEY);
    }
  }


  @Test
  void timeoutTest() throws InterruptedException {
    arcusClient.setData(TEST_KEY, 3, FOO_VAL);
    for (int i = 0; i < 15; i++) {
      Thread.sleep(100);
      arcusClient.getDataWithTimeOut(TEST_KEY);
    }
  }
}
