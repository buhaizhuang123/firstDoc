package com.bu.firstdoc.dp.DesignPatterns.service.impl;

import com.bu.firstdoc.dp.DesignPatterns.service.Options;
import com.bu.firstdoc.zs.zr.inter.model.Member;

import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 4:56 下午 2022/2/14
 * @mark SecOptions
 */
public class SecOptions extends DecoOptions {

    @Override
    public void option(Member member) {
        if (member == null) {
            member = new Member();
        }
        System.out.println("基本执行");
        member.setLoginName("admin");
        member.setLoginPass("123456");
        if (Objects.nonNull(this.options)) {
            super.options.option(member);
        }
    }
}
