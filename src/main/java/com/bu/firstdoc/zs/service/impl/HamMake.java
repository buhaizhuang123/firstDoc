package com.bu.firstdoc.zs.service.impl;

import com.bu.firstdoc.zs.service.CakeMake;

/**
 * @author haizhuangbu
 * @date 1:53 下午 2022/2/10
 * @mark HamMake
 */
public class HamMake extends BaseMake {

    public HamMake(CakeMake cakeMake) {
        super(cakeMake);
    }


    @Override
    public String getMsg() {
        return super.getMsg() +"+"+ "火腿肠";
    }

    @Override
    public Integer getPrice() {
        return super.getPrice() + 2;
    }
}
