package com.jiqunar.light.model.response.monitor;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author jieguang.wang
 * @date 2020/5/7 14:33
 */
@Data
public class ListUserInfoModel {
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

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
