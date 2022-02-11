package com.bu.firstdoc.zs.zr.inter.impl;

import com.bu.firstdoc.zs.zr.inter.Handler;
import com.bu.firstdoc.zs.zr.inter.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 * @author haizhuangbu
 * @date 4:45 下午 2022/2/10
 * @mark AHandler
 */
@Slf4j
public class AHandler extends Handler {


    @Override
    public void doHandler(Member member) {

        if (member != null) {
            System.out.println("======== 校验用户 ==========");
            if (StringUtils.isNotBlank(member.getLoginName()) && StringUtils.isNotBlank(member.getLoginPass())) {
                System.out.println("登录成功");
            }else {
                log.info("用户名或密码不能为空");
            }
        }else {
            log.error("用户登录失败,原因:{},{}",member,"不能为空");
            return;
        }
        this.handler.doHandler(member);
    }
}
