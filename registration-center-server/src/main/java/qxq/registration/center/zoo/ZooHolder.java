package qxq.registration.center.zoo;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import qxq.registration.center.common.Configuration;
import qxq.registration.center.common.ServiceInfo;
import qxq.registration.center.config.Config;
import qxq.registration.center.zoo.watch.ZooDefaultWatch;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static qxq.registration.center.consts.CommonConst.DEFAULT_ZNODE_VALUE;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:34
 **/
@Slf4j
public class ZooHolder {
    private ZooKeeper zooKeeper;
    private Config config;
    private ServiceInfo serviceInfo;
    public ZooHolder(Config config, ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
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
            log.info("连接zookeeper成功...");
            listener();
        } catch (IOException | InterruptedException e) {
            log.error("zookeeper初始化失败 --> zkServers: {}", config.getZkServers(),  e);
        }
    }

    private void listener() {
        try {
            // 创建根目录
            Stat stat = zooKeeper.exists(config.getBasePath(), false);
            if (stat == null) {
                zooKeeper.create(config.getBasePath(), DEFAULT_ZNODE_VALUE,
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            // 自注册
            zooKeeper.create(serviceInfo.buildZnodePath(config.getBasePath()),
                    DEFAULT_ZNODE_VALUE, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

            // 获取服务列表
            List<String> children = zooKeeper.getChildren(config.getBasePath(), new ZooDefaultWatch(this));
            Configuration.setServices(children);

        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public Config getConfig() {
        return config;
    }
}
