package isuue.json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonNodeTest {
  @Test
  void jsonNodeTest() {
    String jsonStr = "{\"name\":\"ksr930\",\"age\":29,\"address\":\"seoul\"}";

    try {
      JsonNode jsonNode = new ObjectMapper().readTree(jsonStr);
      JsonNode age = jsonNode.get("age");
      System.out.println("age.isTextual() = " + age.isTextual());
      System.out.println("age.isNumber() = " + age.isNumber());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void objectTest() {
    Data data = new Data(29, Type.A, "ksr930");
    try {
      String jsonStr = new ObjectMapper().writeValueAsString(data);
      JsonNode jsonNode = new ObjectMapper().readTree(jsonStr);

      assertThat(jsonNode.get("age").isNumber()).isTrue();
      assertThat(jsonNode.get("age").isInt()).isTrue();
      assertThat(jsonNode.get("type").isTextual()).isTrue();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
