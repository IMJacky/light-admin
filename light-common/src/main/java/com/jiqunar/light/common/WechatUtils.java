package com.jiqunar.light.common;

import com.alibaba.fastjson.JSON;
import com.jiqunar.light.model.response.moonlight.CodeSessionResponse;
import com.jiqunar.light.model.response.moonlight.WechatUserDecrypt;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
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

    /**
     * 根据encryptedData，iv，sessionKey解密获取用户信息
     *
     * @param encryptedData
     * @param iv
     * @param sessionKey
     * @return
     */
    public static WechatUserDecrypt decryptData(String encryptedData, String iv, String sessionKey) {
        try {
            byte[] aesKey = Base64.decode(sessionKey);
            byte[] aesIV = Base64.decode(iv);
            byte[] aesEncryptedData = Base64.decode(encryptedData);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(aesKey, "AES"), new IvParameterSpec(aesIV));
            byte[] original = cipher.doFinal(aesEncryptedData);
            if (null != original && original.length > 0) {
                String result = new String(original, "UTF-8");
                return JSON.parseObject(result, WechatUserDecrypt.class);
            }
            return null;
        } catch (Exception ex) {
            return null;
        }
    }

}
