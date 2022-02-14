package com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.impl;

import com.bu.firstdoc.dp.DesignPatterns.DecorativePattern.DecoExe;

/**
 * @author haizhuangbu
 * @date 3:31 下午 2022/2/14
 * @mark Base
 */
public class Base implements DecoExe {
    @Override
    public String getMsg() {
        return "基本";
    }
}
