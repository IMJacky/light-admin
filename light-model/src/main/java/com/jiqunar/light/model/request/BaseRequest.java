package com.jiqunar.light.model.request;

import lombok.Data;

/**
 * 请求基类
 * @author jieguang.wang
 * @date 2020/5/6 16:04
 */
@Data
public class BaseRequest {

    /**
     * 操作人Id
     */
    private Long operateId;

    /**
     * 操作人名字
     */
    private String operateName;
}
