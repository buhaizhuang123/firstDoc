package com.bu.firstdoc.kafkaTs;

import com.bu.firstdoc.sys.model.UserInfo;

/**
 * @Author haizhuangbu
 * @Date
 * @Function
 * @Mark
 */
public class T1 {

    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo();
        UserInfo.getUserBirDate(userInfo);
        System.out.println(userInfo);
    }

}
