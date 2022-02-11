package com.bu.firstdoc.io.channel.reactor;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

/**
 * @author haizhuangbu
 * @date 3:49 下午 2022/1/19
 * @mark EchoHandler
 */
public class EchoHandler implements Runnable {

    final SocketChannel channel;
    final SelectionKey sk;
    final ByteBuffer buffer = ByteBuffer.allocate(1024);
    /**
     * 处理器实例的状态 发送和接受 一个链接对应一个处理器实例
     */
    static final int RECIEVING = 0, SENDING = 1;
    int state = RECIEVING;

    public EchoHandler(Selector selector, SocketChannel channel) throws ClosedChannelException {
        this.channel = channel;
        // 取得选择键，在设置感兴趣的IO事件
        sk = channel.register(selector, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        selector.wakeup();
    }


    @Override
    public void run() {
        if (state == SENDING) {
            try {
                channel.write(buffer);
                buffer.clear();
                sk.interestOps(SelectionKey.OP_READ);
                state = RECIEVING;
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (state == RECIEVING) {
            int length = 0;
            try {
                while ((length = channel.read(buffer)) > 0) {
                    System.out.println(new String(buffer.array(), 0, length));
                }
                buffer.flip();
                sk.interestOps(SelectionKey.OP_WRITE);
                state = SENDING;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
