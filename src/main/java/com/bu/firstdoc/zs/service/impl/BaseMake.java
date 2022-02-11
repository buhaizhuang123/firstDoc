package com.bu.firstdoc.zs.service.impl;

import com.bu.firstdoc.zs.service.CakeMake;

/**
 * @author haizhuangbu
 * @date 1:47 下午 2022/2/10
 * @mark EggMake
 */
public class BaseMake implements CakeMake {

    private CakeMake cakeMake;

    private String msg;

    private Integer price;

    public BaseMake(CakeMake cakeMake) {
        this.cakeMake = cakeMake;
    }

    @Override
    public String getMsg() {
        msg = this.cakeMake.getMsg();
        return msg;
    }

    @Override
    public Integer getPrice() {
        price = this.cakeMake.getPrice();
        return price ;
    }

    public void doSomThing(){
        System.out.println(msg + price);
    }
}
