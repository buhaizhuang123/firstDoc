package com.bu.firstdoc.zs.service.impl;

import com.bu.firstdoc.zs.service.CakeMake;

/**
 * @author haizhuangbu
 * @date 1:50 下午 2022/2/10
 * @mark EggMake
 */
public class EggMake extends BaseMake  {
    public EggMake(CakeMake cakeMake) {
        super(cakeMake);
    }

    @Override
    public Integer getPrice() {
        return super.getPrice() + 1;
    }

    @Override
    public String getMsg() {
        return super.getMsg() +"+"+ "鸡蛋";
    }
}
