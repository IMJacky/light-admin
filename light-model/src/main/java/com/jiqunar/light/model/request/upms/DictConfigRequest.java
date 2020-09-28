package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典配置请求参数
 * @author jieguang.wang
 * @date 2020/7/3 16:44
 */
@Data
public class DictConfigRequest extends BaseRequest {
    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dictCode;
}
