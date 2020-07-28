package com.jiqunar.light.service.moonlight;

import com.jiqunar.light.model.entity.moonlight.VisitRecordEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 访问记录 服务类
 *
 * @author auto generator
 * @since 2020-07-28
 */
public interface VisitRecordService extends IService<VisitRecordEntity> {
    /**
     * 分页获取访问记录
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
