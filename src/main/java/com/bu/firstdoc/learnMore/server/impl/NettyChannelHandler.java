package com.bu.firstdoc.learnMore.server.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 2:21 下午 2022/1/27
 * @mark NettyChannelHandler
 */
@Slf4j
public class NettyChannelHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;

        byte[] bytes = new byte[buf.readableBytes()];

        buf.getBytes(0,bytes);

        log.info("传入数据:{}",new String(bytes,"UTF-8"));

        ChannelFuture future = ctx.channel().writeAndFlush(buf);

        future.addListener((ChannelFuture fult)->{
            log.info("写回后，msg.refCnt:" + ((ByteBuf) msg).refCnt());
        });

    }
}
