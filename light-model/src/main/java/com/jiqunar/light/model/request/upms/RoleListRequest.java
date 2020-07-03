package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色列表请求参数
 * @author jieguang.wang
 * @date 2020/7/3 16:29
 */
@Data
public class RoleListRequest extends PageRequest {
    /**
     * Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
}
