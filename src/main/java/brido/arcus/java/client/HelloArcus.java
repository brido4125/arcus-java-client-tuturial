package brido.arcus.java.client;

import net.spy.memcached.ArcusClient;
import net.spy.memcached.ConnectionFactoryBuilder;

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

    public boolean sayHello() {
        Future<Boolean> future = null;
        boolean setSuccess = false;

        try {
            future = this.arcusClient.set("test", 600, "Brido");
            setSuccess = future.get(700L, TimeUnit.MILLISECONDS);
            return setSuccess;
        } catch (Exception e) {
            System.out.println("future = " + future);
            if (future != null)
                future.cancel(true);
            e.printStackTrace();
        }
        return false;
    }

    public String listenHello() {
        Future<Object> future = null;
        String res = "Not Yet";
        future = this.arcusClient.asyncGet("test");
        try {
            res = (String)future.get(700L, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return res;
    }
}
