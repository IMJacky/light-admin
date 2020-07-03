package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.upms.UserEditRequest;
import com.jiqunar.light.model.request.upms.UserListRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 用户 服务类
 *
 * @author auto generator
 * @since 2020-06-04
 */
public interface UserService extends IService<UserEntity> {
    /**
     * 分页获取用户
     *
     * @param request
     * @return
     */
    PageResponse page(UserListRequest request);

    /**
     * 编辑用户
     *
     * @param request
     * @return
     */
    Long edit(UserEditRequest request);
}
