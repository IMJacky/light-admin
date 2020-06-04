package com.jiqunar.light.model.response.upms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 登录响应实体
 */
@Data
@ApiModel(value = "登录响应实体", description = "登录响应实体")
public class LoginResponse {
    /**
     * 登录Token
     */
    @ApiModelProperty(value = "登录Token")
    private String token;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    private Long userId;
}
