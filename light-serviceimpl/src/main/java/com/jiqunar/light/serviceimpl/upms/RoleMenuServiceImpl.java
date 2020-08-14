package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.model.entity.upms.MenuEntity;
import com.jiqunar.light.model.entity.upms.RoleMenuEntity;
import com.jiqunar.light.dao.upms.RoleMenuMapper;
import com.jiqunar.light.model.request.upms.RoleMenuEditRequest;
import com.jiqunar.light.service.upms.RoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 角色菜单 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenuEntity> implements RoleMenuService {
    /**
     * 分页获取角色菜单
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

    /**
     * 编辑角色菜单
     *
     * @param request
     * @return
     */
    @Override
    public Boolean edit(RoleMenuEditRequest request) {
        Boolean result = false;
        List<RoleMenuEntity> roleMenuEntityList = this.lambdaQuery()
                .eq(RoleMenuEntity::getRoleId, request.getRoleId())
                .list();
        for (String role : request.getMenuIdList()) {
            String[] roleMenuKeySplit = role.split("-");
            Long menuId = Long.valueOf(roleMenuKeySplit[roleMenuKeySplit.length - 1]);
            if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
                Optional<RoleMenuEntity> roleMenuEntityOptional = roleMenuEntityList.stream().filter(m -> m.getMenuId().equals(menuId)).findFirst();
                if (roleMenuEntityOptional.isPresent()) {
                    roleMenuEntityList.remove(roleMenuEntityOptional.get());
                    roleMenuEntityOptional.get().setUpdateDate(LocalDateTime.now());
                    roleMenuEntityOptional.get().setUpdaterId(request.getOperateId());
                    roleMenuEntityOptional.get().setUpdaterName(request.getOperateName());
                    result = this.updateById(roleMenuEntityOptional.get());
                } else {
                    RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                    roleMenuEntity.setMenuId(menuId);
                    roleMenuEntity.setRoleId(request.getRoleId());
                    roleMenuEntity.setCreateDate(LocalDateTime.now());
                    roleMenuEntity.setCreaterId(request.getOperateId());
                    roleMenuEntity.setCreaterName(request.getOperateName());
                    result = this.save(roleMenuEntity);
                }
            } else {
                RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
                roleMenuEntity.setMenuId(menuId);
                roleMenuEntity.setRoleId(request.getRoleId());
                roleMenuEntity.setCreateDate(LocalDateTime.now());
                roleMenuEntity.setCreaterId(request.getOperateId());
                roleMenuEntity.setCreaterName(request.getOperateName());
                result = this.save(roleMenuEntity);
            }
        }
        if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
            List<Long> removeIds = roleMenuEntityList.stream().map(RoleMenuEntity::getId).collect(Collectors.toList());
            result = this.removeByIds(removeIds);
        }
        return result;
    }
}
