package com.bu.firstdoc.afLoan.impl;

import com.bu.firstdoc.afLoan.AfHandler;

import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 9:57 上午 2022/1/25
 * @mark OneHandler
 */
public class TwoHandler extends AfHandler {


    @Override
    public void handleRequest(String req) {
        if ("two".equals(req)) {
            System.out.println("two 执行");
        } else if (Objects.nonNull(getAfHandler())) {
            getAfHandler().handleRequest(req);
        } else {
            System.out.println("找不到执行流程");
        }
    }
}
