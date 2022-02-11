package com.bu.firstdoc.io.buffer;

import lombok.extern.slf4j.Slf4j;

import java.nio.IntBuffer;

/**
 * @author haizhuangbu
 * @date 11:05 上午 2022/1/19
 * @mark IntTs
 */
@Slf4j
public class IntTs {

    public IntBuffer intBuffer = null;


    public void BufferInit() {
        // 初始化 20个Int 内存的缓存
        intBuffer = IntBuffer.allocate(20);
        // 游标位置
        System.out.println("intBuffer.position() = " + intBuffer.position());
        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        System.out.println("intBuffer.capacity() = " + intBuffer.capacity());
    }


    /**
     * 当前buffer 为写入状态
     */
    public void testPut() {
        for (int i = 0; i < 10; i++) {
            intBuffer.put(i);
        }
        System.out.println("afterPutTs = ");
        // 游标位置 写入十个元素之后 游标状态到10
        System.out.println("intBuffer.position() = " + intBuffer.position());
        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        System.out.println("intBuffer.capacity() = " + intBuffer.capacity());
    }

    /**
     * 切换buffer状态为读取状态
     */
    public void testGet() {
        // 切换状态
        intBuffer.flip();
        System.out.println("======== 读取===========");
        // 游标位置
        System.out.println("intBuffer.position() = " + intBuffer.position());
        System.out.println("intBuffer.limit() = " + intBuffer.limit());
        System.out.println("intBuffer.capacity() = " + intBuffer.capacity());
    }

}
