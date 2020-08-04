package com.jiqunar.light.model.request.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 微信登录请求实体
 */
@Data
@ApiModel(value = "微信登录请求实体", description = "微信登录请求实体")
public class WxLoginRequest {
    /**
     * 微信code
     */
    @ApiModelProperty(value = "微信code")
    private String code;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    /**
     * 微信昵称
     */
    @ApiModelProperty(value = "微信昵称")
    private String nickName;
}
