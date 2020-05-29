package com.jiqunar.light.model.request.monitor;

import com.jiqunar.light.model.request.BaseRequest;
import lombok.Data;

/**
 * 删除用户信息请求参数
 * @author jieguang.wang
 * @date 2020/5/7 16:02
 */
@Data
public class DeleteUserInfoRequest extends BaseRequest {
    /**
     * 主键Id
     */
    private Long id;
}
