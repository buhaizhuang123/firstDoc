package com.bu.firstdoc.firstDoc.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author haizhuangbu
 * @date 10:39 下午 2022/2/17
 * @mark UserInfoMapper
 */
@Mapper
public interface UserInfoMapper {

    List findAll();

}
