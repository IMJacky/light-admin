package com.jiqunar.light.controller.upms;

import com.jiqunar.light.controller.BaseController;
import com.jiqunar.light.model.request.upms.ExecuteSqlRequest;
import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.service.upms.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 公共控制器
 *
 * @author jieguang.wang
 * @date 2020/9/8 10:01
 */
@RestController
@RequestMapping("common")
@Api(tags = "公共相关接口")
@Validated
public class CommonController extends BaseController {
    @Autowired
    private CommonService commonService;

    /**
     * 执行sql语句
     *
     * @param request
     * @return
     */
    @PostMapping("executeSql")
    @ApiOperation("执行sql语句")
    public BaseResponse<Object> executeSql(@RequestBody @Validated ExecuteSqlRequest request) {
        return BaseResponse.success(commonService.executeSql(request));
    }
}
