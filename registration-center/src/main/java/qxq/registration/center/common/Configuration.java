package qxq.registration.center.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import qxq.registration.center.utils.UnmodifiableListCollector;

import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collectors;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:32
 **/
@Slf4j
public class Configuration {

    private static Map<String, List<ServiceInfo>> services = new HashMap<>();


    private static final Lock READ_LOCK;
    private static final Lock WRITE_LOCK;
    static {
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
        READ_LOCK = readWriteLock.readLock();
        WRITE_LOCK = readWriteLock.writeLock();
    }

    public static Map<String, List<ServiceInfo>> getServices() {
        READ_LOCK.lock();
        try {
            return services;
        } finally {
            READ_LOCK.unlock();
        }
    }

    private static void setServices(Map<String, List<ServiceInfo>> services) {
        WRITE_LOCK.lock();
        try {
            log.info("服务列表替换 --> 原列表: {}", JSONObject.toJSONString(Configuration.services));
            Configuration.services = Collections.unmodifiableMap(services);
            log.info("服务列表替换 --> 新列表: {}", JSONObject.toJSONString(Configuration.services));
        } finally {
            WRITE_LOCK.unlock();
        }
    }

    public static void setServices(List<String> children) {
        Map<String, List<ServiceInfo>> services = children.stream()
                .map(ServiceInfo::build)
                .collect(
                        Collectors.groupingBy(
                            ServiceInfo::getModule,
                            HashMap::new,
                            new UnmodifiableListCollector(LinkedList::new)
                        )
                );
        setServices(services);
    }
}
