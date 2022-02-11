package com.bu.firstdoc.zs.ts;

import com.bu.firstdoc.zs.MeIn;

/**
 * @author haizhuangbu
 * @date 2:55 下午 2022/2/11
 * @mark Ts
 */
public class Ts {

    public static void main(String[] args) {
        MeIn<String> stringMeIn = new MeIn<>("1223");

        String data = stringMeIn.getData();

        MeIn<Object> stringMeIn1 = new MeIn<>(new Object());

        stringMeIn1.getData();
    }

}
