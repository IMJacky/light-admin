package com.jiqunar.light.serviceimpl.log;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.model.entity.log.LogEntity;
import com.jiqunar.light.dao.log.LogMapper;
import com.jiqunar.light.model.enums.LogSubTypeEnum;
import com.jiqunar.light.model.enums.LogTypeEnum;
import com.jiqunar.light.model.enums.OperateTypeEnum;
import com.jiqunar.light.model.request.log.LogListRequest;
import com.jiqunar.light.model.response.log.LogListResponse;
import com.jiqunar.light.service.log.LogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志 服务实现类
 *
 * @author auto generator
 * @since 2020-09-07
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity> implements LogService {
    /**
     * 分页获取日志
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(LogListRequest request) {
        IPage<LogEntity> page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<LogEntity> queryWrapper = new QueryWrapper<LogEntity>().lambda();
        List<LogListResponse> logListResponseList = new ArrayList<>();
        if (request.getOperateId() != null) {
            queryWrapper.eq(LogEntity::getCreaterId, request.getOperateId());
        }
        if (StringUtils.isNotBlank(request.getOperateName())) {
            queryWrapper.like(LogEntity::getCreaterName, request.getOperateName());
        }
        if (request.getLogType() != null) {
            queryWrapper.eq(LogEntity::getLogType, request.getLogType());
        }
        if (request.getLogSubType() != null) {
            queryWrapper.eq(LogEntity::getLogSubType, request.getLogSubType());
        }
        if (request.getOperateType() != null) {
            queryWrapper.eq(LogEntity::getOperateType, request.getOperateType());
        }
        if (request.getRelateId() != null) {
            queryWrapper.eq(LogEntity::getRelateId, request.getRelateId());
        }
        if (request.getBeginDate() != null) {
            queryWrapper.gt(LogEntity::getCreateDate, request.getBeginDate());
        }
        if (request.getEndDate() != null) {
            queryWrapper.lt(LogEntity::getCreateDate, request.getEndDate().plusDays(1));
        }
        queryWrapper.orderByDesc(LogEntity::getId);
        page = baseMapper.selectPage(page, queryWrapper);
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            for (LogEntity logEntity : page.getRecords()) {
                LogListResponse logListResponse = new LogListResponse();
                logListResponse.setId(logEntity.getId());
                logListResponse.setLogSubTypeName(LogSubTypeEnum.getMsg(logEntity.getLogSubType()));
                logListResponse.setLogTypeName(LogTypeEnum.getMsg(logEntity.getLogType()));
                logListResponse.setMessage(logEntity.getMessage());
                logListResponse.setOperationTime(logEntity.getCreateDate());
                logListResponse.setRelateId(logEntity.getRelateId());
                logListResponse.setUserId(logEntity.getCreaterId());
                logListResponse.setUserName(logEntity.getCreaterName());
                logListResponse.setOperateTypeName(OperateTypeEnum.getMsg(logEntity.getOperateType()));
                logListResponseList.add(logListResponse);
            }
        }
        return new PageResponse(request.getPageNo(), page.getTotal(), logListResponseList);
    }

    /**
     * 新增日志(不用实体接收了，麻烦)
     *
     * @param operateTypeEnum
     * @param relateId
     * @param message
     * @param logTypeEnum
     * @param logSubTypeEnum
     * @param operaterId
     * @param operaterName
     */
    @Override
    public void add(OperateTypeEnum operateTypeEnum, Long relateId, String message, LogTypeEnum logTypeEnum, LogSubTypeEnum logSubTypeEnum, Long operaterId, String operaterName) {
        LogEntity logEntity = new LogEntity();
        logEntity.setCreateDate(LocalDateTime.now());
        logEntity.setCreaterId(operaterId);
        logEntity.setCreaterName(operaterName);
        logEntity.setLogSubType(logSubTypeEnum.getCode());
        logEntity.setLogType(logTypeEnum.getCode());
        logEntity.setMessage(message);
        logEntity.setOperateType(operateTypeEnum.getCode());
        logEntity.setRelateId(relateId);
        baseMapper.insert(logEntity);
    }
}
