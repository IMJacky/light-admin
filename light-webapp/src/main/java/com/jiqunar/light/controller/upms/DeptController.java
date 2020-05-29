package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.upms.DeptEntity;
import com.jiqunar.light.service.upms.DeptService;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 部门 前端控制器
 *
 * @author auto generator
 * @since 2020-05-28
 */
@RestController
@RequestMapping("/DeptEntity")
@Api(tags = "部门相关接口")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 新增部门
     *
     * @param deptEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增部门")
    public BaseResponse save(@RequestBody DeptEntity deptEntity) {
        return BaseResponse.success(deptService.save(deptEntity));
    }

    /**
     * 删除部门
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除部门")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(deptService.removeById(id));
    }

    /**
     * 修改部门
     *
     * @param deptEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改部门")
    public BaseResponse update(@RequestBody DeptEntity deptEntity) {
        return BaseResponse.success(deptService.updateById(deptEntity));
    }

    /**
     * 查看所有部门
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有部门")
    public BaseResponse list() {
        return BaseResponse.success(deptService.list());
    }

    /**
     * 查看单个部门
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个部门")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(deptService.getById(id));
    }

    /**
     * 查看单个部门
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个部门")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(deptService.getById(id));
    }

    /**
     * 分页查看部门
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看部门")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(deptService.page(request));
    }
}