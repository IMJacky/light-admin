package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.UserMenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 用户菜单 服务类
 *
 * @author auto generator
 * @since 2020-05-28
 */
public interface UserMenuService extends IService<UserMenuEntity> {
    /**
     * 分页获取用户菜单
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
