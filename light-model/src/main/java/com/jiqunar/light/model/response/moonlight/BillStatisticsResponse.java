package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 账单统计响应实体
 * @author jieguang.wang
 * @date 2020/11/4 22:13
 */
@Data
@ApiModel(value = "账单统计响应实体", description = "账单统计响应实体")
public class BillStatisticsResponse {

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
     * 统计详情列表
     */
    @ApiModelProperty(value = "统计详情列表")
    private List<StatisticsDetail> statisticsDetailList;
}
