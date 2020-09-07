package com.jiqunar.light.model.entity.upms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiqunar.light.model.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户角色
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lu_user_role")
@ApiModel(value = "UserRoleEntity对象", description = "用户角色")
public class UserRoleEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    @TableField("user_id")
    private Long userId;

    /**
     * 角色Id
     */
    @ApiModelProperty(value = "角色Id")
    @TableField("role_id")
    private Long roleId;


}
