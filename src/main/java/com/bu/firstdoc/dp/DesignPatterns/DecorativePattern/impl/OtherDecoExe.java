package com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.impl;

import com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.DecoExe;

/**
 * @author haizhuangbu
 * @date 3:26 下午 2022/2/14
 * @mark OtherDecoExe
 */
public class OtherDecoExe extends BaseDecoExe {

    public OtherDecoExe(DecoExe exe) {
        super(exe);
    }

    @Override
    public String getMsg() {
        return super.getMsg() + "其他";
    }
}
