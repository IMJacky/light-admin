package com.jiqunar.light.controller.upms;

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

import javax.validation.constraints.NotNull;

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

    /**
     * 登录
     *
     * @return
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public BaseResponse<LoginResponse> login(@RequestBody @Validated LoginRequest request) {
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
}
