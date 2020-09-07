package com.jiqunar.light.serviceimpl.upms;

import com.jiqunar.light.common.RedistUtils;
import com.jiqunar.light.model.entity.upms.*;
import com.jiqunar.light.model.request.upms.LoginRequest;
import com.jiqunar.light.model.response.upms.*;
import com.jiqunar.light.service.upms.*;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
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
    @Autowired
    private JobService jobService;
    @Autowired
    private DeptService deptService;

    /**
     * 登录
     *
     * @param request
     * @return
     */
    @Override
    public LoginResponse login(LoginRequest request) {
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
            if (!userEntity.getDeptId().equals(0L)) {
                List<String> deptNameList = new ArrayList<>();
                getDeptNameListById(deptNameList, userEntity.getDeptId());
                Collections.reverse(deptNameList);
                response.setDeptNameList(deptNameList);
            }
            if (!userEntity.getJobId().equals(0L)) {
                JobEntity jobEntity = jobService.getById(userEntity.getJobId());
                if (jobEntity != null) {
                    response.setJobName(jobEntity.getJobName());
                }
            }
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
                                List<UserRolePermissionAction> actionEntitySet = new ArrayList<>();
                                for (MenuEntity button : menuEntityList.stream().filter(m -> m.getType().equals(1) && m.getParentMenuId().equals(menu.getId())).collect(Collectors.toList())) {
                                    UserRolePermissionAction userRolePermissionAction = new UserRolePermissionAction();
                                    userRolePermissionAction.setAction(button.getPath());
                                    userRolePermissionAction.setDescribe(button.getMenuName());
                                    actionEntitySet.add(userRolePermissionAction);
                                }
                                userRolePermission.setActionEntitySet(actionEntitySet);
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

    private List<String> getDeptNameListById(List<String> deptNameList, Long deptId) {
        DeptEntity deptEntity = deptService.getById(deptId);
        if (deptEntity != null) {
            deptNameList.add(deptEntity.getDeptName());
            if (!deptEntity.getParentDeptId().equals(0L)) {
                getDeptNameListById(deptNameList, deptEntity.getParentDeptId());
            }
        }
        return deptNameList;
    }

    /**
     * 密码重置
     *
     * @param userId
     * @return
     */
    @Override
    public Boolean passwordReset(Long userId) {
        Boolean result = false;
        UserEntity userEntity = userService.getById(userId);
        if (userEntity != null) {
            String userKey = userEntity.getId() + "_" + userEntity.getUserName();
            String oldToken = redistUtils.get(userKey);
            if (!StringUtils.isBlank(oldToken)) {
                redistUtils.delete(oldToken);
            }
            result = redistUtils.delete(userKey);

            userEntity.setUpdateDate(LocalDateTime.now());
            userEntity.setPassword(DigestUtils.md5Hex(userEntity.getUserName()));
            result = userService.updateById(userEntity);
        }
        return result;
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
     * 用户的系统菜单权限
     *
     * @param menuEntityList
     * @param userMenuResponseList
     * @return
     */
    private List<UserMenuResponse> getUserMenuList(List<MenuEntity> menuEntityList, List<UserMenuResponse> userMenuResponseList) {
        for (MenuEntity menu : menuEntityList.stream().filter(m -> !m.getType().equals(1)).sorted(Comparator.comparingInt(m -> m.getSort())).collect(Collectors.toList())) {
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
     * 获取用户菜单树形结构
     *
     * @param userId
     * @return
     */
    @Override
    public MenuTreeResponse getUserMenuTree(Long userId) {
        MenuTreeResponse response = new MenuTreeResponse();
        List<MenuEntity> menuEntityAllList = menuService.list();
        if (CollectionUtils.isNotEmpty(menuEntityAllList)) {
            List<MenuTree> userMenuTreeList = getUserMenuTree(menuEntityAllList, "0", new ArrayList<>());
            response.setMenuTreeList(userMenuTreeList);
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
                                response.setMenuTreeCheckedList(getUserMenuTreeKeyList(menuEntityList, "0", new ArrayList<>()));
                            }
                        }
                    }
                }
            }
        }
        return response;
    }

    /**
     * 获取角色菜单树形结构
     *
     * @param roleId
     * @return
     */
    @Override
    public MenuTreeResponse getRoleMenuTree(Long roleId) {
        MenuTreeResponse response = new MenuTreeResponse();
        List<MenuEntity> menuEntityAllList = menuService.list();
        if (CollectionUtils.isNotEmpty(menuEntityAllList)) {
            List<MenuTree> userMenuTreeList = getUserMenuTree(menuEntityAllList, "0", new ArrayList<>());
            response.setMenuTreeList(userMenuTreeList);
            RoleEntity roleEntity = roleService.getById(roleId);
            if (roleEntity != null) {
                List<RoleMenuEntity> roleMenuEntityList = roleMenuService.lambdaQuery()
                        .select(RoleMenuEntity::getMenuId)
                        .eq(RoleMenuEntity::getRoleId, roleId)
                        .list();
                if (CollectionUtils.isNotEmpty(roleMenuEntityList)) {
                    List<MenuEntity> menuEntityList = menuService.listByIds(roleMenuEntityList.stream().map(RoleMenuEntity::getMenuId).distinct().collect(Collectors.toList()));
                    if (CollectionUtils.isNotEmpty(menuEntityList)) {
                        response.setMenuTreeCheckedList(getUserMenuTreeKeyList(menuEntityList, "0", new ArrayList<>()));
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
     * @param parentMenuKey
     * @return
     */
    private List<MenuTree> getUserMenuTree(List<MenuEntity> menuEntityList, String parentMenuKey, List<MenuTree> response) {
        String[] parentMenuKeySplit = parentMenuKey.split("-");
        Long parentMenuId = Long.valueOf(parentMenuKeySplit[parentMenuKeySplit.length - 1]);
        for (MenuEntity menu : menuEntityList.stream().filter(m -> m.getParentMenuId().equals(parentMenuId)).sorted(Comparator.comparingInt(m -> m.getSort())).collect(Collectors.toList())) {
            MenuTree responseNew = new MenuTree();
            responseNew.setKey(parentMenuKey + "-" + menu.getId());
            responseNew.setTitle(menu.getMenuName());
            if (menuEntityList.stream().anyMatch(m -> m.getParentMenuId().equals(menu.getId()))) {
                responseNew.setChildren(getUserMenuTree(menuEntityList, responseNew.getKey(), new ArrayList<>()));
            }
            response.add(responseNew);
        }
        return response;
    }

    /**
     * 递归处理用户的系统菜单权限
     *
     * @param menuEntityList
     * @param parentMenuKey
     * @return
     */
    private List<String> getUserMenuTreeKeyList(List<MenuEntity> menuEntityList, String parentMenuKey, List<String> response) {
        String[] parentMenuKeySplit = parentMenuKey.split("-");
        Long parentMenuId = Long.valueOf(parentMenuKeySplit[parentMenuKeySplit.length - 1]);
        for (MenuEntity menu : menuEntityList.stream().filter(m -> m.getParentMenuId().equals(parentMenuId)).sorted(Comparator.comparingInt(m -> m.getSort())).collect(Collectors.toList())) {
            String key = parentMenuKey + "-" + menu.getId();
            if (menuEntityList.stream().anyMatch(m -> m.getParentMenuId().equals(menu.getId()))) {
                getUserMenuTreeKeyList(menuEntityList, key, response);
            } else {
                response.add(key);
            }
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
