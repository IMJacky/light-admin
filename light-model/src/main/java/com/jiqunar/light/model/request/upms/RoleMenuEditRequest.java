package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 角色菜单编辑请求参数
 *
 * @author jieguang.wang
 * @date 2020/8/14 17:14
 */
@Data
public class RoleMenuEditRequest extends BaseRequest {
    /**
     * 角色Id
     */
    @ApiModelProperty(value = "角色Id")
    private Long roleId;

    /**
     * 父子级菜单Id集合
     */
    @ApiModelProperty(value = "父子级菜单Id集合")
    private List<String> menuIdList;
}
