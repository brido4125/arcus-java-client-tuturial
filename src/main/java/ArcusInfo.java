import net.spy.memcached.ArcusClient;
import net.spy.memcached.ConnectionFactoryBuilder;

public class ArcusInfo {
    private String arcusAdmin;
    private String serviceCode;
    private ArcusClient arcusClient;

    public ArcusInfo(String arcusAdmin, String serviceCode) {
        this.arcusAdmin = arcusAdmin;
        this.serviceCode = serviceCode;
        System.setProperty("net.spy.log.LoggerImpl", "net.spy.memcached.compat.log.Log4JLogger");
        this.arcusClient = ArcusClient.createArcusClient(arcusAdmin, serviceCode, new ConnectionFactoryBuilder());
    }
}
