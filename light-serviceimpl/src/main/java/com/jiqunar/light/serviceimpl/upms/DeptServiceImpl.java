package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.model.entity.upms.DeptEntity;
import com.jiqunar.light.dao.upms.DeptMapper;
import com.jiqunar.light.service.upms.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 部门 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptEntity> implements DeptService {
    /**
     * 分页获取部门
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
