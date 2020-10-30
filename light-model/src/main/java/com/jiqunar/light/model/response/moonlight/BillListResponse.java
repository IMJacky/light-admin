
package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 账单列表响应实体
 */
@Data
@ApiModel(value = "账单列表响应实体", description = "账单列表响应实体")
public class BillListResponse {
    /**
     * 日历最小日期
     */
    @ApiModelProperty(value = "日历最小日期")
    private Long minDate;
    /**
     * 日历最大日期
     */
    @ApiModelProperty(value = "日历最大日期")
    private Long maxDate;
    /**
     * 日历选择的日期区间
     */
    @ApiModelProperty(value = "日历选择的日期区间")
    private List<Long> defaultRangeList;
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
    /**
     * 收入总金额
     */
    @ApiModelProperty(value = "收入总金额")
    private BigDecimal earningAmount;

    /**
     * 支出总金额
     */
    @ApiModelProperty(value = "支出总金额")
    private BigDecimal expenseAmount;
    /**
     * 账单列表
     */
    @ApiModelProperty(value = "账单列表")
    private List<BillAggregation> billAggregationList;
}