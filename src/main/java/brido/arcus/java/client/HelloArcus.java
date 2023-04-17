package brido.arcus.java.client;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.collection.CollectionAttributes;
import net.spy.memcached.collection.ElementValueType;
import net.spy.memcached.internal.CollectionFuture;
import net.spy.memcached.internal.OperationFuture;
import net.spy.memcached.ops.StatusCode;

import java.util.Objects;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class HelloArcus {

    private String arcusAdmin;
    private String serviceCode;
    private ArcusClient arcusClient;

    public HelloArcus(String arcusAdmin, String serviceCode) {
        this.arcusAdmin = arcusAdmin;
        this.serviceCode = serviceCode;
        System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.Log4JLogger");
        this.arcusClient = ArcusClient.createArcusClient(arcusAdmin, serviceCode, new ConnectionFactoryBuilder());
    }

    public boolean setHello(String key,Object value) {
        OperationFuture<Boolean> future = null;
        boolean setSuccess = false;

        try {
            future = this.arcusClient.set(key, 6, value);
            setSuccess = future.get(700L, TimeUnit.MILLISECONDS);
            return setSuccess;
        } catch (Exception e) {
            if (future != null)
                future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }

    public boolean addData(String key,Object value) {
        Future<Boolean> future = null;
        boolean ret = false;
        try {
            future = this.arcusClient.add(key, 6, value);
            ret = future.get(700L, TimeUnit.MILLISECONDS);
            return ret;
        } catch (Exception e) {
            if (future != null)
                future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }

    public String getData(String key) {
        Future<Object> future = null;
        String res = "Not Yet";
        future = this.arcusClient.asyncGet(key);
        try {
            res = (String)future.get(700L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return res;
    }

    public boolean deleteData(String key) {
        Future<Boolean> future = null;
        boolean ret = false;
        try {
            future = this.arcusClient.delete(key);
            ret = future.get(700L, TimeUnit.MILLISECONDS);
            return ret;
        } catch (Exception e) {
            if (future != null)
                future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }

    public boolean createBop(String key) {
        CollectionFuture<Boolean> future = null;
        CollectionAttributes attribute = new CollectionAttributes(); // (1)
        attribute.setExpireTime(6);
        boolean ret = false;
        try {
            future = this.arcusClient.asyncBopCreate(key, ElementValueType.INTEGER, attribute);
            ret = future.get();
            return ret;
        } catch (Exception e) {
            if (future != null)
                future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }

    public boolean insertBop(String key, long bKey, Object value) {
        CollectionFuture<Boolean> future = null;
        CollectionAttributes ca = new CollectionAttributes();
        byte[] eflag = new byte[] { 1, 1, 1, 1 };
        boolean ret = false;
        try {
            future = this.arcusClient.asyncBopInsert(key, bKey, eflag, value, ca);
            ret = future.get();
            return ret;
        } catch (Exception e) {
            if (future != null)
                future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }
}
