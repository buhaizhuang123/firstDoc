package com.bu.firstdoc.sys.service.impl;

import com.bu.firstdoc.common.point.anno.RedisSys;
import com.bu.firstdoc.sys.dao.UserInfoDao;
import com.bu.firstdoc.sys.model.UserInfo;
import com.bu.firstdoc.sys.service.UserInfoService;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 3:11 下午 2022/1/13
 * @mark UserInfoService
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoDao userInfoDao;

    @Override
    @RedisSys
    public List<UserInfo> findAll(UserInfo userInfo) {
        List<UserInfo> all = userInfoDao.findAll(userInfo, new RowBounds(0, 10));
        return all;
    }
}
