package isuue.hashmap;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class HashMapTest {


  private HashMap<String, Object> map = new HashMap<>();
  @Test
  @DisplayName("Map의 Key에 의해 map에 put되는 순서는 보장 되지 않음")
  void integerKeyTest() {
    int i = 0;
    int h;

    for(i = 0 ; i < 100 ; i++) {
      map.put(String.valueOf(i), "value" + i);
      int hash = (h = Integer.toString(i).hashCode()) ^ (h >>> 16);
      System.out.println("hash = " + hash);
    }
    System.out.println("map = " + map);
  }
}
