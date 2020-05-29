package com.jiqunar.light.model.response.monitor;

import lombok.Data;

/**
 * 单个用户信息
 *
 * @author jieguang.wang
 * @date 2020/5/7 15:46
 */
@Data
public class GetUserInfoModel {
    /**
     * 主键Id
     */
    private Long id;

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
