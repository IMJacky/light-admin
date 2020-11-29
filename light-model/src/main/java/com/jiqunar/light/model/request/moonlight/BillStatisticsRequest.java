package com.jiqunar.light.model.request.moonlight;

import com.jiqunar.light.model.request.BaseWxRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 账单按日统计请求实体
 * @author jieguang.wang
 * @date 2020/11/4 22:09
 */
@Data
@ApiModel(value = "账单按日统计请求实体", description = "账单按日统计请求实体")
public class BillStatisticsRequest extends BaseWxRequest {
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
     * 年份 按月统计
     */
    @ApiModelProperty(value = "年份 按月统计")
    private Integer year;

    /**
     * 统计类型 0：按日统计，1：按月统计
     */
    @ApiModelProperty(value = "统计类型 0：按日统计，1：按月统计")
    private Integer statisticsType;

    /**
     * mysql日期格式
     */
    @ApiModelProperty(value = "mysql日期格式")
    private String statisticsTypeFormat;
}
