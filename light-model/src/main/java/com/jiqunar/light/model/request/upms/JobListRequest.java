package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 岗位列表请求参数
 * @author jieguang.wang
 * @date 2020/7/3 16:43
 */
@Data
public class JobListRequest extends PageRequest {
    /**
     * Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 岗位名称
     */
    @ApiModelProperty(value = "岗位名称")
    private String jobName;
}
