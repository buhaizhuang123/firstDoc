package com.bu.firstdoc.dp.ChainPatterns.impl;

import com.bu.firstdoc.dp.ChainPatterns.Handler;
import com.bu.firstdoc.zs.zr.inter.model.Member;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author haizhuangbu
 * @date 3:51 下午 2022/2/14
 * @mark ChainHandler
 */
public class ChainHandler extends Handler {


    @Override
    public void doSome(Member member) {

        if (member != null && StringUtils.isNotBlank(member.getLoginName()) && StringUtils.isNotBlank(member.getLoginPass())){
            System.out.println("登录成功");

        } else {
            System.out.println("登录失败");
            return;
        }

        if (Objects.nonNull(this.handler)){
            this.handler.doSome(member);
        }

    }

}
