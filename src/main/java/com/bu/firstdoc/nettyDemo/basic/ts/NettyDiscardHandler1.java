package com.bu.firstdoc.nettyDemo.basic.ts;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 11:25 上午 2022/1/20
 * @mark NettyDiscardHandler
 */
@Slf4j
public class NettyDiscardHandler1 extends ChannelInboundHandlerAdapter {

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        log.info("========== handlerAdded ========== 被调用 ");
        super.handlerAdded(ctx);

    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        log.info("=========== channelRegistered ===========");
        super.channelRegistered(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("=========== 被调用 channelActive ===========");
        super.channelActive(ctx);
    }



    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf in = (ByteBuf) msg;
            log.info("收到消息 丢弃如下");
            while (in.isReadable()) {
                System.out.println("in.readByte() = " + (char) in.readByte());
            }
            System.out.println();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info(" =========== channelReadComplete ============ ");
        super.channelReadComplete(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("============= channelInactive ============");
        super.channelInactive(ctx);
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("============= channelUnregistered ===============");
        super.channelUnregistered(ctx);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        log.info(" ============= handlerRemoved ===============");
        super.handlerRemoved(ctx);
    }
}
