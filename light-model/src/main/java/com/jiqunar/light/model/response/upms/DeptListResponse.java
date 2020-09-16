package com.jiqunar.light.model.response.upms;

import com.jiqunar.light.model.entity.upms.DeptEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 部门列表
 *
 * @author jieguang.wang
 * @date 2020/9/15 15:25
 */
@Data
public class DeptListResponse extends DeptEntity {
    /**
     * 子级部门列表
     */
    @ApiModelProperty(value = "子级部门列表")
    private List<DeptListResponse> children;
}
