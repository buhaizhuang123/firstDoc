package com.bu.firstdoc.zs.zr.inter.impl;

import com.bu.firstdoc.zs.zr.inter.Handler;
import com.bu.firstdoc.zs.zr.inter.model.Member;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 4:56 下午 2022/2/10
 * @mark BHandler
 */
@Slf4j
public class BHandler extends Handler {
    @Override
    public void doHandler(Member member) {

        if ("Admin".equals(member.getRoleName())) {
            log.info("admin登录成功");
        }else{
            log.info("登录失败,角色权限不符合要求");
        }
        if (this.handler!=null) {
            this.handler.doHandler(member);
        }

    }
}
