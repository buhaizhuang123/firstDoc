package com.bu.firstdoc.jol.ts;


import java.util.concurrent.CountDownLatch;

/**
 * @author haizhuangbu
 * @date 10:21 上午 2022/1/18
 * @mark ObjectLock
 */
public class ObjectLock {

    /**
     * amount  整型字段占用四字节
     */
    private Integer amount = 0;

    public void increase() {
        synchronized (this) {
            amount++;
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(0);

        long count = countDownLatch.getCount();
        System.out.println(count);

    }


}
