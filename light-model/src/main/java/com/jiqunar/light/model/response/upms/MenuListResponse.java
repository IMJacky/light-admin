package com.jiqunar.light.model.response.upms;

import com.jiqunar.light.model.entity.upms.MenuEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 菜单列表响应实体
 *
 * @author jieguang.wang
 * @date 2020/9/15 17:21
 */
@Data
public class MenuListResponse extends MenuEntity {

    /**
     * 子级菜单列表
     */
    @ApiModelProperty(value = "子级菜单列表")
    private List<MenuListResponse> children;
}
