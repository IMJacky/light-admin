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
 * 用户菜单
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lu_user_menu")
@ApiModel(value = "UserMenuEntity对象", description = "用户菜单")
public class UserMenuEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户Id
     */
    @ApiModelProperty(value = "用户Id")
    @TableField("user_id")
    private Long userId;

    /**
     * 菜单Id
     */
    @ApiModelProperty(value = "菜单Id")
    @TableField("menu_id")
    private Long menuId;


}
