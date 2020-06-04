package com.jiqunar.light.serviceimpl.upms;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiqunar.light.common.DateUtils;
import com.jiqunar.light.common.RedistUtils;
import com.jiqunar.light.model.entity.upms.*;
import com.jiqunar.light.model.request.upms.LoginRequest;
import com.jiqunar.light.model.response.upms.LoginResponse;
import com.jiqunar.light.model.response.upms.UserInfoResponse;
import com.jiqunar.light.model.response.upms.UserRole;
import com.jiqunar.light.model.response.upms.UserRolePermission;
import com.jiqunar.light.service.upms.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author jieguang.wang
 * @date 2020/6/2 17:27
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private RedistUtils redistUtils;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMenuService roleMenuService;
    @Autowired
    private MenuService menuService;

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @Override
    public LoginResponse login(LoginRequest request) {
        //String aa = DigestUtils.md5Hex(request.getUsername());
        LoginResponse response = new LoginResponse();
        UserEntity userEntity = userService.lambdaQuery()
                .eq(UserEntity::getUserName, request.getUsername())
                .eq(UserEntity::getPassword, request.getPassword()).one();
        if (userEntity != null) {
            String oldToken = redistUtils.get(userEntity.getId().toString());
            if (!StringUtils.isBlank(oldToken)) {
                redistUtils.delete(oldToken);
            }
            String token = UUID.randomUUID().toString().replace("-", "");
            Long expireSecond = 7 * 24 * 60 * 60L;
            redistUtils.put(token, userEntity.getId().toString(), expireSecond);
            redistUtils.put(userEntity.getId().toString(), token, expireSecond);

            userService.lambdaUpdate()
                    .set(UserEntity::getVisitDate, LocalDateTime.now())
                    .eq(UserEntity::getId, userEntity.getId()).update();

            response.setUserId(userEntity.getId());
            response.setToken(token);
        }
        return response;
    }

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfoResponse getUserInfo(Long userId) {
        UserInfoResponse response = new UserInfoResponse();
        UserEntity userEntity = userService.getById(userId);
        if (userEntity != null) {
            response.setAvatar(userEntity.getAvatarUrl());
            response.setCreateTime(userEntity.getCreateDate());
            response.setCreatorId(userEntity.getCreaterId());
            response.setCreatorName(userEntity.getCreaterName());
            response.setDeleted(userEntity.getIsDeleted());
            response.setGender(userEntity.getGender());
            response.setId(userEntity.getId());
            response.setLastLoginTime(userEntity.getVisitDate());
            response.setName(userEntity.getUserName());
            response.setNickname(userEntity.getNickName());
            response.setTelephone(userEntity.getPhone());
            response.setUsername(userEntity.getUserName());
            List<UserRoleEntity> userRoleEntityList = userRoleService.lambdaQuery()
                    .select(UserRoleEntity::getRoleId)
                    .eq(UserRoleEntity::getUserId, userEntity.getId())
                    .list();
            if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
                List<RoleEntity> roleEntityList = roleService.listByIds(userRoleEntityList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList()));
                if (CollectionUtils.isNotEmpty(roleEntityList)) {
                    UserRole userRole = new UserRole();
                    RoleEntity firstRoleInfo = roleEntityList.get(0);
                    userRole.setCreateTime(firstRoleInfo.getCreateDate());
                    userRole.setCreatorId(firstRoleInfo.getCreaterId());
                    userRole.setCreatorName(firstRoleInfo.getCreaterName());
                    userRole.setDeleted(firstRoleInfo.getIsDeleted());
                    userRole.setDescribe(firstRoleInfo.getDescription());
                    userRole.setId(firstRoleInfo.getId());
                    userRole.setName(firstRoleInfo.getRoleName());

                    List<RoleMenuEntity> roleMenuEntityList = roleMenuService.lambdaQuery()
                            .select(RoleMenuEntity::getMenuId)
                            .in(RoleMenuEntity::getRoleId, roleEntityList.stream().map(RoleEntity::getId).collect(Collectors.toList()))
                            .list();
                    if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
                        List<MenuEntity> menuEntityList = menuService.listByIds(roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList()));
                        if (CollectionUtils.isNotEmpty(menuEntityList)) {
                            List<UserRolePermission> userRolePermissionList = new ArrayList<>();
                            for (MenuEntity menu : menuEntityList.stream().filter(m -> m.getType().equals(0)).collect(Collectors.toList())) {
                                UserRolePermission userRolePermission = new UserRolePermission();
                                userRolePermission.setRoleId(userRole.getId());
                                userRolePermission.setPermissionId(menu.getMenuKey());
                                userRolePermission.setPermissionName(menu.getMenuName());
                                if (!userRolePermissionList.stream().anyMatch(m -> m.getPermissionId().equals(menu.getId()))) {
                                    userRolePermissionList.add(userRolePermission);
                                }
                            }
                            userRole.setPermissions(userRolePermissionList);
                        }
                    }
                    response.setRole(userRole);
                }
            }
            //response.setRole();
        }
        return response;
    }

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean logout(Long userId) {
        String oldToken = redistUtils.get(userId.toString());
        if (!StringUtils.isBlank(oldToken)) {
            redistUtils.delete(oldToken);
        }
        return redistUtils.delete(userId.toString());
    }
}
