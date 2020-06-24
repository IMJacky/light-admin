package com.jiqunar.light.model.request.upms;

import com.baomidou.mybatisplus.annotation.TableField;
import com.jiqunar.light.model.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单编辑请求参数
 * @author jieguang.wang
 * @date 2020/6/24 14:59
 */
@Data
public class MenuEditRequest extends BaseRequest {
    /**
     * Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 父级菜单Id
     */
    @ApiModelProperty(value = "父级菜单Id")
    private Long parentMenuId;

    /**
     * 菜单名称
     */
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    /**
     * 路由/外链地址
     */
    @ApiModelProperty(value = "路由/外链地址")
    private String path;

    /**
     * 组件路径
     */
    @ApiModelProperty(value = "组件路径")
    private String component;

    /**
     * 类型（0菜单，1按钮，2外链）
     */
    @ApiModelProperty(value = "类型（0菜单，1按钮，2外链）")
    private Integer type;

    /**
     * 排序值
     */
    @ApiModelProperty(value = "排序值")
    private Integer sort;

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;
}
