package com.bu.firstdoc.learnMore.client.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 2:28 下午 2022/1/27
 * @mark NettyClientHandler
 */
@Slf4j
public class NettyClientHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf)
        {
            ByteBuf buf =  (ByteBuf)msg;

            byte[] bytes = new byte[buf.readableBytes()];

            buf.getBytes(0, bytes);

            log.info("返回数据:{}",new String(bytes,"UTF-8"));
          }
        ctx.channel().writeAndFlush("from Client"+Thread.currentThread());
    }
}
