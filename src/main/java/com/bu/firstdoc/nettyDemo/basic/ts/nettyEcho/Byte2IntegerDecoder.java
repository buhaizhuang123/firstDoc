package com.bu.firstdoc.nettyDemo.basic.ts.nettyEcho;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 2:53 下午 2022/1/26
 * @mark Byte2IntegerDecoder 自定义解码器
 */
public class Byte2IntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() >= 4) {
            int i = byteBuf.readInt();
            list.add(i);
        }
    }
}
