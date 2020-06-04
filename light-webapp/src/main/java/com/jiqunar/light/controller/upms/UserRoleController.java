package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.upms.UserRoleEntity;
import com.jiqunar.light.service.upms.UserRoleService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 用户角色 前端控制器
 *
 * @author auto generator
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/UserRoleEntity")
@Api(tags = "用户角色相关接口")
public class UserRoleController extends BaseController {
    @Autowired
    private UserRoleService userRoleService;

    /**
     * 新增用户角色
     *
     * @param userRoleEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增用户角色")
    public BaseResponse save(@RequestBody UserRoleEntity userRoleEntity) {
        return BaseResponse.success(userRoleService.save(userRoleEntity));
    }

    /**
     * 删除用户角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除用户角色")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(userRoleService.removeById(id));
    }

    /**
     * 修改用户角色
     *
     * @param userRoleEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改用户角色")
    public BaseResponse update(@RequestBody UserRoleEntity userRoleEntity) {
        return BaseResponse.success(userRoleService.updateById(userRoleEntity));
    }

    /**
     * 查看所有用户角色
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有用户角色")
    public BaseResponse list() {
        return BaseResponse.success(userRoleService.list());
    }

    /**
     * 查看单个用户角色
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个用户角色")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(userRoleService.getById(id));
    }

    /**
     * 查看单个用户角色
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个用户角色")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(userRoleService.getById(id));
    }

    /**
     * 分页查看用户角色
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看用户角色")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(userRoleService.page(request));
    }
}