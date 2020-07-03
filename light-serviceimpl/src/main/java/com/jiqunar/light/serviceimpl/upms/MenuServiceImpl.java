package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.model.entity.upms.MenuEntity;
import com.jiqunar.light.dao.upms.MenuMapper;
import com.jiqunar.light.model.request.upms.MenuEditRequest;
import com.jiqunar.light.model.request.upms.MenuListRequest;
import com.jiqunar.light.service.upms.MenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * 菜单 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {
    /**
     * 分页获取菜单
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(MenuListRequest request) {
        IPage page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<MenuEntity>().lambda();
        queryWrapper.orderByDesc(MenuEntity::getId);
        if (request.getId() != null && request.getId() > 0) {
            queryWrapper.eq(MenuEntity::getId, request.getId());
        }
        if (request.getParentMenuId() != null && request.getParentMenuId() > 0) {
            queryWrapper.eq(MenuEntity::getParentMenuId, request.getParentMenuId());
        }
        if (request.getType() != null) {
            queryWrapper.eq(MenuEntity::getType, request.getType());
        }
        if (StringUtils.isNotBlank(request.getMenuName())) {
            queryWrapper.like(MenuEntity::getMenuName, request.getMenuName());
        }
        page = this.page(page, queryWrapper);
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }

    /**
     * 编辑菜单
     *
     * @param request
     * @return
     */
    @Override
    public Long edit(MenuEditRequest request) {
        MenuEntity menuEntity = new MenuEntity();
        Long result = Long.valueOf(0);
        LocalDateTime now = LocalDateTime.now();
        if (request.getId() != null && request.getId() > 0) {
            menuEntity = this.getById(request.getId());
            menuEntity.setUpdateDate(now);
            menuEntity.setUpdaterId(request.getOperateId());
            menuEntity.setUpdaterName(request.getOperateName());
        } else {
            menuEntity.setCreateDate(now);
            menuEntity.setCreaterId(request.getOperateId());
            menuEntity.setCreaterName(request.getOperateName());
        }
        menuEntity.setComponent(request.getComponent());
        menuEntity.setIcon(request.getIcon());
        menuEntity.setMenuName(request.getMenuName());
        menuEntity.setParentMenuId(request.getParentMenuId());
        menuEntity.setPath(request.getPath());
        menuEntity.setSort(request.getSort());
        menuEntity.setType(request.getType());
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(menuEntity)) {
                result = menuEntity.getId();
            }
        } else {
            if (this.save(menuEntity)) {
                result = menuEntity.getId();
            }
        }
        return result;
    }
}
