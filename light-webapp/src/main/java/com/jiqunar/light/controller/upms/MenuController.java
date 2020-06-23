package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.request.upms.MenuListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.upms.MenuEntity;
import com.jiqunar.light.service.upms.MenuService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 菜单 前端控制器
 *
 * @author auto generator
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/MenuEntity")
@Api(tags = "菜单相关接口")
public class MenuController extends BaseController {
    @Autowired
    private MenuService menuService;

    /**
     * 新增菜单
     *
     * @param menuEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增菜单")
    public BaseResponse save(@RequestBody MenuEntity menuEntity) {
        return BaseResponse.success(menuService.save(menuEntity));
    }

    /**
     * 删除菜单
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除菜单")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(menuService.removeById(id));
    }

    /**
     * 修改菜单
     *
     * @param menuEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改菜单")
    public BaseResponse update(@RequestBody MenuEntity menuEntity) {
        return BaseResponse.success(menuService.updateById(menuEntity));
    }

    /**
     * 查看所有菜单
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有菜单")
    public BaseResponse list() {
        return BaseResponse.success(menuService.list());
    }

    /**
     * 查看单个菜单
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个菜单")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(menuService.getById(id));
    }

    /**
     * 查看单个菜单
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个菜单")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(menuService.getById(id));
    }

    /**
     * 分页查看菜单
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看菜单")
    public BaseResponse page(@RequestBody MenuListRequest request) {
        return BaseResponse.success(menuService.page(request));
    }
}