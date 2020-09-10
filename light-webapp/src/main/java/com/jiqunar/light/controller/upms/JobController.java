package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.request.upms.JobEditRequest;
import com.jiqunar.light.model.request.upms.JobListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.upms.JobEntity;
import com.jiqunar.light.service.upms.JobService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

/**
 * 岗位 前端控制器
 *
 * @author auto generator
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/JobEntity")
@Api(tags = "岗位相关接口")
public class JobController extends BaseController {
    @Autowired
    private JobService jobService;

    /**
     * 新增岗位
     *
     * @param jobEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增岗位")
    public BaseResponse save(@RequestBody JobEntity jobEntity) {
        return BaseResponse.success(jobService.save(jobEntity));
    }

    /**
     * 删除岗位
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除岗位")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(jobService.removeById(id));
    }

    /**
     * 修改岗位
     *
     * @param jobEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改岗位")
    public BaseResponse update(@RequestBody JobEntity jobEntity) {
        return BaseResponse.success(jobService.updateById(jobEntity));
    }

    /**
     * 查看所有岗位
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有岗位")
    public BaseResponse list() {
        return BaseResponse.success(jobService.list());
    }

    /**
     * 查看单个岗位
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个岗位")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(jobService.getById(id));
    }

    /**
     * 查看单个岗位
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个岗位")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(jobService.getById(id));
    }

    /**
     * 分页查看岗位
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看岗位")
    public BaseResponse page(@RequestBody JobListRequest request) {
        return BaseResponse.success(jobService.page(request));
    }

    /**
     * 编辑岗位
     *
     * @param request
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation("编辑岗位")
    public BaseResponse edit(@RequestBody JobEditRequest request) {
        return BaseResponse.success(jobService.edit(request));
    }
}