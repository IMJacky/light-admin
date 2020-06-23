package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 菜单列表请求参数
 * @author jieguang.wang
 * @date 2020/6/23 11:39
 */
@Data
public class MenuListRequest extends PageRequest {
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
     * 类型（0菜单，1按钮，2外链）
     */
    @ApiModelProperty(value = "类型（0菜单，1按钮，2外链）")
    private Integer type;
}
