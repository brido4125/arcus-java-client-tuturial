package isuue.hashmap;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@Disabled
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

  @Test
  @DisplayName("Map에 동일한 Key, 다른 value put -> 가장 최근에 넣은 값으로 대체됨")
  void sameKeyTest() {
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < 100000000; i++) {
      map.put("hello", i);
    }
    System.out.println("map = " + map);
  }

  @Test
  @DisplayName("Map에 동일한 Key, 다른 value put -> 가장 최근에 넣은 값으로 대체됨")
  void sameKeyTest2() {
    Map<String, Integer> map = new TreeMap<>();
    for (int i = 0; i < 1000; i++) {
      map.put("hello", i);
    }
    System.out.println("map = " + map);
  }
}
