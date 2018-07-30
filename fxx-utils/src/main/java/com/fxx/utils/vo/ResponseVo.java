package com.fxx.utils.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by FengXiaoxi on 2018/4/12.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseVo<T> {

  // 成功标记
  private boolean success;
  // 提示信息
  private String msg;
  // 错误码
  private int errorCode;
  // 返回的具体数据
  private T data;
}
