package com.jiqunar.light.model.request.moonlight;

import com.jiqunar.light.model.request.BaseWxRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 账单编辑请求实体
 */
@Data
@ApiModel(value = "账单编辑请求实体", description = "账单编辑请求实体")
public class BillEditRequest extends BaseWxRequest {
    /**
     * 账单主键Id
     */
    @ApiModelProperty(value = "账单主键Id")
    private Long billId;

    /**
     * 账单类型（0支出，1收入）
     */
    @ApiModelProperty(value = "账单类型（0支出，1收入）")
    private Integer billType;

    /**
     * 金额
     */
    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

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
     * 账单日期
     */
    @ApiModelProperty(value = "账单日期")
    private LocalDateTime billDate;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;
}
