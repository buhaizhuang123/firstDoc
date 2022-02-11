package com.bu.firstdoc.nettyDemo.basic.ts;

import com.bu.firstdoc.nettyDemo.basic.NettyDiscardHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.io.IOException;

/**
 * @author haizhuangbu
 * @date 11:06 上午 2022/1/26
 * @mark ts
 */
public class ts {

    @Test
    public void tsInHandlerLife() throws IOException {
        final NettyDiscardHandler1 nettyDiscardHandler = new NettyDiscardHandler1();
        ChannelInitializer<EmbeddedChannel> i = new ChannelInitializer<EmbeddedChannel>() {
            @Override
            protected void initChannel(EmbeddedChannel embeddedChannel) throws Exception {
                embeddedChannel.pipeline().addLast(nettyDiscardHandler);
            }
        };

        EmbeddedChannel embeddedChannel = new EmbeddedChannel(i);

        ByteBuf buffer = Unpooled.buffer();

        buffer.writeInt(1);

        embeddedChannel.writeInbound(buffer);

        embeddedChannel.flush();



        embeddedChannel.writeInbound(buffer);

        embeddedChannel.flush();

        embeddedChannel.close();


    }

}
