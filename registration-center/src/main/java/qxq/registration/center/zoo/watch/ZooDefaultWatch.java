package qxq.registration.center.zoo.watch;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import qxq.registration.center.common.RegistrationConfiguration;

import java.util.List;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:49
 **/
@Slf4j
public class ZooDefaultWatch implements Watcher {

    @Override
    public void process(WatchedEvent event) {
        log.info("zookeeper watch ... {}", event);
        ZooKeeper zooKeeper = RegistrationConfiguration.instance().getZooKeeper();
        try {
            List<String> children =
                    zooKeeper.getChildren(RegistrationConfiguration.instance().getConfig().getBasePath(), this);
            RegistrationConfiguration.instance().setServices(children);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

}
