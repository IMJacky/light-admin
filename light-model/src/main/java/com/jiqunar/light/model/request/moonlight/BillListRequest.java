
package com.jiqunar.light.model.request.moonlight;

import com.jiqunar.light.model.request.BaseWxRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 账单列表请求实体
 */
@Data
@ApiModel(value = "账单列表请求实体", description = "账单列表请求实体")
public class BillListRequest extends BaseWxRequest {
    /**
     * 开始日期（格式：yyyy-MM-dd）
     */
    @ApiModelProperty(value = "开始日期（格式：yyyy-MM-dd）")
    private LocalDate startDate;

    /**
     * 结束日期（格式：yyyy-MM-dd）
     */
    @ApiModelProperty(value = "结束日期（格式：yyyy-MM-dd）")
    private LocalDate endDate;
}