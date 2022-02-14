package com.bu.firstdoc.dp.ChainPatterns.ts;

import com.bu.firstdoc.dp.ChainPatterns.Handler;
import com.bu.firstdoc.dp.ChainPatterns.impl.ChainHandler;
import com.bu.firstdoc.dp.ChainPatterns.impl.ChainPatternsImpl;
import com.bu.firstdoc.dp.ChainPatterns.impl.ChainPatternsSec;
import com.bu.firstdoc.dp.ChainPatterns.impl.RoleHandler;
import com.bu.firstdoc.zs.zr.inter.model.Member;

/**
 * @author haizhuangbu
 * @date 3:43 下午 2022/2/14
 * @mark Ts
 */
public class Ts {

    public static void main(String[] args) {
        ChainPatternsImpl chainPatterns = new ChainPatternsImpl();
        ChainPatternsSec chainPatterns1 = new ChainPatternsSec();
        chainPatterns.addChain(chainPatterns1);
        chainPatterns.doSomeThing();

        ChainHandler chainHandler = new ChainHandler();
        chainHandler.nextHandler(new RoleHandler());
        Member member = new Member();
        member.setRoleName("Admin");
        member.setLoginName("admin");
        member.setLoginPass("123456");
        chainHandler.doSome(member);

        Handler.Builder builder = new Handler.Builder();
        builder.addHandler(new ChainHandler())
                .addHandler(new RoleHandler())
                .addHandler(new RoleHandler())
                .addHandler(new RoleHandler());
        Handler build = builder.build();

        build.doSome(member);

    }

}
