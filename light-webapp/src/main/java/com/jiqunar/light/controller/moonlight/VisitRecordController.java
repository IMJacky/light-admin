package com.jiqunar.light.controller.moonlight;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.moonlight.VisitRecordEntity;
import com.jiqunar.light.service.moonlight.VisitRecordService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 访问记录 前端控制器
 *
 * @author auto generator
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/VisitRecordEntity")
@Api(tags = "访问记录相关接口")
public class VisitRecordController extends BaseController {
    @Autowired
    private VisitRecordService visitRecordService;

    /**
     * 新增访问记录
     *
     * @param visitRecordEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增访问记录")
    public BaseResponse save(@RequestBody VisitRecordEntity visitRecordEntity) {
        return BaseResponse.success(visitRecordService.save(visitRecordEntity));
    }

    /**
     * 删除访问记录
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除访问记录")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(visitRecordService.removeById(id));
    }

    /**
     * 修改访问记录
     *
     * @param visitRecordEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改访问记录")
    public BaseResponse update(@RequestBody VisitRecordEntity visitRecordEntity) {
        return BaseResponse.success(visitRecordService.updateById(visitRecordEntity));
    }

    /**
     * 查看所有访问记录
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有访问记录")
    public BaseResponse list() {
        return BaseResponse.success(visitRecordService.list());
    }

    /**
     * 查看单个访问记录
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个访问记录")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(visitRecordService.getById(id));
    }

    /**
     * 查看单个访问记录
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个访问记录")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(visitRecordService.getById(id));
    }

    /**
     * 分页查看访问记录
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看访问记录")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(visitRecordService.page(request));
    }
}