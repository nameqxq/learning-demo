package qxq.registration.center.common;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    public static Map<String, List<ServiceInfo>> getServices() {
        readLock.lock();
        try {
            return services;
        } finally {
            readLock.unlock();
        }
    }

    private static void setServices(Map<String, List<ServiceInfo>> services) {
        writeLock.lock();
        try {
                log.info("服务列表替换 --> 原列表: {}", JSONObject.toJSONString(Configuration.services));
            Configuration.services = services;
            log.info("服务列表替换 --> 新列表: {}", JSONObject.toJSONString(Configuration.services));
        } finally {
            writeLock.unlock();
        }
    }

    public static void setServices(List<String> children) {
        Map<String, List<ServiceInfo>> services = children.stream()
                .map(ServiceInfo::build)
                .collect(Collectors.groupingBy(ServiceInfo::getModule));
        setServices(services);
    }
}
