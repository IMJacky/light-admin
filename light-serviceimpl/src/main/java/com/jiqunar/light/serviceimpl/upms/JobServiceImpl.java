package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.model.entity.upms.JobEntity;
import com.jiqunar.light.dao.upms.JobMapper;
import com.jiqunar.light.model.request.upms.JobEditRequest;
import com.jiqunar.light.model.request.upms.JobListRequest;
import com.jiqunar.light.service.upms.JobService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;

/**
 * 岗位 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
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
    public PageResponse page(JobListRequest request) {
        IPage page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<JobEntity> queryWrapper = new QueryWrapper<JobEntity>().lambda();
        queryWrapper.orderByDesc(JobEntity::getId);
        if (request.getId() != null && request.getId() > 0) {
            queryWrapper.eq(JobEntity::getId, request.getId());
        }
        if (StringUtils.isNotBlank(request.getJobName())) {
            queryWrapper.like(JobEntity::getJobName, request.getJobName());
        }
        page = this.page(page, queryWrapper);
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }

    /**
     * 编辑岗位
     *
     * @param request
     * @return
     */
    @Override
    public Long edit(JobEditRequest request) {
        JobEntity jobEntity = new JobEntity();
        Long result = Long.valueOf(0);
        LocalDateTime now = LocalDateTime.now();
        if (request.getId() != null && request.getId() > 0) {
            jobEntity = this.getById(request.getId());
            jobEntity.setUpdateDate(now);
            jobEntity.setUpdaterId(request.getOperateId());
            jobEntity.setUpdaterName(request.getOperateName());
        } else {
            jobEntity.setCreateDate(now);
            jobEntity.setCreaterId(request.getOperateId());
            jobEntity.setCreaterName(request.getOperateName());
        }
        jobEntity.setJobName(request.getJobName());
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(jobEntity)) {
                result = jobEntity.getId();
            }
        } else {
            if (this.save(jobEntity)) {
                result = jobEntity.getId();
            }
        }
        return result;
    }
}
