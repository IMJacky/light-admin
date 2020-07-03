package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色编辑请求参数
 * @author jieguang.wang
 * @date 2020/7/3 16:32
 */
@Data
public class RoleEditRequest extends BaseRequest {
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
