package com.bu.firstdoc.io.channel;

import java.io.IOException;
import java.net.ServerSocket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @author haizhuangbu
 * @date 2:09 下午 2022/1/19
 * @mark ServerSocketChannelTs
 */
public class ServerSocketChannelTs {


    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8081);
        ServerSocketChannel serverSocketChannel = serverSocket.getChannel();

        SocketChannel socketChannel = serverSocketChannel.accept();

        socketChannel.configureBlocking(false);



    }
}
