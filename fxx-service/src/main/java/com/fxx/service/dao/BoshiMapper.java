package com.fxx.service.dao;

import java.util.Date;

import com.fxx.utils.entity.Boshi;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BoshiMapper {

  int deleteByPrimaryKey(Date id);

  int insertSelective(Boshi record);

  Boshi selectByPrimaryKey(Date id);

  int updateByPrimaryKeySelective(Boshi record);

}