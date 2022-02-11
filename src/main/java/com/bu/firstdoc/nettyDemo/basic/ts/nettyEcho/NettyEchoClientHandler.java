package com.bu.firstdoc.nettyDemo.basic.ts.nettyEcho;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 12:00 下午 2022/1/26
 * @mark NettyEchoClientHandler
 */
@Slf4j
public class NettyEchoClientHandler extends ChannelInboundHandlerAdapter {

    public static final NettyEchoClientHandler INSTANCE  = new NettyEchoClientHandler();


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        int len = byteBuf.readableBytes();
        byte[] bytes = new byte[len];
        log.info("===== client received:{} ========= ",new String(bytes,"UTF-8"));
        // 方法一: 手动释放byteBuf
        byteBuf.release();
    }
}
