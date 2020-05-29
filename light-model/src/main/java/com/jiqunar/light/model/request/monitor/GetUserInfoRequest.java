package com.jiqunar.light.model.request.monitor;

import com.jiqunar.light.model.request.BaseRequest;
import lombok.Data;

/**
 * 获取单个用户信息请求参数
 * @author jieguang.wang
 * @date 2020/5/7 15:44
 */
@Data
public class GetUserInfoRequest extends BaseRequest {
    /**
     * 主键Id
     */
    private Long id;
}
