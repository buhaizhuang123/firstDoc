package com.bu.demo;

import com.bu.firstdoc.sys.model.UserInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author haizhuangbu
 * @date 2022/2/28 2:30 下午
 * @mark Ts
 */
public class Ts {

    @Test
    public void lombda1() {
        ArrayList<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setUserId("1");
        userInfo1.setUserInfoData("123");
        userInfos.add(userInfo1);
        Map<String, String> collect = userInfos.stream().collect(Collectors.toMap(userInfo -> userInfo.getUserId(), userInfo -> userInfo.getUserInfoData()));
        collect.forEach((k, v) -> System.out.println(k + v));
        System.out.println(Runtime.getRuntime().maxMemory() / 1000 / 1000 + "M");

    }



}
