import net.spy.memcached.internal.GetFuture;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.ops.StatusCode;
import net.spy.memcached.transcoders.SerializingTranscoder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class ArcusKeyValue extends ArcusInfo{
    public ArcusKeyValue(String arcusAdmin, String serviceCode) {
        super(arcusAdmin, serviceCode);
    }

    public boolean setData(String key, int expireTime, Object value) {
        OperationFuture<Boolean> future = null;
        boolean responseData = false;
        try {
            future = arcusClient.set(key, expireTime, value);
            responseData = future.get();
            return responseData;
        } catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }

    public Object getDataWithTimeOut(String key) {
        GetFuture<Object> getFuture = arcusClient.asyncGet(key);
        try {
            return getFuture.get(1, TimeUnit.NANOSECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Object getData(String key) {
        try {
            return arcusClient.asyncGet(key, new SerializingTranscoder()).get(1000, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("e.getMessage() = " + e.getMessage());
        }
        return null;
    }
}
