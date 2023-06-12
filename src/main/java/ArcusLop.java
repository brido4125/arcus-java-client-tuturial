import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.ops.CollectionOperationStatus;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ArcusLop extends ArcusInfo{
    public ArcusLop(String arcusAdmin, String serviceCode) {
        super(arcusAdmin, serviceCode);
    }


    /*
    * 하나의 Key를 가지는 하나의 List에 여러개의 요소를 Bulk성으로 Insertion
    * */
    public Map<Integer, CollectionOperationStatus> insertLopPipedBulk(String key, List<Object> elements, CollectionAttributes attributesForCreate) {
        CollectionFuture<Map<Integer, CollectionOperationStatus>> future = null;
        future = arcusClient.asyncLopPipedInsertBulk(key, 0, elements, attributesForCreate);
        Map<Integer, CollectionOperationStatus> rv = null;
        try {
            rv = future.get(1000L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        CollectionOperationStatus operationStatus = future.getOperationStatus();
        System.out.println("operationStatus = " + operationStatus);
        return rv;
    }


    /*
    * 여러개의 Key을 인수로 받아, 여러개의 List에 한개의 요소를 Insertion
    * */
    public Map<String, CollectionOperationStatus> insertLopBulk(List<String> keyList, Object element, CollectionAttributes attributesForCreate) {
        Future<Map<String, CollectionOperationStatus>> future = null;
        future = arcusClient.asyncLopInsertBulk(keyList, 0, element, attributesForCreate);
        Map<String, CollectionOperationStatus> rv = null;
        try {
            rv = future.get(1000L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return rv;
    }

    public List<Object> getLop(String key, int index) {
        Future<List<Object>> future = null;
        future = arcusClient.asyncLopGet(key, index, false, false);
        List<Object> rv = null;
        try {
            rv = future.get(1000L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            throw new IllegalStateException();
        }
        return rv;
    }
}
