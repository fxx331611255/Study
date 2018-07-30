package com.fxx.utils.wechat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

/**
 * created by fengxiaoxi on 2018/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WechatPayVo {
    //小程序版本   1.买家版,2.卖家版,3配送版
    // app版本:   4.买家版,5.卖家版,6.配送版
    private Integer wechatType;
    private String openId;
    //商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。
    private String tradeNo;
    //订单总金额，单位为元
    private BigDecimal orderAmount;
    //商品名称
    private String goodsSubject;
    private HttpServletRequest request;
}
