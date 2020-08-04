package com.jiqunar.light.controller.moonlight;

import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.request.moonlight.BillEditGetRequest;
import com.jiqunar.light.model.request.moonlight.BillEditRequest;
import com.jiqunar.light.model.request.moonlight.BillListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.moonlight.BillEntity;
import com.jiqunar.light.service.moonlight.BillService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 账单信息 前端控制器
 *
 * @author auto generator
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/BillEntity")
@Api(tags = "账单信息相关接口")
public class BillController extends BaseController {
    @Autowired
    private BillService billService;

    /**
     * 新增账单信息
     *
     * @param billEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增账单信息")
    public BaseResponse save(@RequestBody BillEntity billEntity) {
        return BaseResponse.success(billService.save(billEntity));
    }

    /**
     * 删除账单信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除账单信息")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(billService.removeById(id));
    }

    /**
     * 修改账单信息
     *
     * @param billEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改账单信息")
    public BaseResponse update(@RequestBody BillEntity billEntity) {
        return BaseResponse.success(billService.updateById(billEntity));
    }

    /**
     * 查看所有账单信息
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有账单信息")
    public BaseResponse list() {
        return BaseResponse.success(billService.list());
    }

    /**
     * 查看单个账单信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个账单信息")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(billService.getById(id));
    }

    /**
     * 查看单个账单信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个账单信息")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(billService.getById(id));
    }

    /**
     * 分页查看账单信息
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看账单信息")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(billService.page(request));
    }

    /**
     * 编辑账单信息
     *
     * @param request
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation("编辑账单信息")
    public BaseResponse save(@RequestBody BillEditRequest request) {
        return billService.editBill(request);
    }

    /**
     * 根据主键Id+用户Id查看账单信息
     *
     * @param request
     * @return
     */
    @PostMapping("/getById")
    @ApiOperation("根据主键Id+用户Id查看账单信息")
    public BaseResponse getById(@RequestBody BillEditGetRequest request) {
        return BaseResponse.success(billService.getById(request));
    }

    /**
     * 按日期范围查看账单信息
     *
     * @return
     */
    @PostMapping("/getbydate")
    @ApiOperation("按日期范围查看账单信息")
    public BaseResponse getByDate(@RequestBody BillListRequest request) {
        return BaseResponse.success(billService.getByDate(request));
    }
}