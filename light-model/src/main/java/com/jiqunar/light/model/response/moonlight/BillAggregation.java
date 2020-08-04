package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * 账单列表聚合响应实体
 */
@Data
@ApiModel(value = "账单列表聚合响应实体", description = "账单列表聚合响应实体")
public class BillAggregation {
    /**
     * 账单日期（格式：yyyy-MM-dd）
     */
    @ApiModelProperty(value = "账单日期（格式：yyyy-MM-dd）")
    private LocalDate billDate;

    /**
     * 周几
     */
    @ApiModelProperty(value = "周几")
    private String billWeek;

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
     * 账单详情列表
     */
    @ApiModelProperty(value = "账单详情列表")
    private List<BillDetail> billDetailList;
}
