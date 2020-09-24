package com.jiqunar.light.model.request.log;

import com.jiqunar.light.model.request.PageRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.time.LocalDate;

/**
 * 日志列表请求参数
 *
 * @author jieguang.wang
 * @date 2020/9/7 18:21
 */
@Data
@ApiModel(value = "日志列表请求参数", description = "日志列表请求参数")
public class LogListRequest extends PageRequest {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 操作类型
     */
    private Integer operateType;
    /**
     * 日志类型
     */
    private Integer logType;
    /**
     * 日志子类型
     */
    private Integer logSubType;
    /**
     * 关联Id
     */
    private Long relateId;
    /**
     * 查询开始日期
     */
    private LocalDate beginDate;
    /**
     * 查询结束日期
     */
    private LocalDate endDate;
}
