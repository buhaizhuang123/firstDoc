package com.bu.firstdoc.klin.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;
/**
 * @author haizhuangbu
 */
@Mapper
public interface DictMapper {

    List findAll();

}
