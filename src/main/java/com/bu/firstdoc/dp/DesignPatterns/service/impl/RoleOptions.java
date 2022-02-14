package com.bu.firstdoc.dp.DesignPatterns.service.impl;

import com.bu.firstdoc.dp.DesignPatterns.service.Options;
import com.bu.firstdoc.zs.zr.inter.model.Member;

import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 5:01 下午 2022/2/14
 * @mark RoleOptions
 */
public class RoleOptions extends DecoOptions {

    @Override
    public void option(Member member) {
        if (member == null) {
            member = new Member();
        }
        System.out.println("角色执行");
        member.setRoleName("Admin");
        if (Objects.nonNull(this.options)) {
            super.options.option(member);
        }
    }
}
