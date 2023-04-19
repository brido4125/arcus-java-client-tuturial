import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.collection.Element;
import net.spy.memcached.collection.ElementFlagFilter;
import net.spy.memcached.collection.ElementValueType;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.ops.CollectionOperationStatus;

import java.util.List;
import java.util.Map;

public class ArcusBop extends ArcusInfo{
    public ArcusBop(String arcusAdmin, String serviceCode) {
        super(arcusAdmin, serviceCode);
    }

    public boolean createBop(String key, ElementValueType valueType) {
        CollectionFuture<Boolean> future = null;
        CollectionAttributes attributes = new CollectionAttributes();
        attributes.setExpireTime(10);
        boolean responseData = false;
        try{
            future = arcusClient.asyncBopCreate(key, valueType, attributes);
            responseData = future.get();
            return responseData;
        }catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertBop(String key, long bKey, Object value, CollectionAttributes attributesForCreate) {
        CollectionFuture<Boolean> future = null;
        boolean responseData = false;
        try {
            future = arcusClient.asyncBopInsert(key, bKey, null ,value, attributesForCreate);
            responseData = future.get();
            return responseData;
        }catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }

    public Map<Long, Element<Object>> getBop(String key, long bKey, ElementFlagFilter elementFlagFilter, boolean withDelete, boolean dropIfEmpty){
        CollectionFuture<Map<Long, Element<Object>>> future = null;
        Map<Long, Element<Object>> responseData = null;
        try {
            future = arcusClient.asyncBopGet(key, bKey, elementFlagFilter, withDelete, dropIfEmpty);
            responseData = future.get();
            return responseData;
        }catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return null;
    }

    public int insertBopPipedBulk(String key, List<Element<Object>> elements, CollectionAttributes attributesForCreate) {
        CollectionFuture<Map<Integer, CollectionOperationStatus>> future = null;
        Map<Integer, CollectionOperationStatus> responseData = null;
        try {
            future = arcusClient.asyncBopPipedInsertBulk(key, elements, attributesForCreate);
            responseData = future.get();
            if (!responseData.isEmpty()) {
                System.out.println("Error occurred while inserting elements.");
                for (Map.Entry<Integer, CollectionOperationStatus> entry : responseData.entrySet()) {
                    System.out.println("Error inserting element at index " + entry.getKey() + ": " + entry.getValue().getResponse());
                }
            } else {
                System.out.println("All elements inserted successfully.");
            }
        } catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
            }
        finally {
            return responseData.size();
        }
    }

}
