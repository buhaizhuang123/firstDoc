package com.bu.firstdoc.io.NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author haizhuangbu
 * @date 4:20 下午 2022/1/20
 * @mark NioClient
 */
public class NioClient {

    public static void main(String[] args) {
        SocketChannel socketChannel = null;
        ByteBuffer buff = ByteBuffer.allocate(1024);
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            socketChannel.connect(new InetSocketAddress("localhost", 8081));

            while (!socketChannel.finishConnect()) {

            }
            System.out.println("buff = " + " 请输入数据");
            Scanner sc = new Scanner(System.in);
            buff.put(sc.nextLine().getBytes(StandardCharsets.UTF_8));
            socketChannel.write(buff);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socketChannel.shutdownOutput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
