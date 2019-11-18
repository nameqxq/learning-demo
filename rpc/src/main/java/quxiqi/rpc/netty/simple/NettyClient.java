package quxiqi.rpc.netty.simple;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
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
public class NettyClient {

    public void start() {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            ChannelFuture future = b.group(eventLoopGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new NettyClientHandler())
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
        new NettyClient().start();
    }
}
