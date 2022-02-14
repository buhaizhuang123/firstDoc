package com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.ts;

import com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.impl.Base;
import com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.impl.BaseDecoExe;
import com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.impl.OtherDecoExe;

/**
 * @author haizhuangbu
 * @date 3:27 下午 2022/2/14
 * @mark Ts
 */
public class Ts {

    public static void main(String[] args) {
        BaseDecoExe baseDecoExe = new BaseDecoExe(new OtherDecoExe(new OtherDecoExe(new Base())));

        System.out.println(baseDecoExe.getMsg());
    }

}
