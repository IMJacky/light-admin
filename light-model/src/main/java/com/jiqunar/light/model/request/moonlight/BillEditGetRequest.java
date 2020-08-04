
package com.jiqunar.light.model.request.moonlight;

import com.jiqunar.light.model.request.BaseWxRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 访问记录请求实体
 */
@Data
@ApiModel(value = "访问记录请求实体", description = "访问记录请求实体")
public class BillEditGetRequest extends BaseWxRequest {
    /**
     * 账单主键Id
     */
    @ApiModelProperty(value = "账单主键Id")
    private Long billId;
}