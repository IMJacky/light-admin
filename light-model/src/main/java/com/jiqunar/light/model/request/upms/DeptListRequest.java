package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门列表请求参数
 * @author jieguang.wang
 * @date 2020/7/3 16:44
 */
@Data
public class DeptListRequest extends PageRequest {
    /**
     * Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    private String deptName;

    /**
     * 父级部门Id
     */
    @ApiModelProperty(value = "父级部门Id")
    private Long parentDeptId;
}
