package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.DeptEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 部门 服务类
 *
 * @author auto generator
 * @since 2020-06-04
 */
public interface DeptService extends IService<DeptEntity> {
    /**
     * 分页获取部门
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
