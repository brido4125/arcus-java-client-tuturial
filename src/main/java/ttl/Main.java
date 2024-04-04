package ttl;

import java.net.InetAddress;
import java.security.Security;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Main {
  private static final String TEST_DOMAIN = "brido.me";

  public static void main(String[] args) throws InterruptedException {
    System.setProperty("networkaddress.cache.ttl", "10");
    Security.setProperty("networkaddress.cache.ttl", "0");

    for (int i = 0; i < 1000; i++) {
      try {
        InetAddress address = InetAddress.getByName(TEST_DOMAIN);
        System.out.println(getCurrentTime() + " lookup success " + address);
      } catch (Exception ignore) {
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
}
