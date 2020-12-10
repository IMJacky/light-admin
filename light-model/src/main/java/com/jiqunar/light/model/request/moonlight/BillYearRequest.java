package com.jiqunar.light.model.request.moonlight;

import com.jiqunar.light.model.request.BaseWxRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 年度账单请求实体
 * @author jieguang.wang
 * @date 2020/11/4 22:09
 */
@Data
@ApiModel(value = "年度账单请求实体", description = "年度账单请求实体")
public class BillYearRequest extends BaseWxRequest {
    /**
     * 年份
     */
    @ApiModelProperty(value = "年份")
    private Integer year;
}
