package ttl;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.ArcusClientPool;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.DefaultConnectionFactory;

import java.net.InetAddress;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.PropertyPermission;
import java.util.concurrent.CompletableFuture;

public class Main {
  private static final String TEST_DOMAIN = "brido.me";


  public static void main(String[] args) throws InterruptedException {

//    Security.setProperty("networkaddress.cache.ttl", "1");
    System.setProperty("sun.net.inetaddr.ttl", "1");
//    InetAddressCachePolicy.setIfNotSet(31);
//    SecurityManager sm = new SecurityManager();
//    System.setSecurityManager(sm);



    for (int i = 0; i < 1000; i++) {
      try {
//        InetAddress address = InetAddress.getByName(TEST_DOMAIN);
//        System.out.println(getCurrentTime() + " lookup success " + address);
      } catch (Exception ignore) {
        System.out.println("ignore = " + ignore);
        System.out.println(getCurrentTime() + " lookup failed");
      } finally {
        Thread.sleep(1000);
      }
    }
  }

  private static String getCurrentTime() {
    DateFormat dateFormat = new SimpleDateFormat("mm:ss");
    return dateFormat.format(Calendar.getInstance().getTime());
  }

  private void test() {
    ArcusClientPool clientPool = ArcusClient.createArcusClientPool("test", new ConnectionFactoryBuilder(), 3);
    ArrayList<Object> objects = new ArrayList<>();
    for (int i = 0; i < objects.size() % 500; i++) {
      clientPool.asyncLopPipedInsertBulk("test", -1, objects.subList(i * 500, (i * 500) + 500), null);
    }
  }

}
