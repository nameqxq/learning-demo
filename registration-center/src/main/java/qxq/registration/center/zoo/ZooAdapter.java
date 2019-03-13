package qxq.registration.center.zoo;

import qxq.registration.center.common.Config;
import qxq.registration.center.common.ServiceInfo;
import qxq.registration.center.listener.ServicesChangeListener;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description zookeeper适配器 -- 考虑使用不同的zk客户端（学习当然要都用用）
 * @date 2019/3/13 17:34
 **/
public interface ZooAdapter {
    /**
     * 功能描述 进行初始化工作
     * @param config 配置对象
     * @author quxiqi
     * @date 2019/3/13 17:36
     **/
    void init(Config config);

    /**
     * 功能描述 开始监听
     * @author quxiqi
     * @date 2019/3/13 18:17
     **/
    void listener();

    /**
     * 功能描述 注册监听器
     * 服务变更时将通知监听器
     * @param servicesChangeListener 监听器
     * @author quxiqi
     * @date 2019/3/13 17:56
     **/
    void registerListener(ServicesChangeListener servicesChangeListener);

    /**
     * 功能描述 注册服务
     * @param serviceInfo 服务注册信息
     * @author quxiqi
     * @date 2019/3/13 17:56
     **/
    void register(ServiceInfo serviceInfo);
}
