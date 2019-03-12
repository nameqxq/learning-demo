package qxq.registration.center.zoo.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

import java.util.HashMap;
import java.util.Map;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:49
 **/
public class ZooDispatchWatch implements Watcher {
    private Map<Event.EventType, Watcher> watchers = new HashMap<>(8);

    public ZooDispatchWatch() {

    }

    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();


    }
}
