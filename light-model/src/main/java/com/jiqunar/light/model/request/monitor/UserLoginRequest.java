package com.jiqunar.light.model.request.monitor;

import lombok.Data;

/**
 * 用户登录请求参数
 * @author jieguang.wang
 * @date 2020/5/8 14:53
 */
@Data
public class UserLoginRequest {
    /**
     * 工号
     */
    private String userJobNumber;

    /**
     * 手机号
     */
    private String mobile;
}
