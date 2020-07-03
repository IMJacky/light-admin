package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.JobEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.upms.JobEditRequest;
import com.jiqunar.light.model.request.upms.JobListRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 岗位 服务类
 *
 * @author auto generator
 * @since 2020-06-04
 */
public interface JobService extends IService<JobEntity> {
    /**
     * 分页获取岗位
     *
     * @param request
     * @return
     */
    PageResponse page(JobListRequest request);

    /**
     * 编辑岗位
     *
     * @param request
     * @return
     */
    Long edit(JobEditRequest request);
}
