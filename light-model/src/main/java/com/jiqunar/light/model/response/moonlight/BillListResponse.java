
package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 账单列表响应实体
 */
@Data
@ApiModel(value = "账单列表响应实体", description = "账单列表响应实体")
public class BillListResponse {
    /**
     * 账单列表
     */
    @ApiModelProperty(value = "账单列表")
    private List<BillAggregation> billAggregationList;
}