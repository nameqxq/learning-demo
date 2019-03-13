package qxq.registration.center;

import qxq.registration.center.common.ServiceInfo;
import qxq.registration.center.config.Config;
import qxq.registration.center.utils.HostUtil;
import qxq.registration.center.zoo.ZooHolder;

import java.util.concurrent.locks.LockSupport;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:30
 **/
public class Start {
    public static void main(String[] args) throws InterruptedException {
        Config config = new Config();
        config.setBasePath("/registration");
        config.setSessionTimeout(10000);
        config.setZkServers("120.78.206.68:2181");

        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setModule("testService");
        serviceInfo.setIp(HostUtil.localHostAddr());
        serviceInfo.setPort("8888");
        new ZooHolder(config, serviceInfo);

        LockSupport.park(new Object());
    }
}
