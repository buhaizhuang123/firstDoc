package com.bu.firstdoc.io.channel.UDP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;

/**
 * @author haizhuangbu
 * @date 2:50 下午 2022/1/19
 * @mark UDPServer
 */
public class UDPServer {

    public void receive() throws IOException {
        DatagramChannel datagramChannel = DatagramChannel.open();

        // 设置非阻塞
        datagramChannel.configureBlocking(false);

        // 绑定监听地址
        datagramChannel.bind(new InetSocketAddress("localhost", 8081));

        // 开启一个通道选择器
        Selector selector = Selector.open();

        datagramChannel.register(selector, SelectionKey.OP_READ);

        while (selector.select() > 0) {
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();

            ByteBuffer buff = ByteBuffer.allocate(1024);

            while (iterator.hasNext()) {
                SelectionKey selectionKey = iterator.next();

                if (selectionKey.isReadable()) {
                    SocketAddress client = datagramChannel.receive(buff);


                    buff.flip();
                    System.out.println(new String(buff.array(), 0, buff.limit()));
                    buff.clear();
                }
                iterator.remove();
            }
        }
        selector.close();
        datagramChannel.close();

    }

    public static void main(String[] args) throws IOException {
        UDPServer udpServer = new UDPServer();
        udpServer.receive();
    }

}
