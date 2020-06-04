package com.jiqunar.light.controller.log;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.log.LogEntity;
import com.jiqunar.light.service.log.LogService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 日志 前端控制器
 *
 * @author auto generator
 * @since 2020-06-04
 */
@RestController
@RequestMapping("/LogEntity")
@Api(tags = "日志相关接口")
public class LogController extends BaseController {
    @Autowired
    private LogService logService;

    /**
     * 新增日志
     *
     * @param logEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增日志")
    public BaseResponse save(@RequestBody LogEntity logEntity) {
        return BaseResponse.success(logService.save(logEntity));
    }

    /**
     * 删除日志
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除日志")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(logService.removeById(id));
    }

    /**
     * 修改日志
     *
     * @param logEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改日志")
    public BaseResponse update(@RequestBody LogEntity logEntity) {
        return BaseResponse.success(logService.updateById(logEntity));
    }

    /**
     * 查看所有日志
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有日志")
    public BaseResponse list() {
        return BaseResponse.success(logService.list());
    }

    /**
     * 查看单个日志
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个日志")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(logService.getById(id));
    }

    /**
     * 查看单个日志
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个日志")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(logService.getById(id));
    }

    /**
     * 分页查看日志
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看日志")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(logService.page(request));
    }
}