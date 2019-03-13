package qxq.registration.center.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author quxiqi
 * @email quxiqi@zskuaixiao.com
 * @description
 * @date 2019/3/12 14:05
 **/
public class HostUtil  {

    private static class HostException extends RuntimeException {
        HostException(String message) {
            super(message);
        }
    }

    private HostUtil(){}
    private static final String HOST_ADDR;
    private static final String HOST_NAME;
    static {
        InetAddress addr;
        try {
            addr = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new HostException("获取本机IP地址出错...");
        }
        HOST_ADDR = addr.getHostAddress();
        HOST_NAME = addr.getHostName();
    }
    public static String localHostAddr() {
        return HOST_ADDR;
    }
    public static String localHostName() {
        return HOST_NAME;
    }
}
