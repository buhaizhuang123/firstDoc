package com.bu.firstdoc.dp.DesignPatterns.service.impl;

import com.bu.firstdoc.dp.DesignPatterns.service.Options;
import com.bu.firstdoc.zs.zr.inter.model.Member;

/**
 * @author haizhuangbu
 * @date 4:52 下午 2022/2/14
 * @mark DecoOptions 配置登录人
 */
public class DecoOptions implements Options {

    protected Options options;

    public void  setOptions(Options options){
        this.options = options;
    }

    @Override
    public void option(Member member) {
        this.options.option(member);
    }
}
