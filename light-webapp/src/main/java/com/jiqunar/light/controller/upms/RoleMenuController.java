package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.upms.RoleMenuEntity;
import com.jiqunar.light.service.upms.RoleMenuService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 角色菜单 前端控制器
 *
 * @author auto generator
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/RoleMenuEntity")
@Api(tags = "角色菜单相关接口")
public class RoleMenuController extends BaseController {
    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * 新增角色菜单
     *
     * @param roleMenuEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增角色菜单")
    public BaseResponse save(@RequestBody RoleMenuEntity roleMenuEntity) {
        return BaseResponse.success(roleMenuService.save(roleMenuEntity));
    }

    /**
     * 删除角色菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除角色菜单")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(roleMenuService.removeById(id));
    }

    /**
     * 修改角色菜单
     *
     * @param roleMenuEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改角色菜单")
    public BaseResponse update(@RequestBody RoleMenuEntity roleMenuEntity) {
        return BaseResponse.success(roleMenuService.updateById(roleMenuEntity));
    }

    /**
     * 查看所有角色菜单
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有角色菜单")
    public BaseResponse list() {
        return BaseResponse.success(roleMenuService.list());
    }

    /**
     * 查看单个角色菜单
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个角色菜单")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(roleMenuService.getById(id));
    }

    /**
     * 查看单个角色菜单
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个角色菜单")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(roleMenuService.getById(id));
    }

    /**
     * 分页查看角色菜单
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看角色菜单")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(roleMenuService.page(request));
    }
}