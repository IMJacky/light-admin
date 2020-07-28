package com.jiqunar.light.service.moonlight;

import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 账单信息 服务类
 *
 * @author auto generator
 * @since 2020-07-28
 */
public interface BillService extends IService<BillEntity> {
    /**
     * 分页获取账单信息
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
