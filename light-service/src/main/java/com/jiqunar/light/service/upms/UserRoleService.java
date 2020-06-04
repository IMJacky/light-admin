package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.UserRoleEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 用户角色 服务类
 *
 * @author auto generator
 * @since 2020-06-04
 */
public interface UserRoleService extends IService<UserRoleEntity> {
    /**
     * 分页获取用户角色
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
