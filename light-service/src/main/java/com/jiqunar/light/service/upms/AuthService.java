package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.request.upms.LoginRequest;
import com.jiqunar.light.model.response.upms.LoginResponse;
import com.jiqunar.light.model.response.upms.UserInfoResponse;
import com.jiqunar.light.model.response.upms.UserMenuResponse;
import com.jiqunar.light.model.response.upms.UserMenuTreeResponse;

import java.util.List;

/**
 * 权限服务定义
 *
 * @author jieguang.wang
 * @date 2020/6/2 17:26
 */
public interface AuthService {
    /**
     * 登录
     *
     * @param request
     * @return
     */
    LoginResponse login(LoginRequest request);

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfoResponse getUserInfo(Long userId);

    /**
     * 获取用户菜单
     *
     * @param userId
     * @return
     */
    List<UserMenuResponse> getUserMenu(Long userId);

    /**
     * 获取用户菜单树形结构
     *
     * @param userId
     * @return
     */
    UserMenuTreeResponse getUserMenuTree(Long userId);

    /**
     * 退出登录
     *
     * @param userId
     * @return
     */
    Boolean logout(Long userId);
}
