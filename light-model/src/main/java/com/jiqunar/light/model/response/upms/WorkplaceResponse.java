package com.jiqunar.light.model.response.upms;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 工作台响应实体
 * @author jieguang.wang
 * @date 2020/9/7 14:34
 */
@Data
@ApiModel(value = "工作台响应实体", description = "工作台响应实体")
public class WorkplaceResponse {
    /**
     * 用户总数
     */
    @ApiModelProperty(value = "用户总数")
    private Integer userCount;

    /**
     * 部门总数
     */
    @ApiModelProperty(value = "部门总数")
    private Integer deptCount;

    /**
     * 岗位总数
     */
    @ApiModelProperty(value = "岗位总数")
    private Integer jobCount;
}
