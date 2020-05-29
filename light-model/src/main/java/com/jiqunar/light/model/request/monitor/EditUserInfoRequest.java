package com.jiqunar.light.model.request.monitor;

import com.jiqunar.light.model.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * 编辑用户信息
 *
 * @author jieguang.wang
 * @date 2020/5/7 16:12
 */
@Data
public class EditUserInfoRequest extends BaseRequest {
    /**
     * 主键Id
     */
    private Long id;

    /**
     * 工号
     */
    @NotNull(message = "工号不能为空")
    private String userJobNumber;

    /**
     * 姓名
     */
    @NotNull(message = "姓名不能为空")
    private String userName;

    /**
     * 手机号
     */
    @NotNull(message = "手机号不能为空")
    private String mobile;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式不正确")
    @NotNull(message = "邮箱不能为空")
    private String email;

    /**
     * 状态：1、有效；0：无效
     */
    @NotNull(message = "状态不能为空")
    @Min(message = "状态只能是0或者1", value = 0)
    @Max(message = "状态只能是0或者1", value = 1)
    private Integer rowState;
}
