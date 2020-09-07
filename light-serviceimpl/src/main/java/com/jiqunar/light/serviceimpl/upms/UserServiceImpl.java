package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.common.ObjectUtils;
import com.jiqunar.light.dao.upms.DeptMapper;
import com.jiqunar.light.dao.upms.UserRoleMapper;
import com.jiqunar.light.model.entity.upms.DeptEntity;
import com.jiqunar.light.model.entity.upms.UserEntity;
import com.jiqunar.light.dao.upms.UserMapper;
import com.jiqunar.light.model.entity.upms.UserRoleEntity;
import com.jiqunar.light.model.enums.LogSubTypeEnum;
import com.jiqunar.light.model.enums.LogTypeEnum;
import com.jiqunar.light.model.enums.OperateTypeEnum;
import com.jiqunar.light.model.request.upms.UserEditRequest;
import com.jiqunar.light.model.request.upms.UserListRequest;
import com.jiqunar.light.model.response.upms.UserListResponse;
import com.jiqunar.light.service.log.LogService;
import com.jiqunar.light.service.upms.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jiqunar.light.model.response.PageResponse;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 用户 服务实现类
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private LogService logService;

    /**
     * 分页获取用户
     *
     * @param request
     * @return
     */
    @Override
    public PageResponse page(UserListRequest request) {
        IPage<UserListResponse> page = baseMapper.userList(new Page<>(request.getPageNo(), request.getPageSize()), request);
        if (CollectionUtils.isNotEmpty(page.getRecords())) {
            for (UserListResponse user : page.getRecords()) {
                if (!user.getDeptId().equals(0L)) {
                    UserListResponse userTemp = new UserListResponse();
                    userTemp.setDeptId(user.getDeptId());
                    userTemp.setDeptIdList(new ArrayList<>());
                    userTemp.setDeptNameList(new ArrayList<>());
                    getDeptInfoById(userTemp);
                    Collections.reverse(userTemp.getDeptNameList());
                    Collections.reverse(userTemp.getDeptIdList());
                    user.setDeptNameList(userTemp.getDeptNameList());
                    user.setDeptIdList(userTemp.getDeptIdList());
                }
            }
        }
        return new PageResponse(request.getPageNo(), page.getTotal(), page.getRecords());
    }

    private UserListResponse getDeptInfoById(UserListResponse user) {
        DeptEntity deptEntity = deptMapper.selectById(user.getDeptId());
        if (deptEntity != null) {
            user.getDeptIdList().add(deptEntity.getId().toString());
            user.getDeptNameList().add(deptEntity.getDeptName());
            if (!deptEntity.getParentDeptId().equals(0L)) {
                user.setDeptId(deptEntity.getParentDeptId());
                getDeptInfoById(user);
            }
        }
        return user;
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
        if (request.getId() != null && request.getId() > 0) {
            userEntity = this.getById(request.getId());
            userEntity.setUpdateDate(LocalDateTime.now());
            userEntity.setUpdaterId(request.getOperateId());
            userEntity.setUpdaterName(request.getOperateName());
        } else {
            userEntity.setCreateDate(LocalDateTime.now());
            userEntity.setCreaterId(request.getOperateId());
            userEntity.setCreaterName(request.getOperateName());
        }
        userEntity.setAvatarUrl(request.getAvatarUrl());
        userEntity.setEmail(request.getEmail());
        userEntity.setGender(request.getGender());
        userEntity.setNickName(request.getNickName());
        userEntity.setPassword(request.getPassword());
        userEntity.setPhone(request.getPhone());
        userEntity.setUserName(request.getUserName());
        userEntity.setJobId(request.getJobId());
        if (CollectionUtils.isNotEmpty(request.getDeptIdList())) {
            request.setDeptId(request.getDeptIdList().get(request.getDeptIdList().size() - 1));
        }
        userEntity.setDeptId(request.getDeptId());
        OperateTypeEnum operateTypeEnum = OperateTypeEnum.Update;
        if (request.getId() != null && request.getId() > 0) {
            if (this.updateById(userEntity)) {
                result = userEntity.getId();
            }
        } else {
            if (this.save(userEntity)) {
                result = userEntity.getId();
                operateTypeEnum = OperateTypeEnum.Add;
            }
        }
        if (result > 0 && CollectionUtils.isNotEmpty(request.getRoleId())) {
            LambdaQueryWrapper<UserRoleEntity> queryWrapper = new LambdaQueryWrapper<UserRoleEntity>()
                    .eq(UserRoleEntity::getUserId, result);
            List<UserRoleEntity> userRoleEntityList = userRoleMapper.selectList(queryWrapper);
            for (Long roleId : request.getRoleId()) {
                Optional<UserRoleEntity> userRoleEntity = userRoleEntityList.stream().filter(m -> roleId.equals(m.getRoleId())).findFirst();
                if (!userRoleEntity.isPresent()) {
                    UserRoleEntity userRoleEntityNew = new UserRoleEntity();
                    userRoleEntityNew.setRoleId(roleId);
                    userRoleEntityNew.setUserId(result);
                    userRoleEntityNew.setCreateDate(LocalDateTime.now());
                    userRoleEntityNew.setCreaterId(request.getOperateId());
                    userRoleEntityNew.setCreaterName(request.getOperateName());
                    userRoleMapper.insert(userRoleEntityNew);
                } else {
                    userRoleEntityList.remove(userRoleEntity.get());
                }
            }
            if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
                userRoleMapper.deleteBatchIds(userRoleEntityList.stream().map(UserRoleEntity::getId).collect(Collectors.toList()));
            }

            logService.add(operateTypeEnum, result, ObjectUtils.getObject(userEntity), LogTypeEnum.System, LogSubTypeEnum.User, request.getOperateId(), request.getOperateName());
        }
        return result;
    }
}
