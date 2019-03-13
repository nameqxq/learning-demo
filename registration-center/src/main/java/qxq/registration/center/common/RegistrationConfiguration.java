package qxq.registration.center.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import qxq.registration.center.exceptions.RegistrationException;
import qxq.registration.center.utils.UnmodifiableListCollector;
import qxq.registration.center.zoo.watch.ZooDefaultWatch;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

import static qxq.registration.center.consts.CommonConst.DEFAULT_ZNODE_VALUE;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 核心类
 * @date 2019/3/8 17:34
 **/
@Slf4j
public class RegistrationConfiguration {

    private static RegistrationConfiguration instance;

    public static void generate(Config config, ServiceInfo serviceInfo) {
        if (instance != null) {
            throw new RegistrationException("不能重复创建 RegistrationConfiguration !");
        }
        synchronized (RegistrationConfiguration.class) {
            if (instance != null) {
                throw new RegistrationException("不能重复创建 RegistrationConfiguration !");
            }
            instance = new RegistrationConfiguration(config, serviceInfo);
        }
    }

    public static RegistrationConfiguration instance() {
        if (instance == null) {
            throw new RegistrationException("RegistrationConfiguration 尚未创建!");
        }
        return instance;
    }

    private ZooKeeper zooKeeper;
    private Config config;
    private ServiceInfo serviceInfo;
    /**
     * 服务列表
     */
    private Map<String, List<ServiceInfo>> services = Collections.unmodifiableMap(new HashMap<>());

    /**
     * 服务列表读锁
     */
    private final Lock readLock;
    /**
     * 服务列表写锁
     */
    private final Lock writeLock;

    private RegistrationConfiguration(Config config, ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
        this.config = config;

        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        readLock = readWriteLock.readLock();
        writeLock = readWriteLock.writeLock();

        init();
    }

    public ZooKeeper getZooKeeper() {
        return zooKeeper;
    }

    public Config getConfig() {
        return config;
    }

    public Map<String, List<ServiceInfo>> getServices() {
        readLock.lock();
        try {
            return services;
        } finally {
            readLock.unlock();
        }
    }

    /**
     * 功能描述
     * 根据服务信息构造服务列表
     * @param children 服务信息 格式为 module@#@ip@#@port
     * @author quxiqi
     * @date 2019/3/13 17:14
     **/
    public void setServices(List<String> children) {
        Map<String, List<ServiceInfo>> services = children.stream()
                .map(ServiceInfo::build)
                .filter(Objects::nonNull)
                .collect(
                        Collectors.groupingBy(
                                ServiceInfo::getModule,
                                HashMap::new,
                                new UnmodifiableListCollector(LinkedList::new)
                        )
                );
        setServices(services);
    }

    private void setServices(Map<String, List<ServiceInfo>> services) {
        writeLock.lock();
        try {
            log.info("服务列表替换 --> 原列表: {}", JSONObject.toJSONString(this.services));
            this.services = Collections.unmodifiableMap(services);
            log.info("服务列表替换 --> 新列表: {}", JSONObject.toJSONString(this.services));
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * zk连接
     */
    private void init() {
        try {
            CountDownLatch cdl = new CountDownLatch(1);
            zooKeeper = new ZooKeeper(config.getZkServers(), config.getSessionTimeout(), event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    cdl.countDown();
                }
            });
            cdl.await();
            log.info("连接zookeeper成功...");
            listener();
        } catch (IOException | InterruptedException e) {
            log.error("zookeeper初始化失败 --> zkServers: {}", config.getZkServers(),  e);
        }
    }

    /**
     *
     */
    private void listener() {
        try {
            // 创建根目录
            Stat stat = zooKeeper.exists(config.getBasePath(), false);
            if (stat == null) {
                zooKeeper.create(config.getBasePath(), DEFAULT_ZNODE_VALUE,
                        ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            // 自注册
            zooKeeper.create(serviceInfo.buildZnodePath(config.getBasePath()),
                    DEFAULT_ZNODE_VALUE, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);

            // 获取服务列表
            List<String> children = zooKeeper.getChildren(config.getBasePath(), new ZooDefaultWatch());
            setServices(children);

        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
