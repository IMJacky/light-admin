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
 * 菜单
 *
 * @author auto generator
 * @since 2020-06-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lu_menu")
@ApiModel(value = "MenuEntity对象", description = "菜单")
public class MenuEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 菜单唯一标识
     */
    @ApiModelProperty(value = "菜单唯一标识")
    @TableField("menu_key")
    private String menuKey;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    /**
     * 路由/外链地址
     */
    @ApiModelProperty(value = "路由/外链地址")
    @TableField("path")
    private String path;

    /**
     * 父级菜单Id
     */
    @ApiModelProperty(value = "父级菜单Id")
    @TableField("parent_menu_id")
    private Long parentMenuId;

    /**
     * 类型（0菜单，1按钮）
     */
    @ApiModelProperty(value = "类型（0菜单，1按钮）")
    @TableField("type")
    private Integer type;

    /**
     * 是否外链（0否，1是）
     */
    @ApiModelProperty(value = "是否外链（0否，1是）")
    @TableField("is_link")
    private Integer isLink;

    /**
     * 排序值
     */
    @ApiModelProperty(value = "排序值")
    @TableField("sort")
    private Integer sort;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    @TableField("icon")
    private String icon;


}
