package isuue.recache;

import org.junit.jupiter.api.Test;

public class MainTest {
  @Test
  void mainTest() {
    int i = 0;
    final int RETRY_COUNT = 9;
    while (i++ <= RETRY_COUNT) {
      System.out.println("MainTest.mainTest");
    }
  }
}
