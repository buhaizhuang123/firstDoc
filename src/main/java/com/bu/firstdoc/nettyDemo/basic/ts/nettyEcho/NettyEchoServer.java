package com.bu.firstdoc.nettyDemo.basic.ts.nettyEcho;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 11:34 上午 2022/1/26
 * @mark NettyEchoServer
 */
@Slf4j
public class NettyEchoServer {

    private ServerBootstrap serverBootstrap = new ServerBootstrap();


    public  void runServer(){

        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup work = new NioEventLoopGroup();
        try {
        // 设置反应器和处理器
        serverBootstrap.group(boss,work);
        // 设置通道类型为nio
        serverBootstrap.channel(NioServerSocketChannel.class);
        //3 设置监听端口
        serverBootstrap.localAddress(8081);
        //4 设置通道的参数
        serverBootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        serverBootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        serverBootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) {

                socketChannel.pipeline().addLast(NioServerHandler.INTANCE);

            }
        });

        // 6 开始绑定server
        // 通过调用sync同步方法阻塞直到绑定成功
        ChannelFuture channelFuture = serverBootstrap.bind().sync();
        log.info(" 服务器启动成功，监听端口: " +
                channelFuture.channel().localAddress());

        // 7 等待通道关闭的异步任务结束
        // 服务监听通道会一直等待通道关闭的异步任务结束
        ChannelFuture closeFuture = channelFuture.channel().closeFuture();

        closeFuture.sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        NettyEchoServer nettyEchoServer = new NettyEchoServer();
        nettyEchoServer.runServer();


    }



}
