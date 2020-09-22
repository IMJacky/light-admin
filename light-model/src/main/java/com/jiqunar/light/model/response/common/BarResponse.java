package com.jiqunar.light.model.response.common;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * 柱状图响应实体
 * @author jieguang.wang
 * @date 2020/9/22 12:04
 */
@Data
@ApiModel(value = "柱状图响应实体", description = "柱状图响应实体")
public class BarResponse {
    /**
     * X轴数据
     */
    private String x;

    /**
     * Y轴数据
     */
    private Long y;
}
