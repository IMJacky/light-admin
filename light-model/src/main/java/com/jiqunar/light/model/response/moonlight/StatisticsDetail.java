package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jieguang.wang
 * @date 2020/11/5 16:14
 */
@Data
public class StatisticsDetail {
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
     * 描述 日期（yyyy-MM-dd）/月份(yyyy-MM)
     */
    @ApiModelProperty(value = "描述 日期（yyyy-MM-dd）/月份(yyyy-MM)")
    private String desc;
}