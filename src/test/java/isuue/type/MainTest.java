package isuue.type;

import org.junit.jupiter.api.Test;

public class MainTest {
  @Test
  void mainTest() {
    Data data = new Data();
    data.value = true;
    System.out.println(data.value instanceof Integer);
  }
}
