package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.model.entity.upms.UserEntity;
import com.jiqunar.light.dao.upms.UserMapper;
import com.jiqunar.light.model.request.upms.UserEditRequest;
import com.jiqunar.light.model.request.upms.UserListRequest;
import com.jiqunar.light.service.upms.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;

/**
 * 用户 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
    /**
     * 分页获取用户
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(UserListRequest request) {
        IPage page = new Page<>(request.getPageNo(), request.getPageSize());
        LambdaQueryWrapper<UserEntity> queryWrapper = new QueryWrapper<UserEntity>().lambda();
        queryWrapper.orderByDesc(UserEntity::getId);
        if (request.getId() != null && request.getId() > 0) {
            queryWrapper.eq(UserEntity::getId, request.getId());
        }
        if (StringUtils.isNotBlank(request.getPhone())) {
            queryWrapper.eq(UserEntity::getPhone, request.getPhone());
        }
        if (StringUtils.isNotBlank(request.getUserName())) {
            queryWrapper.eq(UserEntity::getUserName, request.getUserName());
        }
        page = this.page(page, queryWrapper);
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }

    /**
     * 编辑用户
     *
     * @param request
     * @return
     */
    @Override
    public Long edit(UserEditRequest request) {
        UserEntity userEntity = new UserEntity();
        Long result = Long.valueOf(0);
        LocalDateTime now = LocalDateTime.now();
        if (request.getId() != null && request.getId() > 0) {
            userEntity = this.getById(request.getId());
            userEntity.setUpdateDate(now);
            userEntity.setUpdaterId(request.getOperateId());
            userEntity.setUpdaterName(request.getOperateName());
        } else {
            userEntity.setCreateDate(now);
            userEntity.setCreaterId(request.getOperateId());
            userEntity.setCreaterName(request.getOperateName());
        }
        userEntity.setAvatarUrl(request.getAvatarUrl());
        userEntity.setDeptId(request.getDeptId());
        userEntity.setEmail(request.getEmail());
        userEntity.setGender(request.getGender());
        userEntity.setJobId(request.getJobId());
        userEntity.setNickName(request.getNickName());
        userEntity.setPassword(request.getPassword());
        userEntity.setPhone(request.getPhone());
        userEntity.setUserName(request.getUserName());
        userEntity.setJobId(request.getJobId());
        userEntity.setDeptId(request.getDeptId());
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(userEntity)) {
                result = userEntity.getId();
            }
        } else {
            if (this.save(userEntity)) {
                result = userEntity.getId();
            }
        }
        return result;
    }
}
