package qxq.registration.center;

import qxq.registration.center.common.ServiceInfo;
import qxq.registration.center.common.Config;
import qxq.registration.center.utils.HostUtil;
import qxq.registration.center.common.RegistrationConfiguration;

import java.util.concurrent.locks.LockSupport;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/8 17:30
 **/
public class Start {
    public static void main(String[] args) {
        Config config = new Config("120.78.206.68:2181", "/registration", 10000);

        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.setModule("testService");
        serviceInfo.setIp(HostUtil.localHostAddr());
        serviceInfo.setPort("8888");
        RegistrationConfiguration.generate(config, serviceInfo);

        LockSupport.park(new Object());
    }
}
