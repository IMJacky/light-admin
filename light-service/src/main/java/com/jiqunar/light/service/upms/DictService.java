package com.jiqunar.light.service.upms;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiqunar.light.model.entity.upms.DictEntity;
import com.jiqunar.light.model.request.upms.DictConfigRequest;
import com.jiqunar.light.model.request.upms.DictConfigSaveRequest;
import com.jiqunar.light.model.request.upms.DictEditRequest;
import com.jiqunar.light.model.request.upms.DictListRequest;
import com.jiqunar.light.model.response.PageResponse;
import com.jiqunar.light.model.response.upms.DictConfigResponse;

/**
 * 字典 服务类
 *
 * @author auto generator
 * @since 2020-09-25
 */
public interface DictService extends IService<DictEntity> {
    /**
     * 分页获取岗位
     *
     * @param request
     * @return
     */
    PageResponse page(DictListRequest request);

    /**
     * 编辑岗位
     *
     * @param request
     * @return
     */
    Long edit(DictEditRequest request);

    /**
     * 获取字典配置
     * @param request
     * @return
     */
    DictConfigResponse dictConfig(DictConfigRequest request);

    /**
     * 保存字典配置
     * @param request
     * @return
     */
    Boolean dictConfigSave(DictConfigSaveRequest request);
}
