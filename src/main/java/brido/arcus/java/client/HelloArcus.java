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
        this.arcusClient = ArcusClient.createArcusClient(arcusAdmin, serviceCode, new ConnectionFactoryBuilder());
    }

    public boolean sayHello() {
        Future<Boolean> future = null;
        boolean setSuccess = false;

        future = this.arcusClient.set("test:hello", 600, "Hello Brido");

        try {
            setSuccess = future.get(700L, TimeUnit.MICROSECONDS);
        } catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return setSuccess;
    }

    public String listenHello() {
        Future<Object> future = null;
        String res = "Not";
        future = this.arcusClient.asyncGet("test:hello");
        try {
            res = (String)future.get(700L, TimeUnit.MICROSECONDS);
        } catch (Exception e) {
            if (future != null) future.cancel(true);
            e.printStackTrace();
        }
        return res;
    }
}
