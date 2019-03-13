package qxq.registration.center.listener.impl;

import qxq.registration.center.common.RegistrationConfiguration;
import qxq.registration.center.listener.ServicesChangeListener;

import java.util.List;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 默认监听器
 * @date 2019/3/13 17:47
 **/
public class DefaultServicesChangeListener implements ServicesChangeListener {
    @Override
    public void onChange(List<String> serviceInfos) {
        RegistrationConfiguration.instance().setServices(serviceInfos);
    }
}
