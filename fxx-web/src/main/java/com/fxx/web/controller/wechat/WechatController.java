package com.fxx.web.controller.wechat;

import com.fxx.service.wechat.WechatService;
import com.fxx.utils.vo.ResponseVo;
import com.fxx.utils.wechat.AccessToken;
import com.fxx.utils.wechat.WechatLoginVo;
import com.fxx.utils.wechat.WechatPayVo;
import com.fxx.utils.wechat.WechatUserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * created by fengxiaoxi on 2018/7/19
 */
@RestController
@RequestMapping("wechat")
@Slf4j
public class WechatController {

    @Resource
    private WechatService wechatService;

    @Value("${wechat.login.appId}")
    private String appId;
    @Value("${wechat.pay.machId}")
    private String machId;
    @Value("${wechat.pay.getPay_notify_url}")
    private String getPay_notify_url;
    @Value("${wechat.pay.tradetype}")
    private String tradetype;
    @Value("${wechat.pay.privateKey}")
    private String privateKey;
    @Value("${wechat.pay.signtype}")
    private String signtype;
    @Value("${wechat.pay.pay_url}")
    private String pay_url;

    /**
     * 微信登录
     */
    @PostMapping("login")
    public boolean login(@RequestBody WechatLoginVo vo) throws Exception {
        if (vo.getLoginType() == null) {
            return false;
        } else {
            /**
             *  1.小程序登录
             * */
            if (vo.getLoginType() == 1) {
                AccessToken accessToken = wechatService.getSessionKey(vo);
                Map decodeMap = wechatService.decodeUserInfo(vo.getEncryptedData(), vo.getIv(), accessToken.getSession_key());
                if (decodeMap.get("status").equals("0")) {
                    throw new Exception("登陆失败,请退出重新登陆");
                }
                WechatUserVo wechatUserVo = (WechatUserVo) decodeMap.get("wechatUserVo");
            } else {

            }
        }

        /**
         * 登录成功,进行业务层操作
         * */
        return true;
    }


    /**
     * 微信app/小程序充值
     */
    @PostMapping("wechatPay")
    public Map<String, String> wechatPay(@RequestBody WechatPayVo vo, HttpServletRequest request) throws Exception {
        //生成的随机字符串
        String nonce_str = wechatService.getRandomStringByLength(32);
        //组装参数，用户生成统一下单接口的签名
        Map<String, String> packageParams = new HashMap<>();
        packageParams.put("appid", appId);
        packageParams.put("mch_id", machId);
        packageParams.put("nonce_str", nonce_str);//生成的随机字符串
        packageParams.put("body", vo.getGoodsSubject());  //商品名称
        packageParams.put("out_trade_no", vo.getTradeNo());//商户订单号
        packageParams.put("total_fee", vo.getOrderAmount().multiply(new BigDecimal("100")).intValue() + "");//支付金额，这边需要转成字符串类型，否则后面的签名会失败
        packageParams.put("spbill_create_ip", wechatService.getIpAddr(vo.getRequest()));//客户端的ip地址
        packageParams.put("notify_url", getPay_notify_url);//支付成功后的回调地址
        packageParams.put("trade_type", tradetype);//支付方式
        packageParams.put("openid", vo.getOpenId());

        //MD5运算生成签名，这里是第一次签名，用于调用统一下单接口
        String mysign = wechatService.generateSignature(packageParams, privateKey, signtype).toUpperCase();
        packageParams.put("sign", mysign);
        String mapToXml = wechatService.mapToXml(packageParams);
        log.info("调试模式_统一下单接口 请求XML数据：" + mapToXml);

        //调用统一下单接口，并接受返回的结果
        String result = wechatService.httpRequest(pay_url, "POST", mapToXml);
        log.info("调试模式_统一下单接口 返回XML数据：" + result);

        // 将解析结果存储在HashMap中
        Map map = wechatService.xmlToMap(result);
        String return_code = (String) map.get("return_code");//返回状态码
        Map<String, String> response = new HashMap<>();//返回给小程序端需要的参数
        if (return_code == "SUCCESS" || return_code.equals(return_code)) {
            String prepay_id = (String) map.get("prepay_id");//返回的预付单信息
            response.put("appId", appId);
            response.put("nonceStr", nonce_str);
            response.put("package", "prepay_id=" + prepay_id);
            response.put("signType", "MD5");
            Long timeStamp = System.currentTimeMillis();
            response.put("timeStamp", wechatService.toStr(timeStamp));//这边要将返回的时间戳转化成字符串，不然小程序端调用wx.requestPayment方法会报签名错误
            final String paySign = wechatService.generateSignature(response, privateKey, signtype).toUpperCase();
            response.put("paySign", paySign);
        }
        response.put("appid", appId);
        return response;
    }

    /**
     * 微信支付回调
     */
    @RequestMapping("notify_url")
    public boolean notify_url(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("财付通支付通知开始");
        String result = wechatService.getRequestString(request);
        Map parse = wechatService.doXMLParse(result);
        String out_trade_no = parse.get("out_trade_no").toString();
        String result_code = parse.get("result_code").toString();
        String transaction_id = parse.get("transaction_id").toString();
        String openid = parse.get("openid").toString();
        //  微信支付订单号	transaction_id
        log.info("商户订单号：" + out_trade_no + "......交易状态：" + result_code + "......当前时间" + new Date() + "......transaction_id" + transaction_id);
        //通信成功
        if (parse.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
            log.info("支付通知成功");

            //告诉微信服务器，我收到信息了，不要在调用回调action了
            String responseXml = wechatService.setXML("SUCCESS", "OK");
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(responseXml.getBytes());
            out.flush();
            out.close();
        }
        return true;
    }
}
