package com.jiqunar.light.model.response.upms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息响应实体
 */
@Data
@ApiModel(value = "用户信息响应实体", description = "用户信息响应实体")
public class UserInfoResponse {
    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id")
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickname;

    /**
     * 头像
     */
    @ApiModelProperty(value = "头像")
    private String avatar;

    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Integer status;

    /**
     * 性别
     */
    @ApiModelProperty(value = "性别")
    private Integer gender;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String telephone;

    /**
     * 上次登录ip
     */
    @ApiModelProperty(value = "上次登录ip")
    private String lastLoginIp;

    /**
     * 上次登录时间
     */
    @ApiModelProperty(value = "上次登录时间")
    private LocalDateTime lastLoginTime;

    /**
     * 创建人id
     */
    @ApiModelProperty(value = "创建人id")
    private Long creatorId;

    /**
     * 创建人姓名
     */
    @ApiModelProperty(value = "创建人姓名")
    private String creatorName;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    /**
     * 是否删除
     */
    @ApiModelProperty(value = "是否删除")
    private Integer deleted;


    /**
     * 用户角色信息
     */
    @ApiModelProperty(value = "用户角色信息")
    private UserRole role;
}
