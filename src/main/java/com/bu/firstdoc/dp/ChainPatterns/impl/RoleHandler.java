package com.bu.firstdoc.dp.ChainPatterns.impl;

import com.bu.firstdoc.dp.ChainPatterns.Handler;
import com.bu.firstdoc.zs.zr.inter.model.Member;
import org.junit.Assert;

import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 3:52 下午 2022/2/14
 * @mark RoleHandler
 */
public class RoleHandler extends Handler {

    @Override
    public void doSome(Member member) {
        if (member == null){
            return;
        }
        if ("Admin".equals(member.getRoleName())  ){
            System.out.println("权限校验成功");
        }
        if (Objects.nonNull(this.handler)) {
            this.handler.doSome(member);
        }
    }
}
