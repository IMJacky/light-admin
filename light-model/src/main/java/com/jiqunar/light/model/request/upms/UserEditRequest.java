package com.jiqunar.light.model.request.upms;

import com.jiqunar.light.model.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户编辑请求参数
 *
 * @author jieguang.wang
 * @date 2020/7/3 11:39
 */
@Data
public class UserEditRequest extends BaseRequest {
    /**
     * Id
     */
    @ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    private String avatarUrl;

    /**
     * 性别（0未知，1男，2女）
     */
    @ApiModelProperty(value = "性别（0未知，1男，2女）")
    private Integer gender;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 部门Id
     */
    @ApiModelProperty(value = "部门Id")
    private long deptId;

    /**
     * 部门Id父子级关系
     */
    @ApiModelProperty(value = "部门Id父子级关系")
    private List<Long> deptIdList;

    /**
     * 岗位Id
     */
    @ApiModelProperty(value = "岗位Id")
    private long jobId;

    /**
     * 所属角色
     */
    @ApiModelProperty(value = "所属角色")
    private List<Long> roleId;
}
