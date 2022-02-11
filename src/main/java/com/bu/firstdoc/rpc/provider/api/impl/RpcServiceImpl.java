package com.bu.firstdoc.rpc.provider.api.impl;

import com.bu.firstdoc.rpc.provider.api.IRpcService;

/**
 * @author haizhuangbu
 * @date 9:15 下午 2022/2/9
 * @mark RpcServiceImpl
 */
public class RpcServiceImpl implements IRpcService {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public int mult(int a, int b) {
        return a * b;
    }

    @Override
    public int div(int a, int b) {
        return a / b;
    }
}
