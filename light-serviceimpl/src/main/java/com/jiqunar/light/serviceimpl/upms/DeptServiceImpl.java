package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.model.entity.upms.DeptEntity;
import com.jiqunar.light.dao.upms.DeptMapper;
import com.jiqunar.light.model.entity.upms.MenuEntity;
import com.jiqunar.light.model.request.upms.DeptEditRequest;
import com.jiqunar.light.model.request.upms.DeptListRequest;
import com.jiqunar.light.service.upms.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public PageResponse page(DeptListRequest request) {
        IPage page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<DeptEntity> queryWrapper = new QueryWrapper<DeptEntity>().lambda();
        queryWrapper.orderByDesc(DeptEntity::getId);
        if (request.getId() != null && request.getId() > 0) {
            queryWrapper.eq(DeptEntity::getId, request.getId());
        }
        if (request.getParentDeptId() != null) {
            queryWrapper.eq(DeptEntity::getParentDeptId, request.getParentDeptId());
        }
        if (StringUtils.isNotBlank(request.getDeptName())) {
            queryWrapper.like(DeptEntity::getDeptName, request.getDeptName());
        }
        page = this.page(page, queryWrapper);
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }

    /**
     * 编辑部门
     *
     * @param request
     * @return
     */
    @Override
    public Long edit(DeptEditRequest request) {
        DeptEntity deptEntity = new DeptEntity();
        Long result = Long.valueOf(0);
        LocalDateTime now = LocalDateTime.now();
        if (request.getId() != null && request.getId() > 0) {
            deptEntity = this.getById(request.getId());
            deptEntity.setUpdateDate(now);
            deptEntity.setUpdaterId(request.getOperateId());
            deptEntity.setUpdaterName(request.getOperateName());
        } else {
            deptEntity.setCreateDate(now);
            deptEntity.setCreaterId(request.getOperateId());
            deptEntity.setCreaterName(request.getOperateName());
        }
        deptEntity.setDeptName(request.getDeptName());
        deptEntity.setParentDeptId(request.getParentDeptId());
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(deptEntity)) {
                result = deptEntity.getId();
            }
        } else {
            if (this.save(deptEntity)) {
                result = deptEntity.getId();
            }
        }
        return result;
    }

    /**
     * 查看所有父级部门
     *
     * @return
     */
    @Override
    public Map<Long, String> listParent() {
        Map<Long, String> result = new HashMap<>();
        result.put(0L, "顶级部门");
        LambdaQueryWrapper<DeptEntity> queryWrapper = new QueryWrapper<DeptEntity>().lambda();
        queryWrapper.orderByDesc(DeptEntity::getId);
        List<DeptEntity> menuEntityList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(menuEntityList)) {
            menuEntityList.stream().forEach(m -> {
                result.put(m.getId(), m.getDeptName());
            });
        }
        return result;
    }
}
