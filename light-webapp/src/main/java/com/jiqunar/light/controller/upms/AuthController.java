package com.jiqunar.light.controller.upms;

import com.alibaba.druid.util.DruidWebUtils;
import com.jiqunar.light.controller.BaseController;
import com.jiqunar.light.model.request.upms.LoginRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.upms.*;
import com.jiqunar.light.service.upms.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 权限相关接口
 *
 * @author jieguang.wang
 * @date 2020/6/2 17:17
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "权限相关接口")
@Validated
public class AuthController extends BaseController {
    @Autowired
    private AuthService authService;
    @Resource
    private HttpServletRequest httpServletRequest;

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public BaseResponse<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
        String ipAddress = DruidWebUtils.getRemoteAddr(httpServletRequest);
        String userAgent = httpServletRequest.getHeader("user-agent");
        request.setLogMessage(String.format("IP地址：%s\n浏览器信息：%s", ipAddress, userAgent));
        return BaseResponse.success(authService.login(request));
    }

    /**
     * 退出
     *
     * @return
     */
    @GetMapping("/logout")
    @ApiOperation("退出")
    public BaseResponse logout() {
        return BaseResponse.success(authService.logout(baseRequest().getOperateId()));
    }

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping("/userInfo")
    @ApiOperation("获取用户信息")
    public BaseResponse getUserInfo() {
        return BaseResponse.success(authService.getUserInfo(baseRequest().getOperateId()));
    }

    /**
     * 密码重置
     *
     * @return
     */
    @GetMapping("/passwordReset")
    @ApiOperation("密码重置")
    public BaseResponse passwordReset(Long userId) {
        return BaseResponse.success(authService.passwordReset(userId));
    }

    /**
     * 获取用户菜单
     *
     * @return
     */
    @GetMapping("/userMenu")
    @ApiOperation("获取用户菜单")
    public BaseResponse getUserMenu() {
        return BaseResponse.success(authService.getUserMenu(baseRequest().getOperateId()));
    }

    /**
     * 获取用户菜单树形结构
     *
     * @return
     */
    @GetMapping("/userMenuTree")
    @ApiOperation("获取用户菜单树形结构")
    public BaseResponse getUserMenuTree() {
        return BaseResponse.success(authService.getUserMenuTree(baseRequest().getOperateId()));
    }

    /**
     * 获取角色菜单树形结构
     *
     * @return
     */
    @GetMapping("/roleMenuTree")
    @ApiOperation("获取用户菜单树形结构")
    public BaseResponse getRoleMenuTree(Long roleId) {
        return BaseResponse.success(authService.getRoleMenuTree(roleId));
    }
}
