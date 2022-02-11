package com.bu.firstdoc.io.channel.reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author haizhuangbu
 * @date 3:41 下午 2022/1/19
 * @mark EchoServerReactor
 */
public class EchoServerReactor implements Runnable {

    private Selector selector;

    private ServerSocketChannel serverSocketChannel;

    public EchoServerReactor(Selector selector, ServerSocketChannel serverSocketChannel) {
        this.selector = selector;
        this.serverSocketChannel = serverSocketChannel;
    }

    EchoServerReactor() throws IOException {
        selector = Selector.open();
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("localhost", 8081));
        // 注册事件
        SelectionKey key = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 将执行对象注册到selectKey中
        key.attach(new AcceptorHandler());
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey sk = iterator.next();
                    dispatch(sk);
                }
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void dispatch(SelectionKey sk) {
        Runnable handler = (Runnable) sk.attachment();
        if (handler != null) {
            handler.run();
        }
    }


    class AcceptorHandler implements Runnable {

        @Override
        public void run() {
            // 保持链接
            try {
                SocketChannel channel = serverSocketChannel.accept();
                if (channel != null) {
                    new EchoHandler(selector, channel);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new Thread(new EchoServerReactor()).start();
    }

}
