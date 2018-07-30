package com.fxx.utils.wechat;

import com.alibaba.fastjson.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * created by fengxiaoxi on 2018/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccessToken {

    private JSONObject jsonObject;

    private AccessToken(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    private String access_token;
    private String session_key;
    private Long expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;
    private Long errcode;
    private String errmsg;

    public static AccessToken parse(JSONObject jsonObject) {
        if (jsonObject == null) {
            return null;
        }
        AccessToken obj = new AccessToken(jsonObject);
        obj.access_token = jsonObject.getString("access_token");
        obj.session_key = jsonObject.getString("session_key");
        obj.openid = jsonObject.getString("openid");
        obj.expires_in = jsonObject.getLong("expires_in");
        obj.refresh_token = jsonObject.getString("refresh_token");
        obj.scope = jsonObject.getString("scope");
        obj.unionid = jsonObject.getString("unionid");
        obj.errcode = jsonObject.getLong("errcode");
        obj.errmsg = jsonObject.getString("errmsg");
        return obj;
    }
}
