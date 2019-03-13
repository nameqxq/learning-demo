package qxq.registration.center.listener;

import java.util.LinkedList;
import java.util.List;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/13 18:13
 **/
public class ServicesChangeListenerHodler {

    private final List<ServicesChangeListener> servicesChangeListeners = new LinkedList<>();

    public void notifyListeners(List<String> children) {
        for (ServicesChangeListener servicesChangeListener : servicesChangeListeners) {
            servicesChangeListener.onChange(children);
        }
    }

    public void registerListener(ServicesChangeListener servicesChangeListener) {
        if (servicesChangeListener != null) {
            servicesChangeListeners.add(servicesChangeListener);
        }
    }
}
