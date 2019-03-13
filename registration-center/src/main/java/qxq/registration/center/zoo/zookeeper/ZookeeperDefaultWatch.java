package qxq.registration.center.zoo.zookeeper;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import qxq.registration.center.common.RegistrationConfiguration;
import qxq.registration.center.listener.ServicesChangeListenerHodler;

import java.util.List;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:49
 **/
@Slf4j
public class ZookeeperDefaultWatch implements Watcher {

    private final ZooKeeper zooKeeper;
    private final ServicesChangeListenerHodler servicesChangeListenerHodler;

    public ZookeeperDefaultWatch(ZooKeeper zooKeeper, ServicesChangeListenerHodler servicesChangeListenerHodler) {
        this.zooKeeper = zooKeeper;
        this.servicesChangeListenerHodler = servicesChangeListenerHodler;
    }

    @Override
    public void process(WatchedEvent event) {
        log.info("zookeeper watch ... {}", event);
        try {
            List<String> children =
                    zooKeeper.getChildren(RegistrationConfiguration.instance().getConfig().getBasePath(), this);
            servicesChangeListenerHodler.notifyListeners(children);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
