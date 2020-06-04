package com.jiqunar.light.model.request.upms;

import com.sun.deploy.panel.IProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录请求参数
 */
@Data
@ApiModel(value = "登录请求参数", description = "登录请求参数")
public class LoginRequest {
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 记住我一周
     */
    @ApiModelProperty(value = "记住我一周")
    private Boolean rememberMe;
}
