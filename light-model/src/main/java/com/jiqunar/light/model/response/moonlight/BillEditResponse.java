package com.jiqunar.light.model.response.moonlight;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * 账单编辑响应实体
 */
@Data
@ApiModel(value = "账单编辑响应实体", description = "账单编辑响应实体")
public class BillEditResponse {

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

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String description;

    /**
     * 账单日期
     */
    @ApiModelProperty(value = "账单日期")
    private LocalDateTime billDate;

    /**
     * 标签列表-子标签列表
     */
    @ApiModelProperty(value = "标签列表-子标签列表")
    private Map<String, List<String>> tagMap;
}
