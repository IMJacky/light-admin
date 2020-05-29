package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.model.entity.upms.JobEntity;
import com.jiqunar.light.dao.upms.JobMapper;
import com.jiqunar.light.service.upms.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 岗位 服务实现类
 *
 * @author auto generator
 * @since 2020-05-28
 */
@Service
public class JobServiceImpl extends ServiceImpl<JobMapper, JobEntity> implements JobService {
    /**
     * 分页获取岗位
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(PageRequest request) {
        IPage page = new Page<>(request.getPageIndex(), request.getPageSize());
        page = this.page(page);
        return new PageResponse(page.getTotal(), page.getRecords());
    }
}
