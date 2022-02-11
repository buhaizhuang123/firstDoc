package com.bu.firstdoc.zs.zsAndZr.ts;

import com.bu.firstdoc.zs.service.CakeMake;
import com.bu.firstdoc.zs.zsAndZr.Car;
import com.bu.firstdoc.zs.zsAndZr.One;
import com.bu.firstdoc.zs.zsAndZr.impl.AWMCar;
import com.bu.firstdoc.zs.zsAndZr.impl.BYDCar;

/**
 * @author haizhuangbu
 * @date 10:10 上午 2022/2/11
 * @mark CarTs
 */
public class CarTs {

    public static void main(String[] args) {

        Car.Builder builder = new Car.Builder();
        Car car = builder.addCar(new AWMCar())
                .addCar(new BYDCar())
                .addCar(new AWMCar())
                .addCar(new AWMCar())
                .build();
        System.out.println("car.loadAcce() = " + car.loadAcce());
/**
        new CarTs().addMake(()->{

        });*/


    }

    public void addMake(One one){

    }


}
