package com.bu.firstdoc.sys.dao;

import com.bu.firstdoc.sys.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

// @Mapper
public interface UserInfoDao {

    /**
     * 查询
     */
    List<UserInfo> findAll(@Param("userInfo") UserInfo userInfo, RowBounds rowBounds);

    /**
     * 保存
     */
    Integer saveUser(UserInfo userInfo);

    /**
     * 删除
     */
    Integer delUser(@Param("userId") String userId);

    /**
     * 修改
     */
    Integer updateUser(UserInfo userInfo);

}
