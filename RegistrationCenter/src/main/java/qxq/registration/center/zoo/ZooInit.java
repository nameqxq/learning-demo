package qxq.registration.center.zoo;

import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import qxq.registration.center.config.Config;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:34
 **/
public class ZooInit {
    private ZooKeeper zooKeeper;
    private volatile boolean ok;

    public void init (Config config) {
        try {
            CountDownLatch cdl = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(config.getZkService(), config.getSessionTimeout(), event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    cdl.countDown();
                }
            });

            // Thread thread = new Thread(() -> {
            //     try {
            //         cdl.await();
            //     } catch (InterruptedException e) {
            //         e.printStackTrace();
            //     }
            //     ok = true;
            // });

            // thread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
