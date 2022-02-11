package com.bu.firstdoc.io.channel;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author haizhuangbu
 * @date 2:04 下午 2022/1/19
 * @mark SocketChannelTs
 */
public class SocketChannelTs {
    public static void main(String[] args) {
        // 获取拼接字管道
        try {
            SocketChannel socketChannel = SocketChannel.open();
            // 设置管道为非阻塞
            socketChannel.configureBlocking(false);
            // 对服务器的IP 和 PORT 发起链接
            socketChannel.connect(new InetSocketAddress("127.0.0.1", 8081));

            while (!socketChannel.finishConnect()) {
                // 不断自旋等待链接
                System.out.println("======== loading =========");
            }
            // 读取数据
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer);

            buffer.flip();

            socketChannel.write(buffer);

            // 向对方发起关闭请求
            socketChannel.shutdownOutput();

            // 关闭套接字管道
            IOUtils.closeQuietly(socketChannel);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
