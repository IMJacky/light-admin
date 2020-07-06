package com.jiqunar.light.model.response.upms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户菜单响应实体
 * @author jieguang.wang
 * @date 2020/6/4 14:59
 */
@Data
public class UserMenuResponse {
    /**
     * 主键Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 父级Id
     */
    @ApiModelProperty(value = "父级Id")
    private Long parentId;

    /**
     * 路由地址
     */
    @ApiModelProperty(value = "路由地址")
    private String path;

    /**
     * 是否外链
     */
    @ApiModelProperty(value = "是否外链")
    private Boolean isLink;

    /**
     * 路由唯一标识
     */
    @ApiModelProperty(value = "路由唯一标识")
    private String key;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 用户菜单扩展信息
     */
    @ApiModelProperty(value = "用户菜单扩展信息")
    private UserMenuMeta meta;
}
