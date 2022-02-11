package com.bu.firstdoc.io.channel.UDP;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author haizhuangbu
 * @date 2:28 下午 2022/1/19
 * @mark UDPClient
 */
public class UDPClient {

    public void send() throws IOException {
        // 开启UDP 套接字
        DatagramChannel dChannel = DatagramChannel.open();

        // 设置非阻塞
        dChannel.configureBlocking(false);

        ByteBuffer buff = ByteBuffer.allocate(1024);

        Scanner scanner = new Scanner(System.in);

        System.out.println("====== UDP 客户端启动成功 ==========");
        System.out.println("====== 请输入发送内容 ==========");
        while (scanner.hasNext()) {
            String info = scanner.nextLine();
            // 写入数据
            buff.put((System.currentTimeMillis() + " >>> " + info).getBytes(StandardCharsets.UTF_8));

            // 转换  读取数据
            buff.flip();
            // 将数据发送到 localhost:8081
            dChannel.send(buff, new InetSocketAddress("localhost", 8081));
            buff.clear();
        }
    }

    public static void main(String[] args) {
        try {
            new UDPClient().send();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
