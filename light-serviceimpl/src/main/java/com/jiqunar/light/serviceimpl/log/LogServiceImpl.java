package com.jiqunar.light.serviceimpl.log;

import com.jiqunar.light.model.entity.log.LogEntity;
import com.jiqunar.light.dao.log.LogMapper;
import com.jiqunar.light.service.log.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 日志 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {
    /**
     * 分页获取日志
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
