package qxq.registration.center.zoo.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import qxq.registration.center.zoo.ZooHolder;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:49
 **/
public class ZooDispatchWatch implements Watcher {
    private Watcher[] watchers = new Watcher[Event.EventType.values().length];
    public ZooDispatchWatch(ZooHolder zooHolder) {
        watchers[Event.EventType.None.ordinal()] = new ZooWatcherNone(zooHolder);
        watchers[Event.EventType.NodeCreated.ordinal()] = new ZooWatcherNodeCreated(zooHolder);
        watchers[Event.EventType.NodeChildrenChanged.ordinal()] = new ZooWatcherNodeChildrenChanged(zooHolder);
        watchers[Event.EventType.NodeDataChanged.ordinal()] = new ZooWatcherNodeDataChanged(zooHolder);
        watchers[Event.EventType.NodeDeleted.ordinal()] = new ZooWatcherNodeDeleted(zooHolder);
    }

    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        watchers[type.ordinal()].process(event);
    }

}
