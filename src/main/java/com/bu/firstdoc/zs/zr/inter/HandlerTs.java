package com.bu.firstdoc.zs.zr.inter;

import com.bu.firstdoc.zs.zr.inter.impl.AHandler;
import com.bu.firstdoc.zs.zr.inter.impl.BHandler;
import com.bu.firstdoc.zs.zr.inter.model.Member;

/**
 * @author haizhuangbu
 * @date 4:58 下午 2022/2/10
 * @mark HandlerTs
 */
public class HandlerTs {

    public static void main(String[] args) {
        Handler aHandler = new AHandler();
        aHandler.nextHandler(new BHandler());
        Member member = new Member();
        member.setLoginName("admin");
        member.setLoginPass("admin");
        member.setRoleName("Admin");
        aHandler.doHandler(member);

        Handler.Builder builder = new Handler.Builder();
        Handler build = builder
                .addHandler(new AHandler())
                .addHandler(new BHandler())
                .build();
        build.doHandler(member);

    }


}
