package com.bu.firstdoc.learnMore.server;

import com.bu.firstdoc.learnMore.server.impl.NettyChannelHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 2:16 下午 2022/1/27
 * @mark NioNettyEchoServer 服务器
 */
@Data
@Slf4j
public class NioNettyEchoServer {

    /**
     * @field port
     */
    private Integer port;

    private final ServerBootstrap bootstrap;;


    {
        bootstrap = new ServerBootstrap();
    }

    public void startServer(){
        NioEventLoopGroup boss = new NioEventLoopGroup(1);

        NioEventLoopGroup worker = new NioEventLoopGroup();
        try {
            bootstrap.group(worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new NettyChannelHandler());
                        }
                    });
            ;
            bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            bootstrap.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            ChannelFuture future = bootstrap.bind(port).sync();

            log.info("服务启动成功 。。。。。");
            future.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }

    }

    public NioNettyEchoServer(Integer port) {
        this.port = port;
    }

    public static void main(String[] args) {
        NioNettyEchoServer nioNettyEchoServer = new NioNettyEchoServer(8081);
        nioNettyEchoServer.startServer();
    }


}
