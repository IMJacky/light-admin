package com.jiqunar.light.model.response.upms;

import com.jiqunar.light.model.entity.upms.UserEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户列表响应实体
 *
 * @author jieguang.wang
 * @date 2020/7/9 17:49
 */
@Data
public class UserListResponse extends UserEntity {
    /**
     * 角色id（多个用逗号分隔）
     */
    @ApiModelProperty(value = "角色id（多个用逗号分隔）")
    private String roleId;

    /**
     * 用户角色（多个用逗号分隔）
     */
    @ApiModelProperty(value = "用户角色（多个用逗号分隔）")
    private String roleName;

    /**
     * 部门id
     */
    @ApiModelProperty(value = "部门id")
    private List<String> deptIdList;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private List<String> deptNameList;
}
