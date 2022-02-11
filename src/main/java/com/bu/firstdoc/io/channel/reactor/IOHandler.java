package com.bu.firstdoc.io.channel.reactor;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author haizhuangbu
 * @date 4:18 下午 2022/1/19
 * @mark IOHandler
 */
public class IOHandler implements Runnable {


    final SocketChannel channel;

    final SelectionKey selectionKey;

    public IOHandler(SocketChannel channel, SelectionKey selectionKey) {
        this.channel = channel;
        this.selectionKey = selectionKey;
    }

    public IOHandler(Selector selector, SocketChannel channel) throws IOException {
        this.channel = channel;
        channel.configureBlocking(false);
        selectionKey = channel.register(selector, 0);
        selectionKey.attach(this);
        selectionKey.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
    }

    @Override
    public void run() {
        System.out.println("============ 执行 ============");
    }
}
