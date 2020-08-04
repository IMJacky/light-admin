package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 账单列表详情响应实体
 */
@Data
@Builder
@ApiModel(value = "账单列表详情响应实体", description = "账单列表详情响应实体")
public class BillDetail {
    /**
     * 主键Id
     */
    private Long id;

    /**
     * 账单时间（格式：HH:mm）
     */
    @ApiModelProperty(value = "账单时间（格式：HH:mm）")
    private String billTime;

    /**
     * 账单类型（0支出，1收入）
     */
    @ApiModelProperty(value = "账单类型（0支出，1收入）")
    private Integer billType;

    /**
     * 标签
     */
    @ApiModelProperty(value = "标签")
    private String tag;

    /**
     * 子标签
     */
    @ApiModelProperty(value = "子标签")
    private String subTag;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;
}
