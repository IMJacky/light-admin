package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 字典编辑请求参数
 *
 * @author jieguang.wang
 * @date 2020/7/3 16:44
 */
@Data
public class DictConfigSaveRequest extends BaseRequest {
    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    private String dictCode;

    /**
     * 字典配置集合
     */
    @ApiModelProperty(value = "字典配置集合")
    private List<DictConfigItem> dictConfigList;

    @Data
    public static class DictConfigItem {
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
}
