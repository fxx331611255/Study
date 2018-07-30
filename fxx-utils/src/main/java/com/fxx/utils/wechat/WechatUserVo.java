package com.fxx.utils.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by Administrator on 2018/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WechatUserVo {

    private String openId;
    private String nickName;
    private String gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
    private String unionId;
    private String status;
    private String session_key;
}
