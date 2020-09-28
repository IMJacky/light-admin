package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典编辑请求参数
 * @author jieguang.wang
 * @date 2020/7/3 16:44
 */
@Data
public class DictEditRequest extends BaseRequest {
    /**
     * Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    private String dictName;
}
