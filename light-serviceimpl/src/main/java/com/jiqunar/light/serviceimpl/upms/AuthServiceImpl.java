package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.common.RedistUtils;
import com.jiqunar.light.model.entity.upms.*;
import com.jiqunar.light.model.request.upms.LoginRequest;
import com.jiqunar.light.model.response.upms.*;
import com.jiqunar.light.service.upms.*;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
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
            String userKey = userEntity.getId() + "_" + userEntity.getUserName();
            String oldToken = redistUtils.get(userKey);
            if (!StringUtils.isBlank(oldToken)) {
                redistUtils.delete(oldToken);
            }
            String token = UUID.randomUUID().toString().replace("-", "");
            Long expireSecond = 24 * 60 * 60L;
            if (request.getRememberMe() != null && request.getRememberMe()) {
                expireSecond *= 7;
            }
            redistUtils.put(token, userKey, expireSecond);
            redistUtils.put(userKey, token, expireSecond);

            userService.lambdaUpdate()
                    .set(UserEntity::getVisitDate, LocalDateTime.now())
                    .eq(UserEntity::getId, userEntity.getId()).update();

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
            response.setId(userEntity.getId().toString());
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
                    userRole.setId(firstRoleInfo.getId().toString());
                    userRole.setName(firstRoleInfo.getRoleName());

                    List<RoleMenuEntity> roleMenuEntityList = roleMenuService.lambdaQuery()
                            .select(RoleMenuEntity::getMenuId)
                            .in(RoleMenuEntity::getRoleId, roleEntityList.stream().map(RoleEntity::getId).collect(Collectors.toList()))
                            .list();
                    if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
                        List<MenuEntity> menuEntityList = menuService.listByIds(roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).collect(Collectors.toList()));
                        if (CollectionUtils.isNotEmpty(menuEntityList)) {
                            List<UserRolePermission> userRolePermissionList = new ArrayList<>();
                            for (MenuEntity menu : menuEntityList.stream().filter(m -> !m.getType().equals(1)).collect(Collectors.toList())) {
                                UserRolePermission userRolePermission = new UserRolePermission();
                                userRolePermission.setRoleId(userRole.getId());
                                userRolePermission.setPermissionId(menu.getId().toString());
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
     * 获取用户菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserMenuResponse> getUserMenu(Long userId) {
        List<UserMenuResponse> response = new ArrayList<>();
        UserEntity userEntity = userService.getById(userId);
        if (userEntity != null) {
            List<UserRoleEntity> userRoleEntityList = userRoleService.lambdaQuery()
                    .select(UserRoleEntity::getRoleId)
                    .eq(UserRoleEntity::getUserId, userEntity.getId())
                    .list();
            if (CollectionUtils.isNotEmpty(userRoleEntityList)) {
                List<RoleEntity> roleEntityList = roleService.listByIds(userRoleEntityList.stream().map(UserRoleEntity::getRoleId).collect(Collectors.toList()));
                if (CollectionUtils.isNotEmpty(roleEntityList)) {
                    List<RoleMenuEntity> roleMenuEntityList = roleMenuService.lambdaQuery()
                            .select(RoleMenuEntity::getMenuId)
                            .in(RoleMenuEntity::getRoleId, roleEntityList.stream().map(RoleEntity::getId).collect(Collectors.toList()))
                            .list();
                    if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
                        List<MenuEntity> menuEntityList = menuService.listByIds(roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).distinct().collect(Collectors.toList()));
                        if (CollectionUtils.isNotEmpty(menuEntityList)) {
                            response = getUserMenuList(menuEntityList, new ArrayList<>());
                        }
                    }
                }
            }
        }
        return response;
    }

    /**
     * 递归处理用户的系统菜单权限
     *
     * @param menuEntityList
     * @param userMenuResponseList
     * @return
     */
    private List<UserMenuResponse> getUserMenuList(List<MenuEntity> menuEntityList, List<UserMenuResponse> userMenuResponseList) {
        for (MenuEntity menu : menuEntityList.stream().sorted(Comparator.comparingInt(m -> m.getSort())).collect(Collectors.toList())) {
            UserMenuResponse userMenuResponse = new UserMenuResponse();
            userMenuResponse.setKey(menu.getId().toString());
            userMenuResponse.setPath(menu.getPath());
            userMenuResponse.setId(menu.getId());
            userMenuResponse.setParentId(menu.getParentMenuId());
            userMenuResponse.setComponent(menu.getComponent());
            userMenuResponse.setIsLink(menu.getType().equals(2));
            UserMenuMeta meta = new UserMenuMeta();
            meta.setIcon(menu.getIcon());
            meta.setTitle(menu.getMenuName());
            userMenuResponse.setMeta(meta);
            userMenuResponseList.add(userMenuResponse);
        }
        return userMenuResponseList;
    }

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean logout(Long userId) {
        Boolean result = false;
        UserEntity userEntity = userService.getById(userId);
        if (userEntity != null) {
            String userKey = userEntity.getId() + "_" + userEntity.getUserName();
            String oldToken = redistUtils.get(userKey);
            if (!StringUtils.isBlank(oldToken)) {
                redistUtils.delete(oldToken);
            }
            result = redistUtils.delete(userKey);
        }
        return result;
    }
}
