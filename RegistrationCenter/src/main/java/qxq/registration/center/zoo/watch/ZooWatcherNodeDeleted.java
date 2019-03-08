package qxq.registration.center.zoo.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 对应 NodeDeleted 的watcher
 * @date 2019/3/8 18:01
 **/
public class ZooWatcherNodeDeleted implements Watcher {
    @Override
    public void process(WatchedEvent event) {

    }
}
