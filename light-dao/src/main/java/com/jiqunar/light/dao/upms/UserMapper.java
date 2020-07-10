package com.jiqunar.light.dao.upms;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jiqunar.light.model.entity.upms.UserEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jiqunar.light.model.request.upms.UserListRequest;
import com.jiqunar.light.model.response.upms.UserListResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户 Mapper 接口
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 获取用户列表
     *
     * @param page
     * @param request
     * @return
     */
    IPage<UserListResponse> userList(Page page, UserListRequest request);
}
