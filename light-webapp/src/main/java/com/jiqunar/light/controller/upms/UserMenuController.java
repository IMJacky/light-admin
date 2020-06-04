package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.upms.UserMenuEntity;
import com.jiqunar.light.service.upms.UserMenuService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 用户菜单 前端控制器
 *
 * @author auto generator
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/UserMenuEntity")
@Api(tags = "用户菜单相关接口")
public class UserMenuController extends BaseController {
    @Autowired
    private UserMenuService userMenuService;

    /**
     * 新增用户菜单
     *
     * @param userMenuEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增用户菜单")
    public BaseResponse save(@RequestBody UserMenuEntity userMenuEntity) {
        return BaseResponse.success(userMenuService.save(userMenuEntity));
    }

    /**
     * 删除用户菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除用户菜单")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(userMenuService.removeById(id));
    }

    /**
     * 修改用户菜单
     *
     * @param userMenuEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改用户菜单")
    public BaseResponse update(@RequestBody UserMenuEntity userMenuEntity) {
        return BaseResponse.success(userMenuService.updateById(userMenuEntity));
    }

    /**
     * 查看所有用户菜单
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有用户菜单")
    public BaseResponse list() {
        return BaseResponse.success(userMenuService.list());
    }

    /**
     * 查看单个用户菜单
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个用户菜单")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(userMenuService.getById(id));
    }

    /**
     * 查看单个用户菜单
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个用户菜单")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(userMenuService.getById(id));
    }

    /**
     * 分页查看用户菜单
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看用户菜单")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(userMenuService.page(request));
    }
}