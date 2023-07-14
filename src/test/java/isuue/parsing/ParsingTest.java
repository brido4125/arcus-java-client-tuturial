package isuue.parsing;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class ParsingTest {

  @Test
  void test() {
    String target = "1, 2, 3, 4, 5, 6, 7, 8, 9, 10";
    Arrays.stream(target.split(", "))
        .forEach(System.out::println);
  }
}
