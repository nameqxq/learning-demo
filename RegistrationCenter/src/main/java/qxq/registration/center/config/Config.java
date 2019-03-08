package qxq.registration.center.config;

import lombok.Data;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description 对应配置文件
 * @date 2019/3/8 17:43
 **/
@Data
public class Config {
    private String zkService;

    private String basePath;

    private int sessionTimeout;
}
