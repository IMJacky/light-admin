package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.request.upms.ExecuteSqlRequest;

/**
 * 公共服务接口定义
 * @author jieguang.wang
 * @date 2020/9/8 10:02
 */
public interface CommonService {
    /**
     * 执行sql
     * @param request
     * @return
     */
    Object executeSql(ExecuteSqlRequest request);
}
