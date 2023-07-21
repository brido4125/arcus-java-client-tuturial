package isuue.sync;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {

  private boolean isStop = false;
  private AtomicBoolean isStopAtomic = new AtomicBoolean();

  private AtomicInteger count = new AtomicInteger();
  @Test
  void failTest() {
    Thread threadA = new Thread(() -> {
      isStop = true;
      System.out.println("isStop = " + isStop);
    });
    threadA.start();
    isStop = true;
    System.out.println("isStop = " + isStop);
  }

  void doSomething() {
    try {
      Thread.sleep(50);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
