package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.model.entity.upms.RoleEntity;
import com.jiqunar.light.dao.upms.RoleMapper;
import com.jiqunar.light.model.request.upms.RoleEditRequest;
import com.jiqunar.light.model.request.upms.RoleListRequest;
import com.jiqunar.light.service.upms.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;

/**
 * 角色 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, RoleEntity> implements RoleService {
    /**
     * 分页获取角色
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(RoleListRequest request) {
        IPage page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<RoleEntity> queryWrapper = new QueryWrapper<RoleEntity>().lambda();
        queryWrapper.orderByDesc(RoleEntity::getId);
        if (request.getId() != null && request.getId() > 0) {
            queryWrapper.eq(RoleEntity::getId, request.getId());
        }
        if (StringUtils.isNotBlank(request.getDescription())) {
            queryWrapper.like(RoleEntity::getDescription, request.getDescription());
        }
        if (StringUtils.isNotBlank(request.getRoleName())) {
            queryWrapper.eq(RoleEntity::getRoleName, request.getRoleName());
        }
        page = this.page(page, queryWrapper);
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }

    /**
     * 编辑角色
     *
     * @param request
     * @return
     */
    @Override
    public Long edit(RoleEditRequest request) {
        RoleEntity roleEntity = new RoleEntity();
        Long result = Long.valueOf(0);
        LocalDateTime now = LocalDateTime.now();
        if (request.getId() != null && request.getId() > 0) {
            roleEntity = this.getById(request.getId());
            roleEntity.setUpdateDate(now);
            roleEntity.setUpdaterId(request.getOperateId());
            roleEntity.setUpdaterName(request.getOperateName());
        } else {
            roleEntity.setCreateDate(now);
            roleEntity.setCreaterId(request.getOperateId());
            roleEntity.setCreaterName(request.getOperateName());
        }
        roleEntity.setDescription(request.getDescription());
        roleEntity.setRoleName(request.getRoleName());
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(roleEntity)) {
                result = roleEntity.getId();
            }
        } else {
            if (this.save(roleEntity)) {
                result = roleEntity.getId();
            }
        }
        return result;
    }
}
