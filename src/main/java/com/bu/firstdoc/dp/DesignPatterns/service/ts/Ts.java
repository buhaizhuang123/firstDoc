package com.bu.firstdoc.dp.DesignPatterns.service.ts;

import com.bu.firstdoc.dp.DesignPatterns.service.impl.RoleOptions;
import com.bu.firstdoc.dp.DesignPatterns.service.impl.SecOptions;
import com.bu.firstdoc.zs.zr.inter.model.Member;

/**
 * @author haizhuangbu
 * @date 5:04 下午 2022/2/14
 * @mark Ts
 */
public class Ts {


    public static void main(String[] args) {
        SecOptions secOptions = new SecOptions();
        secOptions.setOptions(new RoleOptions());
        Member member = new Member();
        secOptions.option(member);
        System.out.println(member);
    }

}
