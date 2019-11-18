package quxiqi.rpc.netty.line;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;

/**
 * @author <a href="mailto:quxiqi@zskuaixiao.com"> quxiqi </a>
 * @version 1.0 2019 11月.2019/11/18
 */
public class NettyServerLineHandler extends ChannelInboundHandlerAdapter {

    private static final String RESPONSE_MSG = "hi, Here is the server\r\n";
    private int readCount;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf msgBuf = (ByteBuf) msg;
        byte[] msgByte = new byte[msgBuf.readableBytes()];
        msgBuf.readBytes(msgByte);
        System.out.println("server accept meg: " + new String(msgByte) + "  "+ ++readCount);
        ReferenceCountUtil.release(msg);
    }

    // @Override
    // public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
    //     byte[] bytes = RESPONSE_MSG.getBytes();
    //     System.out.println("server send meg: " + RESPONSE_MSG);
    //     ctx.writeAndFlush(Unpooled.buffer(bytes.length).writeBytes(bytes));
    // }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
