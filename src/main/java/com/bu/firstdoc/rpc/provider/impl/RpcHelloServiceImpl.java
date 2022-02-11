package com.bu.firstdoc.rpc.provider.impl;

import com.bu.firstdoc.rpc.provider.IRpcHelloService;

/**
 * @author haizhuangbu
 * @date 9:14 下午 2022/2/9
 * @mark RpcHelloServiceImpl
 */
public class RpcHelloServiceImpl implements IRpcHelloService {
    @Override
    public String sayHello(String name) {
        return  name + "Hello" + "!";
    }
}
