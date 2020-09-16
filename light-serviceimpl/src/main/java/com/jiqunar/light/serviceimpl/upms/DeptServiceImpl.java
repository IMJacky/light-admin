package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiqunar.light.common.ObjectUtils;
import com.jiqunar.light.dao.upms.DeptMapper;
import com.jiqunar.light.model.entity.upms.DeptEntity;
import com.jiqunar.light.model.enums.LogSubTypeEnum;
import com.jiqunar.light.model.enums.LogTypeEnum;
import com.jiqunar.light.model.enums.OperateTypeEnum;
import com.jiqunar.light.model.request.upms.DeptEditRequest;
import com.jiqunar.light.model.request.upms.DeptListRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.jiqunar.light.model.response.common.CascadeResponse;
import com.jiqunar.light.model.response.upms.DeptListResponse;
import com.jiqunar.light.service.log.LogService;
import com.jiqunar.light.service.upms.DeptService;
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
 * 部门 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, DeptEntity> implements DeptService {
    @Autowired
    private LogService logService;

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
     * 获取部门列表
     *
     * @param request
     * @return
     */
    @Override
    public List<DeptListResponse> getDeptList(DeptListRequest request) {
        List<DeptListResponse> response = new ArrayList<>();
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
        List<DeptEntity> deptEntityList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(deptEntityList)) {
            response = getDeptList(deptEntityList, 0L, new ArrayList<>());
        }
        return response;
    }

    private List<DeptListResponse> getDeptList(List<DeptEntity> deptEntityList, Long parentId, List<DeptListResponse> deptListResponseList) {
        for (DeptEntity deptEntity : deptEntityList.stream().filter(m -> parentId.equals(m.getParentDeptId())).collect(Collectors.toList())) {
            DeptListResponse deptListResponse = new DeptListResponse();
            BeanCopier beanCopier = BeanCopier.create(DeptEntity.class, DeptListResponse.class, false);
            beanCopier.copy(deptEntity, deptListResponse, null);
            if (deptEntityList.stream().anyMatch(m -> m.getParentDeptId().equals(deptEntity.getId()))) {
                deptListResponse.setChildren(getDeptList(deptEntityList, deptEntity.getId(), new ArrayList<>()));
            }
            deptListResponseList.add(deptListResponse);
        }
        return deptListResponseList;
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
        OperateTypeEnum operateTypeEnum = OperateTypeEnum.Update;
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(deptEntity)) {
                result = deptEntity.getId();
            }
        } else {
            if (this.save(deptEntity)) {
                result = deptEntity.getId();
                operateTypeEnum = OperateTypeEnum.Add;
            }
        }
        if (result > 0) {
            logService.add(operateTypeEnum, result, ObjectUtils.getObject(deptEntity), LogTypeEnum.System, LogSubTypeEnum.Dept, request.getOperateId(), request.getOperateName());
        }
        return result;
    }

    /**
     * 查看所有父级部门
     *
     * @return
     */
    @Override
    public Map<Long, String> mapAll() {
        Map<Long, String> result = new HashMap<>();
        result.put(0L, "顶级部门");
        LambdaQueryWrapper<DeptEntity> queryWrapper = new QueryWrapper<DeptEntity>().lambda();
        queryWrapper.orderByDesc(DeptEntity::getId);
        List<DeptEntity> deptEntityList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(deptEntityList)) {
            deptEntityList.stream().forEach(m -> {
                result.put(m.getId(), m.getDeptName());
            });
        }
        return result;
    }

    /**
     * 查看所有部门(级联方式)
     *
     * @return
     */
    @Override
    public List<CascadeResponse> cascadeAll() {
        List<CascadeResponse> response = new ArrayList<>();
        LambdaQueryWrapper<DeptEntity> queryWrapper = new QueryWrapper<DeptEntity>().lambda();
        queryWrapper.orderByDesc(DeptEntity::getId);
        List<DeptEntity> deptEntityList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(deptEntityList)) {
            response = getCascade(deptEntityList, 0L, new ArrayList<>());
        }
        return response;
    }

    private List<CascadeResponse> getCascade(List<DeptEntity> deptEntityList, Long parentId, List<CascadeResponse> cascadeResponseList) {
        for (DeptEntity deptEntity : deptEntityList.stream().filter(m -> parentId.equals(m.getParentDeptId())).collect(Collectors.toList())) {
            CascadeResponse cascadeResponse = new CascadeResponse();
            cascadeResponse.setLabel(deptEntity.getDeptName());
            cascadeResponse.setValue(deptEntity.getId().toString());
            if (deptEntityList.stream().anyMatch(m -> m.getParentDeptId().equals(deptEntity.getId()))) {
                cascadeResponse.setChildren(getCascade(deptEntityList, deptEntity.getId(), new ArrayList<>()));
            }
            cascadeResponseList.add(cascadeResponse);
        }
        return cascadeResponseList;
    }
}
