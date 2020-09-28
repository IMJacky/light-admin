package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiqunar.light.common.ObjectUtils;
import com.jiqunar.light.dao.upms.DictMapper;
import com.jiqunar.light.model.entity.upms.DictEntity;
import com.jiqunar.light.model.enums.LogSubTypeEnum;
import com.jiqunar.light.model.enums.LogTypeEnum;
import com.jiqunar.light.model.enums.OperateTypeEnum;
import com.jiqunar.light.model.request.upms.DictConfigRequest;
import com.jiqunar.light.model.request.upms.DictConfigSaveRequest;
import com.jiqunar.light.model.request.upms.DictEditRequest;
import com.jiqunar.light.model.request.upms.DictListRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.jiqunar.light.model.response.upms.DictConfigResponse;
import com.jiqunar.light.service.log.LogService;
import com.jiqunar.light.service.upms.DictService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 字典 服务实现类
 *
 * @author auto generator
 * @since 2020-09-25
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictMapper, DictEntity> implements DictService {
    @Autowired
    private LogService logService;

    /**
     * 分页获取字典
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(DictListRequest request) {
        IPage page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<DictEntity> queryWrapper = new QueryWrapper<DictEntity>().lambda();
        queryWrapper.orderByDesc(DictEntity::getId);
        queryWrapper.eq(DictEntity::getParentDictCode, "");
        if (request.getId() != null && request.getId() > 0) {
            queryWrapper.eq(DictEntity::getId, request.getId());
        }
        if (StringUtils.isNotBlank(request.getDictName())) {
            queryWrapper.like(DictEntity::getDictName, request.getDictName());
        }
        if (StringUtils.isNotBlank(request.getDictCode())) {
            queryWrapper.like(DictEntity::getDictCode, request.getDictCode());
        }
        page = this.page(page, queryWrapper);
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }

    /**
     * 编辑字典
     *
     * @param request
     * @return
     */
    @Override
    public Long edit(DictEditRequest request) {
        DictEntity dictEntity = new DictEntity();
        Long result = Long.valueOf(0);
        LocalDateTime now = LocalDateTime.now();
        if (request.getId() != null && request.getId() > 0) {
            dictEntity = this.getById(request.getId());
            dictEntity.setUpdateDate(now);
            dictEntity.setUpdaterId(request.getOperateId());
            dictEntity.setUpdaterName(request.getOperateName());
        } else {
            dictEntity.setCreateDate(now);
            dictEntity.setCreaterId(request.getOperateId());
            dictEntity.setCreaterName(request.getOperateName());
        }
        dictEntity.setDictCode(request.getDictCode());
        dictEntity.setDictName(request.getDictName());
        OperateTypeEnum operateTypeEnum = OperateTypeEnum.Update;
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(dictEntity)) {
                result = dictEntity.getId();
            }
        } else {
            if (this.save(dictEntity)) {
                result = dictEntity.getId();
                operateTypeEnum = OperateTypeEnum.Add;
            }
        }
        if (result > 0) {
            logService.add(operateTypeEnum, result, ObjectUtils.getObject(dictEntity), LogTypeEnum.System, LogSubTypeEnum.Dict, request.getOperateId(), request.getOperateName());
        }
        return result;
    }

    /**
     * 获取字典配置
     *
     * @param request
     * @return
     */
    @Override
    public DictConfigResponse dictConfig(DictConfigRequest request) {
        DictConfigResponse response = new DictConfigResponse();
        List<DictConfigResponse.DictConfigItem> dictCodeList = new ArrayList<>();
        response.setDictCode(request.getDictCode());
        LambdaQueryWrapper<DictEntity> queryWrapper = new QueryWrapper<DictEntity>().lambda();
        queryWrapper.orderByAsc(DictEntity::getId);
        queryWrapper.eq(DictEntity::getParentDictCode, request.getDictCode());
        List<DictEntity> dictEntityList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(dictEntityList)) {
            dictEntityList.forEach(m -> {
                DictConfigResponse.DictConfigItem dictConfigItem = new DictConfigResponse.DictConfigItem();
                dictConfigItem.setDictCode(m.getDictCode());
                dictConfigItem.setDictName(m.getDictName());
                dictCodeList.add(dictConfigItem);
            });
        }
        response.setDictConfigList(dictCodeList);
        return response;
    }

    /**
     * 保存字典配置
     *
     * @param request
     * @return
     */
    @Override
    public Boolean dictConfigSave(DictConfigSaveRequest request) {
        AtomicReference<Boolean> response = new AtomicReference<>(false);
        LambdaQueryWrapper<DictEntity> queryWrapper = new QueryWrapper<DictEntity>().lambda();
        queryWrapper.eq(DictEntity::getParentDictCode, request.getDictCode());
        List<DictEntity> dictEntityList = this.list(queryWrapper);
        if (CollectionUtils.isNotEmpty(dictEntityList)) {
            dictEntityList.forEach(m -> {
                this.removeById(m.getId());
            });
        }
        request.getDictConfigList().forEach(m -> {
            DictEntity dictEntity = new DictEntity();
            dictEntity.setCreateDate(LocalDateTime.now());
            dictEntity.setCreaterId(request.getOperateId());
            dictEntity.setCreaterName(request.getOperateName());
            dictEntity.setParentDictCode(request.getDictCode());
            dictEntity.setDictCode(m.getDictCode());
            dictEntity.setDictName(m.getDictName());
            response.set(this.save(dictEntity));
            if (response.get()) {
                logService.add(OperateTypeEnum.Add, dictEntity.getId(), ObjectUtils.getObject(dictEntity), LogTypeEnum.System, LogSubTypeEnum.Dict, request.getOperateId(), request.getOperateName());
            }
        });
        return response.get();
    }
}
