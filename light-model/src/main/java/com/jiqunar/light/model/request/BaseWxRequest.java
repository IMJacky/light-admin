package com.jiqunar.light.model.request;

import lombok.Data;

/**
 * 微信请求基类
 * @author jieguang.wang
 * @date 2020/8/4 15:38
 */
@Data
public class BaseWxRequest {
    /**
     * 微信unionid
     */
    private String unionId;

    /**
     * 微信openid
     */
    private String openId;
}
