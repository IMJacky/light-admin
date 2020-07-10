package com.jiqunar.light.model.request;

import lombok.Data;

/**
 * 分页请求参数
 *
 * @author jieguang.wang
 * @date 2020/5/7 14:36
 */
@Data
public class PageRequest extends BaseRequest {
    /**
     * 第几页
     */
    private Integer pageNo = 1;

    /**
     * 页码大小
     */
    private Integer pageSize = 10;
}
