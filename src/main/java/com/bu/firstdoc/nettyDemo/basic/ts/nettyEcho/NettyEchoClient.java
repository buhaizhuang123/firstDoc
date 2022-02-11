package com.bu.firstdoc.nettyDemo.basic.ts.nettyEcho;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author haizhuangbu
 * @date 11:47 上午 2022/1/26
 * @mark NettyEchoClient
 */
@Data
@Slf4j
public class NettyEchoClient {

    private int serverPort;

    private String ip;

    public NettyEchoClient(int serverPort, String ip) {
        this.serverPort = serverPort;
        this.ip = ip;
    }

    Bootstrap bootstrap = new Bootstrap();

    public void runClient()  {

        NioEventLoopGroup eventExecutor = new NioEventLoopGroup();
        try {
        // 设置反应轮询组
        bootstrap.group(eventExecutor);

        // 设置nio类型通道
        bootstrap.channel(NioSocketChannel.class);

        // 设置监听端口
        bootstrap.remoteAddress(ip,serverPort);

        // 设置通道参数
        bootstrap.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

        bootstrap.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel socketChannel) throws Exception {
                socketChannel.pipeline().addLast(NettyEchoClientHandler.INSTANCE);
            }
        });

        ChannelFuture connect = bootstrap.connect();

        connect.addListener((ChannelFuture channel)->{
            if (channel.isSuccess()){
                log.info("echoClient 客户端链接成功");
            }else {
                log.info("echoClient 客户端链接失败");
            }
        });


        connect.sync();


        Channel channel = connect.channel();

        Scanner sc = new Scanner(System.in);
        log.info("输入内容");
        while (sc.hasNext()) {
            String line = sc.next();
            byte[] bytes = line.getBytes(StandardCharsets.UTF_8);

            ByteBuf buffer = channel.alloc().buffer();
            buffer.writeBytes(bytes);
            channel.writeAndFlush(buffer);
            System.out.println("请输入发送内容");
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            eventExecutor.shutdownGracefully();
        }
    }

    public static void main(String[] args) {

        NettyEchoClient nettyEchoClient = new NettyEchoClient(8081, "localhost");
        nettyEchoClient.runClient();

    }

}
