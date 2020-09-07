package com.jiqunar.light.service.log;

import com.jiqunar.light.model.entity.log.LogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.enums.LogSubTypeEnum;
import com.jiqunar.light.model.enums.LogTypeEnum;
import com.jiqunar.light.model.enums.OperateTypeEnum;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.request.log.LogListRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 日志 服务类
 *
 * @author auto generator
 * @since 2020-09-07
 */
public interface LogService extends IService<LogEntity> {
    /**
     * 分页获取日志
     *
     * @param request
     * @return
     */
    PageResponse page(LogListRequest request);

    /**
     * 新增日志(不用实体接收了，麻烦)
     *
     * @param operateTypeEnum
     * @param relateId
     * @param message
     * @param logTypeEnum
     * @param logSubTypeEnum
     * @param operaterId
     * @param operaterName
     */
    void add(OperateTypeEnum operateTypeEnum, Long relateId, String message, LogTypeEnum logTypeEnum, LogSubTypeEnum logSubTypeEnum, Long operaterId, String operaterName);
}
