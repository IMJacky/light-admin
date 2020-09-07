package com.jiqunar.light.model.response.log;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 日志列表响应实体
 *
 * @author jieguang.wang
 * @date 2020/9/7 18:23
 */
@Data
@ApiModel(value = "日志列表响应实体", description = "日志列表响应实体")
public class LogListResponse {
    /**
     * 日志Id
     */
    private Long id;
    /**
     * 操作人Id
     */
    private Long userId;
    /**
     * 操作人名称
     */
    private String userName;
    /**
     * 日志类型
     */
    private String logTypeName;
    /**
     * 日志子类型
     */
    private String logSubTypeName;
    /**
     * 操作类型
     */
    private String operateTypeName;
    /**
     * 关联Id
     */
    private long relateId;
    /**
     * 日志内容
     */
    private String message;
    /**
     * 操作时间
     */
    private LocalDateTime operationTime;
}
