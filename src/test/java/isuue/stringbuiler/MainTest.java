package isuue.stringbuiler;

import org.junit.jupiter.api.Test;

public class MainTest {
  @Test
  void stringBuilder() {
    StringBuilder test = new StringBuilder();
    test.toString();
    System.out.println("test = " + test);

    String empty = "";
    System.out.println("empty = " + empty);

    String text = "hello";

    test.append(text);

    System.out.println("test = " + test);
  }
}
