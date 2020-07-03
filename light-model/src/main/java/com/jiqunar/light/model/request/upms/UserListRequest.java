package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.PageRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户列表请求参数
 * @author jieguang.wang
 * @date 2020/7/3 11:39
 */
@Data
public class UserListRequest extends PageRequest {
    /**
     * Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;
}
