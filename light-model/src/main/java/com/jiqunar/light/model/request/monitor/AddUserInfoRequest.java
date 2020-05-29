package com.jiqunar.light.model.request.monitor;

import com.jiqunar.light.model.request.BaseRequest;
import lombok.Data;

/**
 * 新增用户信息的请求参数
 * @author jieguang.wang
 * @date 2020/5/6 16:27
 */
@Data
public class AddUserInfoRequest extends BaseRequest {
    /**
     * 工号
     */
    private String userJobNumber;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态：1、有效；0：无效
     */
    private Integer rowState;
}
