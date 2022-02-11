package com.bu.firstdoc.nettyDemo.second.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 10:07 上午 2022/1/27
 * @mark ServerHandler
 */
@Slf4j
@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    public static final ServerHandler INSTANCE  = new ServerHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("=============");
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.getBytes(0, bytes);
        log.info("===== 传入数据:{} ======",new String(bytes));
        ChannelFuture f = ctx.writeAndFlush(msg);
        f.addListener((ChannelFuture futureListener) -> {
            log.info("写回后，msg.refCnt:" + ((ByteBuf) msg).refCnt());
        });
    }
}
