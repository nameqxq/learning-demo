package qxq.registration.center.common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import static qxq.registration.center.consts.CommonConst.NODE_NAME_LENGTH;
import static qxq.registration.center.consts.CommonConst.NODE_NAME_SEPARATOR;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/12 15:11
 **/
@Slf4j
@Data
public class ServiceInfo {
    /**
     * 模块.服务名
     */
    private String module;

    private String ip;

    private String port;

    public static ServiceInfo build(String str) {
        String[] split = str.split(NODE_NAME_SEPARATOR);
        if (split.length != NODE_NAME_LENGTH) {
           log.warn("注册格式错误 --> znode: " + str);
           return null;
        }
        ServiceInfo serviceInfo = new ServiceInfo();
        serviceInfo.module = split[0];
        serviceInfo.ip = split[1];
        serviceInfo.port = split[2];
        return serviceInfo;
    }

    public String buildZnodePath(String basePath) {
        return basePath + "/" + module + NODE_NAME_SEPARATOR + ip + NODE_NAME_SEPARATOR + port;
    }
}
