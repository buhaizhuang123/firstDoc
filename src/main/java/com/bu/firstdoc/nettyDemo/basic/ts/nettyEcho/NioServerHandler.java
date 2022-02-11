package com.bu.firstdoc.nettyDemo.basic.ts.nettyEcho;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 11:38 上午 2022/1/26
 * @mark NioHandler
 */
@Slf4j
@ChannelHandler.Sharable
public class NioServerHandler extends ChannelInboundHandlerAdapter {

    public static final  NioServerHandler INTANCE = new NioServerHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        log.info("======= 读取数据 =========");
        ByteBuf buf  = (ByteBuf)msg;

        int len = buf.readableBytes();

        System.out.println("len = " + len);

        byte[] bytes = new byte[len];

        buf.getBytes(0,bytes);

        log.info("server received :{}",new String(bytes,"UTF-8"));

        // 写回数据 异步任务
        ChannelFuture channelFuture = ctx.writeAndFlush(msg);

        channelFuture.addListener((ChannelFuture futureListener)->{
            log.info(" 写回后(ByteBuf)msg:{} ",((ByteBuf)msg).refCnt());
        });

    }
}
