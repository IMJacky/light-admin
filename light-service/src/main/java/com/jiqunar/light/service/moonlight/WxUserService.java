package com.jiqunar.light.service.moonlight;

import com.jiqunar.light.model.entity.moonlight.WxUserEntity;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.request.PageRequest;
import com.jiqunar.light.model.response.PageResponse;

/**
 * 微信用户信息 服务类
 *
 * @author auto generator
 * @since 2020-07-28
 */
public interface WxUserService extends IService<WxUserEntity> {
    /**
     * 分页获取微信用户信息
     *
     * @param request
     * @return
     */
    PageResponse page(PageRequest request);
}
