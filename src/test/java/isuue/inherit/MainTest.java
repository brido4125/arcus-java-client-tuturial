package isuue.inherit;

import org.junit.jupiter.api.Test;

public class MainTest {

  @Test
  void mainTest() {
    Parent child = new Child();
    child.doSomething();
  }
}
