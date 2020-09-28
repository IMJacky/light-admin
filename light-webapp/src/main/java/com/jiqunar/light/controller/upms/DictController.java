package com.jiqunar.light.controller.upms;

import com.jiqunar.light.controller.BaseController;
import com.jiqunar.light.model.entity.upms.DictEntity;
import com.jiqunar.light.model.request.upms.DictConfigRequest;
import com.jiqunar.light.model.request.upms.DictConfigSaveRequest;
import com.jiqunar.light.model.request.upms.DictEditRequest;
import com.jiqunar.light.model.request.upms.DictListRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.service.upms.DictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 字典 前端控制器
 *
 * @author auto generator
 * @since 2020-09-25
 */
@RestController
@RequestMapping("/DictEntity")
@Api(tags = "字典相关接口")
public class DictController extends BaseController {
    @Autowired
    private DictService dictService;

    /**
     * 新增字典
     *
     * @param dictEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增字典")
    public BaseResponse save(@RequestBody DictEntity dictEntity) {
        return BaseResponse.success(dictService.save(dictEntity));
    }

    /**
     * 删除字典
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除字典")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(dictService.removeById(id));
    }

    /**
     * 修改字典
     *
     * @param dictEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改字典")
    public BaseResponse update(@RequestBody DictEntity dictEntity) {
        return BaseResponse.success(dictService.updateById(dictEntity));
    }

    /**
     * 查看所有字典
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有字典")
    public BaseResponse list() {
        return BaseResponse.success(dictService.list());
    }

    /**
     * 查看单个字典
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个字典")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(dictService.getById(id));
    }

    /**
     * 查看单个字典
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个字典")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(dictService.getById(id));
    }

    /**
     * 分页查看字典
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看字典")
    public BaseResponse page(@RequestBody DictListRequest request) {
        return BaseResponse.success(dictService.page(request));
    }

    /**
     * 编辑字典
     *
     * @param request
     * @return
     */
    @PostMapping("/edit")
    @ApiOperation("编辑字典")
    public BaseResponse edit(@RequestBody DictEditRequest request) {
        return BaseResponse.success(dictService.edit(request));
    }

    /**
     * 获取字典配置
     *
     * @param request
     * @return
     */
    @PostMapping("/dictConfig")
    @ApiOperation("获取字典配置")
    public BaseResponse dictConfig(@RequestBody DictConfigRequest request) {
        return BaseResponse.success(dictService.dictConfig(request));
    }

    /**
     * 保存字典配置
     *
     * @param request
     * @return
     */
    @PostMapping("/dictConfigSave")
    @ApiOperation("保存字典配置")
    public BaseResponse dictConfigSave(@RequestBody DictConfigSaveRequest request) {
        return BaseResponse.success(dictService.dictConfigSave(request));
    }
}