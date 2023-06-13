import net.spy.memcached.internal.GetFuture;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.ops.StatusCode;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

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
}
