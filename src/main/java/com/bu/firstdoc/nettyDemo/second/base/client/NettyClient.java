package com.bu.firstdoc.nettyDemo.second.base.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultPromise;
import lombok.extern.slf4j.Slf4j;

import javax.xml.ws.Response;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

/**
 * @author haizhuangbu
 * @date 10:23 上午 2022/1/27
 * @mark ClientServer
 */
@Slf4j
public class NettyClient {

    public static EventLoopGroup group = null;

    public static Bootstrap bootstrap = null;

    static {
        group = new NioEventLoopGroup();

        bootstrap = new Bootstrap();

        //
        bootstrap.channel(NioSocketChannel.class);

        bootstrap.remoteAddress(new InetSocketAddress("127.0.0.1", 8081));

        bootstrap.group(group);

        // 设置内存分配器
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
    }

    public static void exe() {

        try {

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                log.info("执行 初始化");
                socketChannel.pipeline().addLast(ClientHandler.THIS);
            }
        });
        ChannelFuture future = bootstrap.connect();

            future.addListener((ChannelFuture fule)->{
                if (fule.isSuccess()) {
                    log.info("服务器链接成功");
                }else {
                    log.info("服务器链接失败");
                }
            });

        future.sync();

            Scanner sc = new Scanner(System.in);
            while (sc.hasNext()) {
                log.info("请输入内容");
                Channel channel = future.channel();
                ByteBuf buffer = channel.alloc().buffer();
                buffer.writeBytes(sc.next().getBytes(StandardCharsets.UTF_8));
                channel.writeAndFlush(buffer);

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        NettyClient.exe();
    }


}
