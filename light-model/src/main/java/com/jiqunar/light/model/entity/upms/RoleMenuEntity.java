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
 * 角色菜单
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lu_role_menu")
@ApiModel(value = "RoleMenuEntity对象", description = "角色菜单")
public class RoleMenuEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 角色Id
     */
    @ApiModelProperty(value = "角色Id")
    @TableField("role_id")
    private Long roleId;

    /**
     * 菜单Id
     */
    @ApiModelProperty(value = "菜单Id")
    @TableField("menu_id")
    private Long menuId;


}
