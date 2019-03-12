package qxq.registration.center.zoo;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import qxq.registration.center.config.Config;
import qxq.registration.center.zoo.watch.ZooDispatchWatch;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:34
 **/
@Slf4j
public class ZooInit {
    private ZooKeeper zooKeeper;
    private Config config;

    public ZooInit(Config config) {
        this.config = config;
        init();
    }

    private void init() {
        try {
            CountDownLatch cdl = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(config.getZkServers(), config.getSessionTimeout(), event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    cdl.countDown();
                }
            });
            cdl.await();
            listener();
        } catch (IOException | InterruptedException e) {
            log.error("zookeeper初始化失败 --> zkServers: {}", config.getZkServers(),  e);
        }
    }

    private void listener() {
        try {

            Stat stat = zooKeeper.exists(config.getBasePath(), new ZooDispatchWatch());
            if (stat == null) {
                zooKeeper.create(config.getBasePath(), "".getBytes(), null, CreateMode.PERSISTENT);
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
