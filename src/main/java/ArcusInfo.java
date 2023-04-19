import net.spy.memcached.ArcusClient;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.internal.OperationFuture;

import java.util.concurrent.TimeUnit;

public class ArcusInfo {
    protected String arcusAdmin;
    protected String serviceCode;
    protected ArcusClient arcusClient;

    public ArcusInfo(String arcusAdmin, String serviceCode) {
        this.arcusAdmin = arcusAdmin;
        this.serviceCode = serviceCode;
        System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.Log4JLogger");
        this.arcusClient = ArcusClient.createArcusClient(arcusAdmin, serviceCode, new ConnectionFactoryBuilder());
    }

    public boolean deleteAll(String prefix) {
        OperationFuture<Boolean> future = null;
        boolean ret = false;
        try {
            future = this.arcusClient.flush(prefix);
            ret = future.get(1000L, TimeUnit.MILLISECONDS);
            return ret;
        } catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }
}
