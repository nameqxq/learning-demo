package qxq.registration.center.listener;

import java.util.List;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 服务列表变更监听器
 * @date 2019/3/13 17:45
 **/
public interface ServicesChangeListener {

    /**
     * 功能描述 服务列表变更时触发
     * @author quxiqi
     * @date 2019/3/13 17:46
     **/
    void onChange(List<String> serviceInfos);
}
