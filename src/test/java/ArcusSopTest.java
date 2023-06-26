import net.spy.memcached.ops.CollectionOperationStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArcusSopTest {

  private ArcusSop arcusClient;
  private final String TEST_VAL = "Brido";
  private final String TEST_KEY = "TEST:KEY";

  private final String TEST_PREFIX = "TEST";

  @BeforeEach
  public void set() {
    arcusClient = new ArcusSop("127.0.0.1:2191", "brido");
    arcusClient.createSop(TEST_KEY);
  }

  @AfterEach
  public void clear() {
    boolean b = arcusClient.deleteAll(TEST_PREFIX);
  }

  @Test
  void existsTest() {
    List<String> elements = new ArrayList<>();

    for (int i = 0; i < 400; i++) {
      elements.add(TEST_VAL + i);
    }


    Map<Integer, CollectionOperationStatus> insertRv = arcusClient.insertSopPiped(TEST_KEY, elements);
    Assertions.assertThat(insertRv.size()).isEqualTo(0);

    Map<Object, Boolean> objectBooleanMap = arcusClient.existSopPiped(TEST_KEY, elements);
    Assertions.assertThat(objectBooleanMap.size()).isEqualTo(400);
    for (Map.Entry<Object, Boolean> objectBooleanEntry : objectBooleanMap.entrySet()) {
      Assertions.assertThat(objectBooleanEntry.getValue()).isTrue();
    }

  }

  @Test
  void noneExistTest() {
    List<String> elements = new ArrayList<>();

    for (int i = 0; i < 400; i++) {
      elements.add(TEST_VAL + i);
    }


    Map<Integer, CollectionOperationStatus> insertRv = arcusClient.insertSopPiped(TEST_KEY, elements);
    Assertions.assertThat(insertRv.size()).isEqualTo(0);

    elements.clear();

    for (int i = 0; i < 400; i++) {
      elements.add(TEST_VAL + (i + 400));
    }

    Map<Object, Boolean> objectBooleanMap = arcusClient.existSopPiped(TEST_KEY, elements);
    Assertions.assertThat(objectBooleanMap.size()).isEqualTo(400);
    for (Map.Entry<Object, Boolean> objectBooleanEntry : objectBooleanMap.entrySet()) {
      Assertions.assertThat(objectBooleanEntry.getValue()).isFalse();
    }
  }
}
