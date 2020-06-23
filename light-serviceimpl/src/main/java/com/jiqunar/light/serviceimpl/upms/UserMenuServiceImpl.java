package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.model.entity.upms.UserMenuEntity;
import com.jiqunar.light.dao.upms.UserMenuMapper;
import com.jiqunar.light.service.upms.UserMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 用户菜单 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class UserMenuServiceImpl extends ServiceImpl<UserMenuMapper, UserMenuEntity> implements UserMenuService {
    /**
     * 分页获取用户菜单
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
