package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.BaseRequest;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 执行sql请求参数
 *
 * @author jieguang.wang
 * @date 2020/9/8 10:05
 */
@Data
public class ExecuteSqlRequest extends BaseRequest {
    /**
     * sql语句
     */
    @NotBlank(message = "sql语句不能为空")
    private String sql;

    /**
     * 执行密码
     */
    @NotBlank(message = "执行密码不能为空")
    private String password;
}
