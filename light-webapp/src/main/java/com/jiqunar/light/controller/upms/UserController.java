package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.request.upms.UserEditRequest;
import com.jiqunar.light.model.request.upms.UserListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.upms.UserEntity;
import com.jiqunar.light.service.upms.UserService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 用户 前端控制器
 *
 * @author auto generator
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/UserEntity")
@Api(tags = "用户相关接口")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 新增用户
     *
     * @param userEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增用户")
    public BaseResponse save(@RequestBody UserEntity userEntity) {
        return BaseResponse.success(userService.save(userEntity));
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除用户")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(userService.removeById(id));
    }

    /**
     * 修改用户
     *
     * @param userEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改用户")
    public BaseResponse update(@RequestBody UserEntity userEntity) {
        return BaseResponse.success(userService.updateById(userEntity));
    }

    /**
     * 查看所有用户
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有用户")
    public BaseResponse list() {
        return BaseResponse.success(userService.list());
    }

    /**
     * 查看单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个用户")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(userService.getById(id));
    }

    /**
     * 查看单个用户
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个用户")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(userService.getById(id));
    }

    /**
     * 分页查看用户
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看用户")
    public BaseResponse page(@RequestBody UserListRequest request) {
        return BaseResponse.success(userService.page(request));
    }

    /**
     * 编辑用户
     *
     * @param request
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation("编辑用户")
    public BaseResponse edit(@RequestBody UserEditRequest request) {
        return BaseResponse.success(userService.edit(request));
    }
}