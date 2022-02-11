package com.bu.firstdoc.nettyDemo.second.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author haizhuangbu
 * @date 10:02 上午 2022/1/27
 * @mark server
 */
public class NettyServer {

    /**
     * 工厂对象
     */
    private ServerBootstrap bootstrap = new ServerBootstrap();

    public void runServer(){
        // 线程组
        NioEventLoopGroup boss = new NioEventLoopGroup(1);
        // 处理线程
        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
        bootstrap.group(boss,worker);
        // 设置否阻塞channel
        bootstrap.channel(NioServerSocketChannel.class);

        bootstrap.option(ChannelOption.SO_KEEPALIVE, true);
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
        bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                channel.pipeline().addLast(ServerHandler.INSTANCE);
            }
        });

        ChannelFuture future = bootstrap.bind(8081).sync();
        future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }


    }

    public static void main(String[] args) {
        NettyServer nettyServer = new NettyServer();
        nettyServer.runServer();
    }
}
