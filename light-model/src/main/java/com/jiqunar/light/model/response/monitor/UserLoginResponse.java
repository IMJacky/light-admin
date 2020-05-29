package com.jiqunar.light.model.response.monitor;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户登录响应实体
 * @author jieguang.wang
 * @date 2020/5/8 14:55
 */
@Data
public class UserLoginResponse {
    /**
     * 登录是否成功
     */
    private Boolean isSuccess;

    /**
     * 消息
     */
    private String message;

    /**
     * 登录成功之后生成的唯一token
     */
    private String token;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;
}
