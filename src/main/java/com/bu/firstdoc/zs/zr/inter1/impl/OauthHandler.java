package com.bu.firstdoc.zs.zr.inter1.impl;

import com.bu.firstdoc.zs.zr.inter.model.Member;
import com.bu.firstdoc.zs.zr.inter1.Handler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author haizhuangbu
 * @date 9:13 下午 2022/2/10
 * @mark OauthHandler
 */
@Slf4j
public class OauthHandler extends Handler {
    @Override
    public void doHandler(Member member) {
        log.info("====== 权限认证 =========");
        if ("Admin".equals(member.getRoleName())){
            log.info("======= 权限认证成功,Success ======= ");
        }else{
            log.info(" ========== 权限认证失败 Fail =========");
        }
    }
}
