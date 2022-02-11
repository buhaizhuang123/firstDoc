package com.bu.firstdoc.nettyDemo.second.base.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.concurrent.Promise;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.xml.ws.Response;

/**
 * @author haizhuangbu
 * @date 10:30 上午 2022/1/27
 * @mark ClientHandler
 */
@Data
@Slf4j
@ChannelHandler.Sharable
public class ClientHandler extends ChannelInboundHandlerAdapter {

    public static final ClientHandler THIS = new ClientHandler();

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf str  = (ByteBuf)msg;
        byte[] bytes = new byte[str.readableBytes()];
        str.getBytes(0, bytes);
        log.info("返回数据:{}",new String(bytes,"UTF-8"));
    }
}
