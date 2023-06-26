import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.collection.ElementValueType;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.ops.CollectionOperationStatus;


import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class ArcusSop extends ArcusInfo {
  public ArcusSop(String arcusAdmin, String serviceCode) {
    super(arcusAdmin, serviceCode);
  }

  public Boolean createSop(String key) {
    CollectionFuture<Boolean> future = null;
    Boolean result = null;
    try {
      CollectionFuture<Boolean> rv = arcusClient.asyncSopCreate(key, ElementValueType.STRING, new CollectionAttributes());
      result = rv.get(1000L, TimeUnit.MILLISECONDS);
    } catch (Exception e) {
      throw new IllegalStateException();
    }
    return result;
  }

  public <T> Map<Integer, CollectionOperationStatus> insertSopPiped(String key, List<String> values) {
    Map<Integer, CollectionOperationStatus> result = null;
    try {
      CollectionFuture<Map<Integer, CollectionOperationStatus>> rv = arcusClient.asyncSopPipedInsertBulk(key, convertObject(values), new CollectionAttributes());
      result = rv.get(1000L, TimeUnit.MILLISECONDS);
    } catch (Exception e) {
      throw new IllegalStateException();
    }
    return result;
  }

  private static List<Object> convertObject(List<String> values) {
    return values.stream()
            .map(value -> (Object) value)
            .collect(Collectors.toList());
  }

  public Map<Object, Boolean> existSopPiped(String key, List<String> values) {
    CollectionFuture<Boolean> future = null;
    Map<Object, Boolean> result = null;
    try {
      CollectionFuture<Map<Object, Boolean>> rv = arcusClient.asyncSopPipedExistBulk(key, convertObject(values));
      result = rv.get(1000L, TimeUnit.MILLISECONDS);
    } catch (Exception e) {
      throw new IllegalStateException();
    }
    return result;
  }
}
