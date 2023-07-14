package isuue.rawtype;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RawTypeTest {
  @Test
  void rawTypeTest() {
    List list = new ArrayList();
    list.add(1);
    list.add("2");

    assertThat(list.get(0)).isInstanceOf(Integer.class);
    assertThat(list.get(1)).isInstanceOf(String.class);
  }
}
