package com.bu.firstdoc.zs.zr.inter1.impl;

import com.bu.firstdoc.zs.zr.inter.model.Member;
import com.bu.firstdoc.zs.zr.inter1.Handler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 9:06 下午 2022/2/10
 * @mark LoginHandler
 */
@Slf4j
public class LoginHandler extends Handler {
    @Override
    public void doHandler(Member member) {
        if (Objects.nonNull(member)){
            log.info("======= 登录认证 =========");
            if (StringUtils.isNotBlank(member.getLoginName()) && StringUtils.isNotBlank(member.getLoginPass())){
                log.info("====== 登录成功 =========");
                this.handler.doHandler(member);
            }
        }else {
            log.info("登录失败");
        }

    }
}
