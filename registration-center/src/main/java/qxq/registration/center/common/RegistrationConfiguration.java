package qxq.registration.center.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import qxq.registration.center.exceptions.RegistrationException;
import qxq.registration.center.listener.impl.DefaultServicesChangeListener;
import qxq.registration.center.utils.UnmodifiableListCollector;
import qxq.registration.center.zoo.ZooAdapter;
import qxq.registration.center.zoo.official.ZookeeperAdapter;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 核心类
 * @date 2019/3/8 17:34
 **/
@Slf4j
public class RegistrationConfiguration {

    private static RegistrationConfiguration instance;

    public static RegistrationConfiguration generate(Config config, ServiceInfo serviceInfo) {
        if (instance != null) {
            throw new RegistrationException("不能重复创建 RegistrationConfiguration !");
        }
        synchronized (RegistrationConfiguration.class) {
            if (instance != null) {
                throw new RegistrationException("不能重复创建 RegistrationConfiguration !");
            }
            instance = new RegistrationConfiguration(config, serviceInfo);
        }
        return instance;
    }

    public static RegistrationConfiguration instance() {
        if (instance == null) {
            throw new RegistrationException("RegistrationConfiguration 尚未创建!");
        }
        return instance;
    }

    private ZooAdapter zooAdapter = new ZookeeperAdapter();
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

    public void start() {
        zooAdapter.listener();
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
     * zk初始化
     */
    private void init() {
        zooAdapter.init(config);
        zooAdapter.register(serviceInfo);
        zooAdapter.registerListener(new DefaultServicesChangeListener());
    }

}
