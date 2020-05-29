package com.jiqunar.light.service.log;

import com.jiqunar.light.model.entity.log.LogEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 日志 服务类
 *
 * @author auto generator
 * @since 2020-05-28
 */
public interface LogService extends IService<LogEntity> {
    /**
     * 分页获取日志
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
