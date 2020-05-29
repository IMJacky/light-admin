package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 用户 服务类
 *
 * @author auto generator
 * @since 2020-05-28
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 分页获取用户
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
