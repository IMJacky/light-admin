package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiqunar.light.common.ObjectUtils;
import com.jiqunar.light.dao.upms.MenuMapper;
import com.jiqunar.light.model.entity.upms.MenuEntity;
import com.jiqunar.light.model.enums.LogSubTypeEnum;
import com.jiqunar.light.model.enums.LogTypeEnum;
import com.jiqunar.light.model.enums.OperateTypeEnum;
import com.jiqunar.light.model.request.upms.MenuEditRequest;
import com.jiqunar.light.model.request.upms.MenuListRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.jiqunar.light.model.response.upms.MenuListResponse;
import com.jiqunar.light.service.log.LogService;
import com.jiqunar.light.service.upms.MenuService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 菜单 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, MenuEntity> implements MenuService {
    @Autowired
    private LogService logService;

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
        if (request.getParentMenuId() != null) {
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
     * 获取部门列表
     *
     * @param request
     * @return
     */
    @Override
    public List<MenuListResponse> getMenuList(MenuListRequest request) {
        List<MenuListResponse> response = new ArrayList<>();
        LambdaQueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<MenuEntity>().lambda();
        queryWrapper.orderByDesc(MenuEntity::getId);
        if (request.getId() != null && request.getId() > 0) {
            queryWrapper.eq(MenuEntity::getId, request.getId());
        }
        if (request.getParentMenuId() != null) {
            queryWrapper.eq(MenuEntity::getParentMenuId, request.getParentMenuId());
        }
        if (request.getType() != null) {
            queryWrapper.eq(MenuEntity::getType, request.getType());
        }
        if (StringUtils.isNotBlank(request.getMenuName())) {
            queryWrapper.like(MenuEntity::getMenuName, request.getMenuName());
        }
        List<MenuEntity> menuEntityList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(menuEntityList)) {
            response = getMenuList(menuEntityList, 0L, new ArrayList<>());
        }
        return response;
    }

    private List<MenuListResponse> getMenuList(List<MenuEntity> menuEntityList, Long parentId, List<MenuListResponse> menuListResponseList) {
        for (MenuEntity menuEntity : menuEntityList.stream().filter(m -> parentId.equals(m.getParentMenuId())).collect(Collectors.toList())) {
            MenuListResponse menuListResponse = new MenuListResponse();
            BeanCopier beanCopier = BeanCopier.create(MenuEntity.class, MenuListResponse.class, false);
            beanCopier.copy(menuEntity, menuListResponse, null);
            if (menuEntityList.stream().anyMatch(m -> m.getParentMenuId().equals(menuEntity.getId()))) {
                menuListResponse.setChildren(getMenuList(menuEntityList, menuEntity.getId(), new ArrayList<>()));
            }
            menuListResponseList.add(menuListResponse);
        }
        return menuListResponseList;
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
        OperateTypeEnum operateTypeEnum = OperateTypeEnum.Update;
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(menuEntity)) {
                result = menuEntity.getId();
            }
        } else {
            if (this.save(menuEntity)) {
                result = menuEntity.getId();
                operateTypeEnum = OperateTypeEnum.Add;
            }
        }
        if (result > 0) {
            logService.add(operateTypeEnum, result, ObjectUtils.getObject(menuEntity), LogTypeEnum.System, LogSubTypeEnum.Menu, request.getOperateId(), request.getOperateName());
        }
        return result;
    }

    /**
     * 查看所有菜单
     *
     * @return
     */
    @Override
    public Map<Long, String> mapAll() {
        Map<Long, String> result = new HashMap<>();
        result.put(0L, "顶级菜单");
        LambdaQueryWrapper<MenuEntity> queryWrapper = new QueryWrapper<MenuEntity>().lambda();
        queryWrapper.orderByDesc(MenuEntity::getId);
        queryWrapper.eq(MenuEntity::getType, 0);
        List<MenuEntity> menuEntityList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(menuEntityList)) {
            menuEntityList.stream().forEach(m -> {
                result.put(m.getId(), m.getMenuName());
            });
        }
        return result;
    }
}
