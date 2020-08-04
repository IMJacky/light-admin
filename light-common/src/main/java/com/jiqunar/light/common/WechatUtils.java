package com.jiqunar.light.common;

import com.alibaba.fastjson.JSON;
import com.jiqunar.light.model.response.moonlight.CodeSessionResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * 微信帮助方法
 */
public class WechatUtils {
    private static final String appid = "wx319ba67a02e203aa";
    private static final String secret = "3ee5e85110234c9f41eee85fcc23560c";

    public static CodeSessionResponse getCodeSession(String code) {
        String requestUrl = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> requestUrlParam = new HashMap<>();
        //https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/login/auth.code2Session.html
        //小程序appId
        requestUrlParam.put("appid", appid);
        //小程序secret
        requestUrlParam.put("secret", secret);
        //小程序端返回的code
        requestUrlParam.put("js_code", code);
        //默认参数
        requestUrlParam.put("grant_type", "authorization_code");
        String result = HttpClientUtils.doPost(requestUrl, requestUrlParam);
        //发送post请求读取调用微信接口获取openid用户唯一标识
        return JSON.parseObject(result, CodeSessionResponse.class);
    }
}
