package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 字典列表请求参数
 * @author jieguang.wang
 * @date 2020/7/3 16:43
 */
@Data
public class DictListRequest extends PageRequest {
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
