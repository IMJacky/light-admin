package com.jiqunar.light.model.response.upms;

import com.jiqunar.light.model.entity.upms.UserEntity;
import lombok.Data;

/**
 * 用户列表响应实体
 *
 * @author jieguang.wang
 * @date 2020/7/9 17:49
 */
@Data
public class UserListResponse extends UserEntity {
    /**
     * 用户id（多个用逗号分隔）
     */
    private String roleId;

    /**
     * 用户角色（多个用逗号分隔）
     */
    private String roleName;
}
