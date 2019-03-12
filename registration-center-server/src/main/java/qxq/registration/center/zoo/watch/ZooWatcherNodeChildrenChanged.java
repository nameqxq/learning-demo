package qxq.registration.center.zoo.watch;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import qxq.registration.center.common.ServiceInfo;
import qxq.registration.center.zoo.ZooHolder;

import java.util.List;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 对应NodeChildrenChanged 的watcher
 * @date 2019/3/8 18:02
 **/
public class ZooWatcherNodeChildrenChanged implements Watcher {
    private ZooHolder zooHolder;

    public ZooWatcherNodeChildrenChanged(ZooHolder zooHolder) {
        this.zooHolder = zooHolder;
    }

    @Override
    public void process(WatchedEvent event) {
        ZooKeeper zooKeeper = zooHolder.getZooKeeper();
        try {
            List<String> children = zooKeeper.getChildren(zooHolder.getConfig().getBasePath(), true);
            children.stream().map(ServiceInfo::build);
            for (String child : children) {

            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
