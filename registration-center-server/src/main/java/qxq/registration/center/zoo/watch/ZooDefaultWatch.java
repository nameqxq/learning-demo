package qxq.registration.center.zoo.watch;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import qxq.registration.center.common.Configuration;
import qxq.registration.center.zoo.ZooHolder;

import java.util.List;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:49
 **/
@Slf4j
public class ZooDefaultWatch implements Watcher {
    private ZooHolder zooHolder;
    public ZooDefaultWatch(ZooHolder zooHolder) {
        this.zooHolder = zooHolder;
    }

    @Override
    public void process(WatchedEvent event) {
        log.info("zookeeper watch ... {}", event);
        ZooKeeper zooKeeper = zooHolder.getZooKeeper();
        try {
            List<String> children = zooKeeper.getChildren(zooHolder.getConfig().getBasePath(), this);
            Configuration.setServices(children);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
