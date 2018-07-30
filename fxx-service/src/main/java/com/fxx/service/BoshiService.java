package com.fxx.service;

import javax.annotation.Resource;

import com.fxx.service.dao.BoshiMapper;
import com.fxx.utils.entity.Boshi;
import org.springframework.stereotype.Service;

/**
 * Created by FengXiaoxi on 2018/4/12.
 */
@Service
public class BoshiService {

  @Resource
  private BoshiMapper boshiMapper;


  public int insertSelective(Boshi record) {
    return boshiMapper.insertSelective(record);
  }


}
