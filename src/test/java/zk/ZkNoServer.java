package zk;

import org.apache.zookeeper.ZooKeeper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.security.Security;

public class ZkNoServer {
  @Test
  void noCacheNode() throws IOException {
    String zPath = "/arcus/client_list/test";
    ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 15000, e -> {
      System.out.println("e = " + e);
    });
  }

  @Test
  void dns() {
//    Security.setProperty("networkaddress.cache.ttl", "a");
//    String property = Security.getProperty("networkaddress.cache.ttl");
//    int i = Integer.parseInt(property);
//    System.out.println("i = " + i);
    boolean hi = isNonNumeric("123");
    System.out.println("hi = " + hi);
    int i = Integer.decode("123");
    System.out.println("i = " + i);
  }

  private static boolean isNonNumeric(String target) {
    return !target.matches("[+-]?\\d*(\\.\\d+)?");
  }

  @Test
  void tcpSelector() throws IOException {
    Selector selector = Selector.open();
    ServerSocketChannel socketChannel = ServerSocketChannel.open();
    socketChannel.configureBlocking(false);

    socketChannel.bind(new InetSocketAddress("localhost", 11211));
    socketChannel.register(selector, SelectionKey.OP_ACCEPT);
  }
}
