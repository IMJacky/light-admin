package com.jiqunar.light.model.request.monitor;

import com.jiqunar.light.model.request.PageRequest;
import lombok.Data;

/**
 * 用户列表请求参数
 *
 * @author jieguang.wang
 * @date 2020/5/7 14:29
 */
@Data
public class ListUserInfoRequest extends PageRequest {
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
     * 状态：1、有效；0：无效
     */
    private Integer rowState;
}
