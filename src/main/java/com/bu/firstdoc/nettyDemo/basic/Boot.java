package com.bu.firstdoc.nettyDemo.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * @author haizhuangbu
 * @date 5:09 下午 2022/1/25
 * @mark Boot
 */
@Slf4j
public class Boot {

    public static void main(String[] args) throws InterruptedException {

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        EventLoopGroup eventExecutors = new NioEventLoopGroup(1);
        EventLoopGroup eventExecutor = new NioEventLoopGroup();


        // 设置反应器 和 处理器
        serverBootstrap.group(eventExecutors,eventExecutor);

        // 设置通道类型 Nio
        serverBootstrap.channel(NioServerSocketChannel.class);

        // 设置绑定端口
        serverBootstrap.localAddress(new InetSocketAddress(8081));

        // 设置通道参数
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE,true);
        serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
//        serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        
        // 装配子通道流水线
        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel){
                // 向子通道流水线添加handler业务处理器
                socketChannel.pipeline().addLast(new NettyDiscardHandler());
            }
        });

        // 开始绑定端口 通过sync 方法阻塞
        ChannelFuture channelFuture = serverBootstrap.bind().sync();

        log.info("服务器启动成功,监听端口:{}",channelFuture.channel().localAddress());

        // 自我阻塞 直到通道关闭和异步任务结束
        ChannelFuture channelFuture1 = channelFuture.channel().closeFuture();

        channelFuture1.sync();

        eventExecutors.shutdownGracefully();
        eventExecutor.shutdownGracefully();
    }

}
