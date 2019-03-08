package qxq.registration.center.zoo.watch;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 对应 None 的 watcher
 * @date 2019/3/8 17:58
 **/
public class ZooWatcherNone implements Watcher {
    @Override
    public void process(WatchedEvent event) {

    }
}
