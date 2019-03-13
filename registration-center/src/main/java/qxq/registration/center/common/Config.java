package qxq.registration.center.common;

import lombok.Data;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 对应配置文件
 * @date 2019/3/8 17:43
 **/
@Data
public class Config {
    private final String zkServers;

    private final String basePath;

    private final int sessionTimeout;
}
