package com.jiqunar.light.controller.upms;

import com.jiqunar.light.model.response.BaseResponse;
import com.jiqunar.light.model.response.upms.WorkplaceResponse;
import com.jiqunar.light.service.upms.WorkplaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jieguang.wang
 * @date 2020/9/7 14:20
 */
@RestController
@RequestMapping("/workplace")
@Api(tags = "工作台相关接口")
@Validated
public class WorkplaceController {
    @Autowired
    private WorkplaceService workplaceService;

    /**
     * 工作台详情
     *
     * @return
     */
    @GetMapping("/getWorkplace")
    @ApiOperation("获取工作台详情")
    public BaseResponse<WorkplaceResponse> getWorkplace() {
        return BaseResponse.success(workplaceService.getWorkplace());
    }
}
