package com.bu.firstdoc.zs.zr.inter1.ts;

import com.bu.firstdoc.zs.zr.inter.model.Member;
import com.bu.firstdoc.zs.zr.inter1.Handler;
import com.bu.firstdoc.zs.zr.inter1.impl.LoginHandler;
import com.bu.firstdoc.zs.zr.inter1.impl.OauthHandler;

/**
 * @author haizhuangbu
 * @date 9:15 下午 2022/2/10
 * @mark HandlerTs
 */
public class HandlerTs {

    public static void main(String[] args) {

        Handler.Builder builder = new Handler.Builder();
        Member member = new Member();
        member.setLoginName("admin");
        member.setLoginPass("admin");
        member.setRoleName("Admin");
        Handler build = builder.addHandler(new LoginHandler())
                .addHandler(new OauthHandler()).build();

        build.doHandler(member);

    }

}
