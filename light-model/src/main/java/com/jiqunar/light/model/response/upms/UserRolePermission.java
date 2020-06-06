package com.jiqunar.light.model.response.upms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * 用户角色权限信息
 */
@Data
@ApiModel(value = "用户角色权限信息", description = "用户角色权限信息")
public class UserRolePermission {
    /**
     * 角色id
     */
    @ApiModelProperty(value = "角色id")
    private String roleId;

    /**
     * 权限唯一标识
     */
    @ApiModelProperty(value = "权限唯一标识")
    private String permissionId;

    /**
     * 权限名称
     */
    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    /**
     * 用户角色权限详细操作
     */
    @ApiModelProperty(value = "用户角色权限详细操作")
    private List<UserRolePermissionAction> actionEntitySet;
}
