package com.jiqunar.light.controller.moonlight;

import com.jiqunar.light.model.request.BaseWxRequest;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.request.moonlight.WxLoginRequest;
import com.jiqunar.light.model.response.BaseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.jiqunar.light.model.entity.moonlight.WxUserEntity;
import com.jiqunar.light.service.moonlight.WxUserService;
import org.springframework.web.bind.annotation.RestController;
import com.jiqunar.light.controller.BaseController;

import java.util.List;

/**
 * 微信用户信息 前端控制器
 *
 * @author auto generator
 * @since 2020-07-28
 */
@RestController
@RequestMapping("/WxUserEntity")
@Api(tags = "微信用户信息相关接口")
public class WxUserController extends BaseController {
    @Autowired
    private WxUserService wxUserService;

    /**
     * 新增微信用户信息
     *
     * @param wxUserEntity
     * @return
     */
    @PutMapping("/add")
    @ApiOperation("新增微信用户信息")
    public BaseResponse save(@RequestBody WxUserEntity wxUserEntity) {
        return BaseResponse.success(wxUserService.save(wxUserEntity));
    }

    /**
     * 删除微信用户信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    @ApiOperation("删除微信用户信息")
    public BaseResponse delete(@PathVariable Long id) {
        return BaseResponse.success(wxUserService.removeById(id));
    }

    /**
     * 修改微信用户信息
     *
     * @param wxUserEntity
     * @return
     */
    @PostMapping("/update")
    @ApiOperation("修改微信用户信息")
    public BaseResponse update(@RequestBody WxUserEntity wxUserEntity) {
        return BaseResponse.success(wxUserService.updateById(wxUserEntity));
    }

    /**
     * 查看所有微信用户信息
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看所有微信用户信息")
    public BaseResponse list() {
        return BaseResponse.success(wxUserService.list());
    }

    /**
     * 查看单个微信用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation("查看单个微信用户信息")
    public BaseResponse getOne(@PathVariable Long id) {
        return BaseResponse.success(wxUserService.getById(id));
    }

    /**
     * 查看单个微信用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/get")
    @ApiOperation("查看单个微信用户信息")
    public BaseResponse get(@RequestParam Long id) {
        return BaseResponse.success(wxUserService.getById(id));
    }

    /**
     * 分页查看微信用户信息
     *
     * @return
     */
    @PostMapping("/page")
    @ApiOperation("分页查看微信用户信息")
    public BaseResponse page(@RequestBody PageRequest request) {
        return BaseResponse.success(wxUserService.page(request));
    }

    @PostMapping("/wxlogin")
    @ApiOperation("微信登录")
    public BaseResponse wxLogin(@RequestBody WxLoginRequest request) {
        return wxUserService.wxLogin(request);
    }

    @PostMapping("/visitrecord")
    @ApiOperation("新增访问记录")
    public BaseResponse addVisitRecord(@RequestBody BaseWxRequest request) {
        return wxUserService.addVisitRecord(request);
    }
}