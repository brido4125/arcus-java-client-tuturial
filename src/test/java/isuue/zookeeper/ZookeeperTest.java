package isuue.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

public class ZookeeperTest {

    @BeforeEach
    void clear() {
        ZookeeperConnection zookeeperConnection = new ZookeeperConnection();
        try {
            ZooKeeper zookeeper = zookeeperConnection.connect("localhost:2181");
            zookeeper.delete("/test", -1);
            zookeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void conn() {
        ZookeeperConnection zookeeperConnection = new ZookeeperConnection();
        try {
            ZooKeeper zookeeper = zookeeperConnection.connect("localhost:2181");
            zookeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void create() {
        ZookeeperConnection zookeeperConnection = new ZookeeperConnection();
        try {
            ZooKeeper zookeeper = zookeeperConnection.connect("localhost:2181");
            zookeeper.create("/test", "test".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zookeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getData() {
        ZookeeperConnection zookeeperConnection = new ZookeeperConnection();
        try {
            ZooKeeper zookeeper = zookeeperConnection.connect("localhost:2181");
            zookeeper.create("/test", "test".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

            byte[] data = zookeeper.getData("/test", false, null);

            Assertions.assertThat(new String(data, StandardCharsets.UTF_8)).isEqualTo("test");
            zookeeper.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
