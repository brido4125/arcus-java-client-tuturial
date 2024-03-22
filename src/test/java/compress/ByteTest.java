package compress;

import net.spy.memcached.util.BTreeUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.TreeMap;

public class ByteTest {
  @Test
  void test() {
    String value = "value.??%";
    byte[] bytes = value.getBytes();
    System.out.println("bytes = " + Arrays.toString(bytes));
    byte[] over = {(byte) 100L};
    System.out.println("over = " + Arrays.toString(over));
  }

  @Test
  void treeMap() {
    TreeMap<byte[], String> treeMap = new TreeMap<>(BTreeUtil::compareByteArraysInLexOrder);

    treeMap.put(new byte[]{(byte) 0}, "0");
    treeMap.put(new byte[]{(byte) 1}, "1");
    treeMap.put(new byte[]{(byte) 2}, "2");
    treeMap.put(new byte[]{(byte) 3}, "3");

    Assertions.assertThat(treeMap.firstKey()).isEqualTo(new byte[]{(byte) 0});
    Assertions.assertThat(treeMap.lastKey()).isEqualTo(new byte[]{(byte) 3});
  }
}
