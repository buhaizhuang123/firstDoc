package com.bu.firstdoc.zs.service.impl;

import com.bu.firstdoc.zs.service.CakeMake;

/**
 * @author haizhuangbu
 * @date 1:41 下午 2022/2/10
 * @mark BaseCakeMake
 */
public class BaseCakeMake implements CakeMake {


    @Override
    public String getMsg() {
        return "煎饼";
    }

    @Override
    public Integer getPrice() {
        return 8;
    }
}
