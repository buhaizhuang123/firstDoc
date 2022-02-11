package com.bu.firstdoc.ioTR;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.*;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

/**
 * @author haizhuangbu
 * @date 11:34 上午 2022/1/23
 * @mark Ts
 */
@Slf4j
public class Ts {


    /**
     * 测试buffer
     */
    @Test
    public void tsBuffer() {
        // 默认为写入状态
        IntBuffer intBuffer = IntBuffer.allocate(1024);

        for (int i = 0; i < 10; i++) {
            intBuffer.put(i);
        }
        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        System.out.println("intBuffer.capacity() = " + intBuffer.capacity());
        System.out.println("intBuffer.position() 位置 = " + intBuffer.position());

        // 转换为读取状态
        intBuffer.flip();


        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        System.out.println("intBuffer.capacity() = " + intBuffer.capacity());
        System.out.println("intBuffer.position() = " + intBuffer.position());

        //  遍历当前buffer
        for (intBuffer.position(); intBuffer.position() < intBuffer.limit(); ) {
            System.out.println("intBuffer.get(i) = " + intBuffer.get());
        }


    }


    /**
     * 文件管道必须为阻塞
     */
    @Test
    public void testFileChannel() throws IOException {

        // 1. fileStream.getChannel()

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);

            fileInputStream = new FileInputStream("src/main/resources/1.txt");
            // 输入管道
            FileChannel in = fileInputStream.getChannel();

            fileOutputStream = new FileOutputStream("src/main/resources/ons.txt");
            // 输出管道
            FileChannel out = fileOutputStream.getChannel();

            // 读取文件信息到buffer中
            while (in.read(buffer) != -1) {
                System.out.println("buffer.position() = " + buffer.position());
                System.out.println("buffer.limit() = " + buffer.limit());
                System.out.println("buffer.capacity() = " + buffer.capacity());
                // 将buffer的模式转换为输出
                buffer.flip();

                System.out.println("buffer.position() = " + buffer.position());
                System.out.println("buffer.limit() = " + buffer.limit());
                System.out.println("buffer.capacity() = " + buffer.capacity());

            }

            int length = 0;
            while ((length = out.write(buffer)) != 0) {
                System.out.println(length);
            }
            in.close();
            out.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {

            fileInputStream.close();
            fileOutputStream.close();

        }
    }


    /**
     * 测试第二种文件传输方式
     */
    @Test
    public void testFileChannel2() throws IOException {
        RandomAccessFile randomAccessFile = null;
        FileChannel fileChannel = null;
        try {

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

            randomAccessFile = new RandomAccessFile("src/main/resources/1.txt", "rw");

            fileChannel = randomAccessFile.getChannel();

            // 写入数据
            while (fileChannel.read(byteBuffer) != -1) {
                System.out.println("byteBuffer.limit() = " + byteBuffer.limit());
                System.out.println("byteBuffer.capacity() = " + byteBuffer.capacity());
                System.out.println("byteBuffer.position() = " + byteBuffer.position());
            }
            byteBuffer.flip();

            while (fileChannel.write(byteBuffer) != 0) {
                System.out.println("byteBuffer.limit() = " + byteBuffer.limit());
                System.out.println("byteBuffer.capacity() = " + byteBuffer.capacity());
                System.out.println("byteBuffer.position() = " + byteBuffer.position());
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            fileChannel.close();
            randomAccessFile.close();
        }


    }

    /**
     * 测试socketChannel
     */
    @Test
    public void testSocketChannel() throws IOException {
        ServerSocketChannel serverSocketChannel = null;
        try {

            // 选择器
            Selector selector = Selector.open();

            ByteBuffer buffer = ByteBuffer.allocate(1024);

            // 服务端
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8991));
            // 设置为非阻塞
            serverSocketChannel.configureBlocking(false);

            log.info("===== 服务器启动成功 ========");
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.select() > 0) {
                Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();

                while (selectionKeys.hasNext()) {
                    SelectionKey selectionKey = selectionKeys.next();
                    // 判断状态
                    if (selectionKey.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();

                        socketChannel.configureBlocking(false);

                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (selectionKey.isReadable()) {
                        SocketChannel channel = (SocketChannel) selectionKey.channel();
                        while (channel.read(buffer) > 0) {
                            buffer.flip();
                            log.info(new String(buffer.array()));
                            buffer.clear();
                        }
                        channel.close();
                    }

                }
                selectionKeys.remove();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            serverSocketChannel.close();
        }
    }

    @Test
    public void testServerSocketChannel() {
        SocketChannel socketChannel = null;
        try {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel = SocketChannel.open(new InetSocketAddress("localhost", 8991));
            socketChannel.configureBlocking(false);
            while (!socketChannel.finishConnect()) {
            }
            log.info("====== 客户端链接成功 ===========");

            log.info("=======   请输入信息   ==========");
            buffer.put("Hello World".getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            socketChannel.write(buffer);
            socketChannel.shutdownOutput();
            socketChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
