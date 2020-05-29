package com.jiqunar.light.service.upms;

import com.jiqunar.light.model.entity.upms.MenuEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 菜单 服务类
 *
 * @author auto generator
 * @since 2020-05-28
 */
public interface MenuService extends IService<MenuEntity> {
    /**
     * 分页获取菜单
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
