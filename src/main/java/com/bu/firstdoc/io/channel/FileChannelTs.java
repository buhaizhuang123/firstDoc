package com.bu.firstdoc.io.channel;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author haizhuangbu
 * @date 11:35 上午 2022/1/19
 * @mark FileChannelTs
 */
public class FileChannelTs {

    public void createFiltChannel() {
        try {
            FileInputStream fileInputStream = new FileInputStream("");
            // 获取文件通道
            FileChannel channel = fileInputStream.getChannel();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("");
            FileChannel channel = fileOutputStream.getChannel();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void otherCreate() {
        try {
            // 创建可读可写的通道
            RandomAccessFile rw = new RandomAccessFile("fileName.txt", "rw");
            // 通道
            FileChannel channel = rw.getChannel();

            // 创建缓存区
            ByteBuffer buffer = ByteBuffer.allocate(100);

            int length = -1;

            while ((length = channel.read(buffer)) != -1) {
                // 业务处理
            }

            buffer.flip();

            int maxLength = 0;

            while ((maxLength = channel.write(buffer)) != 0) {
                System.out.println("写入的字节数" + maxLength);
            }

            // 关闭通道
            channel.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
