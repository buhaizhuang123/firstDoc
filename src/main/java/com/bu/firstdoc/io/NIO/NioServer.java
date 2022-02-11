package com.bu.firstdoc.io.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;

/**
 * @author haizhuangbu
 * @date 4:20 下午 2022/1/20
 * @mark NioServer
 */
public class NioServer {

    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        ByteBuffer buff = ByteBuffer.allocate(1024);
        try {
            serverSocketChannel = ServerSocketChannel.open();


            // 绑定端口
            serverSocketChannel.bind(new InetSocketAddress(8081));

            // 设置管道通信方式为异步
            serverSocketChannel.configureBlocking(false);

            serverSocketChannel.accept();
//
//            if (serverSocketChannel.read(buff) != 0) {
//                System.out.println("bufferedReader.readLine() = " + bufferedReader.readLine());
//            }
//            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getCause().getMessage());
        } finally {
            try {
                serverSocketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
