package quxiqi.rpc.netty;

import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

/**
 * @author : Quxiqi
 * @Description :
 * @date : 2019/4/21 21:53.
 * @remark:
 */
public class NettyServer {
    private final int port = 8888;

    public void start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
    }
}
