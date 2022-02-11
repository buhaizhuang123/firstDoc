package com.bu.firstdoc.learnMore.client;

import com.bu.firstdoc.learnMore.client.impl.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author haizhuangbu
 * @date 2:15 下午 2022/1/27
 * @mark NioNettyEchoClient 客户端
 */
@Slf4j
public class NioNettyEchoClient {

    private Bootstrap bootstrap ;

    {
        bootstrap = new Bootstrap();
    }

    public void startClient(){

        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();

        try {
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.group(nioEventLoopGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new NettyClientHandler());
                    }
                });

            ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8081)).sync();

            future.addListener((ChannelFuture fu)->{
                if (fu.isSuccess()) {
                    log.info("服务器链接成功 。。。。");
                }else {
                    log.info("服务器链接失败 。。。。");
                }
            });


            future.sync();


            Scanner scanner = new Scanner(System.in);

            while (scanner.hasNext()) {
                log.info(" 请输入内容 ");
                Channel channel = future.channel();
                ByteBuf buffer = channel.alloc().buffer();
                byte[] bytes = scanner.next().getBytes(StandardCharsets.UTF_8);
                buffer.writeBytes(bytes);
                channel.writeAndFlush(buffer);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            nioEventLoopGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) {
        NioNettyEchoClient nioNettyEchoClient = new NioNettyEchoClient();

        nioNettyEchoClient.startClient();
    }
}
