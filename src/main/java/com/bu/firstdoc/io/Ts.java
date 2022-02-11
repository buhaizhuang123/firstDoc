package com.bu.firstdoc.io;

import com.bu.firstdoc.io.channel.copy.FileCopy;

/**
 * @author haizhuangbu
 * @date 11:04 上午 2022/1/19
 * @mark Ts
 */
public class Ts {

    public static void main(String[] args) {
//        IntTs intTs = new IntTs();
//        // buffer 初始化
//        intTs.BufferInit();
//        // buffer 写入
//        intTs.testPut();
//        // buffer 读取
//        intTs.testGet();
        FileCopy.nioCopySourceFile();
    }
}
