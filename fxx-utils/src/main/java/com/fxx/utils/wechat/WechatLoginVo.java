package com.fxx.utils.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

/**
 * created by Administrator on 2018/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WechatLoginVo {

    // 1、小程序登录
    private Integer loginType;
    //小程序登录入参
    private String code;
    private String encryptedData;
    private String iv;


}
