package quxiqi.rpc.netty.line;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 11月.2019/11/18
 */
public class NettyClientLineHandler extends SimpleChannelInboundHandler<ByteBuf> {

    private static final String RESPONSE_MSG = "hi, Here is the client\r\n";

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] msgByte = new byte[msg.readableBytes()];
        msg.readBytes(msgByte);
        System.out.println("client accept meg: " + new String(msgByte));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端连接成功!");
        byte[] bytes = RESPONSE_MSG.getBytes();
        for (int i = 0; i < 100; i++) {
            ctx.write(Unpooled.buffer(bytes.length).writeBytes(bytes));
            System.out.println("client send meg: " + RESPONSE_MSG + "  " + (i+1));
        }
        ctx.flush();
        ctx.close();
    }
}
