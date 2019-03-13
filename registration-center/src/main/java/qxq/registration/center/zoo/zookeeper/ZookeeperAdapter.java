package qxq.registration.center.zoo.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import qxq.registration.center.common.Config;
import qxq.registration.center.common.ServiceInfo;
import qxq.registration.center.listener.ServicesChangeListener;
import qxq.registration.center.listener.ServicesChangeListenerHodler;
import qxq.registration.center.zoo.ZooAdapter;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static qxq.registration.center.consts.CommonConst.DEFAULT_ZNODE_VALUE;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 原生API
 * @date 2019/3/13 18:05
 **/
@Slf4j
public class ZookeeperAdapter implements ZooAdapter {
    private ZooKeeper zooKeeper;
    private Config config;
    private final ServicesChangeListenerHodler servicesChangeListenerHodler = new ServicesChangeListenerHodler();
    @Override
    public void init(Config config) {
        this.config = config;
        try {

            CountDownLatch cdl = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(config.getZkServers(), config.getSessionTimeout(), event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    cdl.countDown();
                }
            });
            cdl.await();
            log.info("连接zookeeper成功...");
        } catch (IOException | InterruptedException e) {
            log.error("zookeeper初始化失败 --> zkServers: {}", config.getZkServers(),  e);
        }
    }

    @Override
    public void listener() {
        try {
            // 创建根目录
            Stat stat = zooKeeper.exists(config.getBasePath(), false);
            if (stat == null) {
                zooKeeper.create(config.getBasePath(), DEFAULT_ZNODE_VALUE,
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            // 获取服务列表
            List<String> children = zooKeeper.getChildren(config.getBasePath(),
                    new ZookeeperDefaultWatch(zooKeeper, servicesChangeListenerHodler));

            servicesChangeListenerHodler.notifyListeners(children);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void registerListener(ServicesChangeListener servicesChangeListener) {
        servicesChangeListenerHodler.registerListener(servicesChangeListener);
    }

    @Override
    public void register(ServiceInfo serviceInfo) {
        // 注册
        try {
            zooKeeper.create(serviceInfo.buildZnodePath(config.getBasePath()),
                    DEFAULT_ZNODE_VALUE, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
