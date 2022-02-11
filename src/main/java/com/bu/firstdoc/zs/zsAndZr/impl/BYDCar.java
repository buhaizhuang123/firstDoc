package com.bu.firstdoc.zs.zsAndZr.impl;

import com.bu.firstdoc.zs.zsAndZr.Car;

/**
 * @author haizhuangbu
 * @date 10:03 上午 2022/2/11
 * @mark BYDCar
 */
public class BYDCar extends Car {
    @Override
    public String loadAcce() {
        if (this.car != null) {
            return car.loadAcce()+ " " + "BYD";
        }
        return "BYD";
    }
}
