package com.bu.firstdoc.zs.zsAndZr.impl;

import com.bu.firstdoc.zs.zsAndZr.Car;

/**
 * @author haizhuangbu
 * @date 10:00 上午 2022/2/11
 * @mark AWMCar
 */
public class AWMCar extends Car  {


    @Override
    public String loadAcce() {
        if (this.car != null) {
            return car.loadAcce()+ " " + "宝马";
        }
        return "宝马";
    }

}
