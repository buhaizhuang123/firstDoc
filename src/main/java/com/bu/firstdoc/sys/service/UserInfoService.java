package com.bu.firstdoc.sys.service;

import com.bu.firstdoc.sys.model.UserInfo;

import java.util.List;

public interface UserInfoService {

    List<UserInfo> findAll(UserInfo userInfo);

}
