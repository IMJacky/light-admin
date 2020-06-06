package com.jiqunar.light.model.response.upms;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * 用户菜单扩展信息
 * @author jieguang.wang
 * @date 2020/6/6 20:35
 */
@Data
public class UserMenuMeta {

    /**
     * 图标
     */
    @ApiModelProperty(value = "图标")
    private String icon;

    /**
     * 页面标题
     */
    @ApiModelProperty(value = "页面标题")
    private String title;

//    /**
//     * 是否显示
//     */
//    @ApiModelProperty(value = "是否显示")
//    private Boolean show;
}
