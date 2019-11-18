package quxiqi.rpc.netty.line;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import quxiqi.rpc.netty.NettyConst;

import java.net.InetSocketAddress;

/**
 * @author : Quxiqi
 * @Description :
 * @date : 2019/4/21 21:53.
 * @remark:
 */
public class NettyLineClient {

    public void start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            ChannelFuture future = b.group(eventLoopGroup)
                    .option(ChannelOption.SO_LINGER, -1)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientLineHandler())
                    .connect(new InetSocketAddress(NettyConst.ADDRESS, NettyConst.PORT))
                    .sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyLineClient().start();
    }
}
