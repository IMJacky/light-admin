package com.jiqunar.light.model.response.common;

import lombok.Data;

import java.util.List;

/**
 * 级联选择响应实体
 * @author jieguang.wang
 * @date 2020/9/3 19:56
 */
@Data
public class CascadeResponse {
    /**
     * 值
     */
    private String value;

    /**
     * 描述
     */
    private String label;

    /**
     * 子节点
     */
    private List<CascadeResponse> children;
}
