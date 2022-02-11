package com.bu.firstdoc.zs.service;

import com.bu.firstdoc.zs.service.impl.BaseCakeMake;
import com.bu.firstdoc.zs.service.impl.BaseMake;
import com.bu.firstdoc.zs.service.impl.EggMake;
import com.bu.firstdoc.zs.service.impl.HamMake;

/**
 * @author haizhuangbu
 * @date 1:38 下午 2022/2/10
 * @mark Ts
 */
public class Ts {


    public static void main(String[] args) {
        BaseMake baseMake = new BaseMake(new HamMake(new HamMake(new EggMake(new BaseCakeMake()))));

        System.out.println("baseMake.getMsg() = " + baseMake.getMsg());

        System.out.println("baseMake.getPrice() = " + baseMake.getPrice());

        baseMake.doSomThing();
    }

}
