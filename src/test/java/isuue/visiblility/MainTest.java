package isuue.visiblility;

import org.junit.jupiter.api.Test;

public class MainTest {
  @Test
  void test() throws InterruptedException {
    for (int tc = 0; tc < 100; tc++) {
      Worker worker = new Worker();
      worker.start();
      sleep(100);
      worker.isStop = true;
      System.out.println("tc = " + tc);
      worker.join();
    }
    
  }

  void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
