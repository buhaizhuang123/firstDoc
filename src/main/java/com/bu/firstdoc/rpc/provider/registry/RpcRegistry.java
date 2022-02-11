package com.bu.firstdoc.rpc.provider.registry;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

/**
 * @author haizhuangbu
 * @date 9:17 下午 2022/2/9
 * @mark RpcRegistry
 */
public class RpcRegistry {

    private int port;

    public RpcRegistry(int port){
        this.port = port;
    }

    public void start(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();

                            // 自定义协议解码器
                            pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4))
                                    .addLast(new LengthFieldPrepender(4))
                                    .addLast("encoder",new ObjectEncoder())
                                    .addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)))
                                    .addLast(new RegistryHandler())
                            ;

                        }
                    }).option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture future = bootstrap.bind(port).sync();

            System.out.println("Rpc Registry start listen at"+port);
            future.channel().closeFuture().sync();

        }catch (Exception e){
                bossGroup.shutdownGracefully();
                workerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) {
        new RpcRegistry(8080).start();
    }


}
