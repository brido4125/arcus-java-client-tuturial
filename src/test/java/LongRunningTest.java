import net.spy.memcached.ArcusClient;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.internal.GetFuture;
import net.spy.memcached.ops.StoreType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LongRunningTest {

  private final String SERVICE_CODE = "brido";
  private final String ARCUS_ADMIN = "127.0.0.1:2191";

  private final String KEY_VALUE_KEY = "KEY_VALUE";
  private final String LIST_KEY = "LIST_KEY";
  private final String SET_KEY = "SET_KEY";
  private final String BTREE_KEY = "BTREE_KEY";

  private final String DATA = "DATA";

  private ArcusClient arcusClient;

  @BeforeEach
  void setData() {
    this.arcusClient = ArcusClient.createArcusClient(ARCUS_ADMIN, SERVICE_CODE, new ConnectionFactoryBuilder());
    for (int i = 0; i < 1000; i++) {
      arcusClient.asyncLopInsert(LIST_KEY + i, i, (Object) DATA,
              new CollectionAttributes(10000, 2000L, null));
      arcusClient.set(KEY_VALUE_KEY + i, 10000,DATA);
      arcusClient.asyncBopInsert(BTREE_KEY + i, i, null, (Object) DATA,
              new CollectionAttributes(10000, 2000L, null));
      arcusClient.asyncSopInsert(SET_KEY + i,(Object) DATA,
              new CollectionAttributes(10000, 2000L, null));
    }

  }
  @Test
  void mainTest() throws ExecutionException, InterruptedException, TimeoutException {
    for (int i = 0; i < 1000; i++) {
      GetFuture<Object> getFuture = arcusClient.asyncGet(KEY_VALUE_KEY + i);
      try {
        getFuture.get(1L, TimeUnit.MILLISECONDS);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
