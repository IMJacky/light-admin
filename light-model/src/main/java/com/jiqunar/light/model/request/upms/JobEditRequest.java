package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 岗位编辑请求参数
 * @author jieguang.wang
 * @date 2020/7/3 16:44
 */
@Data
public class JobEditRequest extends BaseRequest {
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
