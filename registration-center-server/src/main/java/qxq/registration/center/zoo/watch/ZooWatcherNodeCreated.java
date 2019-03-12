package qxq.registration.center.zoo.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import qxq.registration.center.zoo.ZooHolder;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 对应NodeCreated 的watcher
 * @date 2019/3/8 17:59
 **/
public class ZooWatcherNodeCreated implements Watcher {
    private ZooHolder zooHolder;

    public ZooWatcherNodeCreated(ZooHolder zooHolder) {
        this.zooHolder = zooHolder;
    }

    @Override
    public void process(WatchedEvent event) {
        ZooKeeper zooKeeper = zooHolder.zooKeeper();

        // zooKeeper.
    }
}
