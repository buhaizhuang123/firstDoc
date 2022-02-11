package com.bu.firstdoc.nettyDemo.basic;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author haizhuangbu
 * @date 11:16 上午 2022/1/20
 * @mark NettyDiscardServer
 */
public class NettyDiscardServer {

    private final int serverPort;

    ServerBootstrap b = new ServerBootstrap();

    public NettyDiscardServer(int serverPort) {
        this.serverPort = serverPort;
    }

    public void runServer() {

        // 创建反应器轮询组 Nio 事件轮询
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {

            // 1. 设置反应器轮询组
            b.group(boss, worker);

            // 2. 设置NIO类型通道
            b.channel(NioServerSocketChannel.class);

            // 3. 设置监听端口
            b.bind(serverPort);

            // 4. 设置通道参数
            b.option(ChannelOption.SO_KEEPALIVE, true);

            // 5. 装配子通道流水线
            b.childHandler(new ChannelInitializer<SocketChannel>() {
                // 有连接到达时会创建一个通道
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    // 流水线的职责 ： 负责管理通道中的处理器
                    // 向子通道(传输通道) 流水线添加一个处理器
                    socketChannel.pipeline().addLast(new NettyDiscardHandler());
                }

            });

            // 6. 开始绑定服务
            // 调用sync 方法阻塞服务 直到绑定成功
            ChannelFuture sync = b.bind().sync();
            System.out.println("sync.channel().localAddress() = " + sync.channel().localAddress());
            // 7. 等待通道关闭 异步任务结束
            // 服务监听通道会一直等待通道关闭 异步任务结束
            ChannelFuture channelFuture = sync.channel().closeFuture();
            channelFuture.sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyDiscardServer(8081).runServer();
    }

}
