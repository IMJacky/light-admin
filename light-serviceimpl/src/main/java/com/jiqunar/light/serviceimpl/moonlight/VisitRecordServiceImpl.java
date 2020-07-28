package com.jiqunar.light.serviceimpl.moonlight;

import com.jiqunar.light.model.entity.moonlight.VisitRecordEntity;
import com.jiqunar.light.dao.moonlight.VisitRecordMapper;
import com.jiqunar.light.service.moonlight.VisitRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 访问记录 服务实现类
 *
 * @author auto generator
 * @since 2020-07-28
 */
@Service
public class VisitRecordServiceImpl extends ServiceImpl<VisitRecordMapper, VisitRecordEntity> implements VisitRecordService {
    /**
     * 分页获取访问记录
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(PageRequest request) {
        IPage page = new Page<>(request.getPageNo(), request.getPageSize());
        page = this.page(page);
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }
}
