package com.jiqunar.light.model.response.upms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用户角色权限详细操作信息
 */
@Data
@ApiModel(value = "用户角色权限详细操作信息", description = "用户角色权限详细操作信息")
public class UserRolePermissionAction {
    /**
     * 操作
     */
    @ApiModelProperty(value = "操作")
    private String action;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String describe;
}
