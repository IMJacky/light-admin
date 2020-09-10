package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.request.upms.RoleEditRequest;
import com.jiqunar.light.model.request.upms.RoleListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.upms.RoleEntity;
import com.jiqunar.light.service.upms.RoleService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 角色 前端控制器
 *
 * @author auto generator
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/RoleEntity")
@Api(tags = "角色相关接口")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;

    /**
     * 新增角色
     *
     * @param roleEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增角色")
    public BaseResponse save(@RequestBody RoleEntity roleEntity) {
        return BaseResponse.success(roleService.save(roleEntity));
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除角色")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(roleService.removeById(id));
    }

    /**
     * 修改角色
     *
     * @param roleEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改角色")
    public BaseResponse update(@RequestBody RoleEntity roleEntity) {
        return BaseResponse.success(roleService.updateById(roleEntity));
    }

    /**
     * 查看所有角色
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有角色")
    public BaseResponse list() {
        return BaseResponse.success(roleService.list());
    }

    /**
     * 查看单个角色
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个角色")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(roleService.getById(id));
    }

    /**
     * 查看单个角色
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个角色")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(roleService.getById(id));
    }

    /**
     * 分页查看角色
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看角色")
    public BaseResponse page(@RequestBody RoleListRequest request) {
        return BaseResponse.success(roleService.page(request));
    }

    /**
     * 编辑角色
     *
     * @param request
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation("编辑角色")
    public BaseResponse edit(@RequestBody RoleEditRequest request) {
        return BaseResponse.success(roleService.edit(request));
    }
}