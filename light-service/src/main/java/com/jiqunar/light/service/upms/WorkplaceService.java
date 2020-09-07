package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.response.upms.WorkplaceResponse;

/**
 * 工作台服务定义
 * @author jieguang.wang
 * @date 2020/9/7 14:38
 */
public interface WorkplaceService {
    /**
     * 工作台详情
     * @return
     */
    WorkplaceResponse getWorkplace();
}
