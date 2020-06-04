package com.jiqunar.light.model.entity.upms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiqunar.light.model.entity.BaseEntity;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lu_user")
@ApiModel(value = "UserEntity对象", description = "用户")
public class UserEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    @TableField("user_name")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @TableField("password")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    @TableField("nick_name")
    private String nickName;

    /**
     * 头像地址
     */
    @ApiModelProperty(value = "头像地址")
    @TableField("avatar_url")
    private String avatarUrl;

    /**
     * 性别（0未知，1男，2女）
     */
    @ApiModelProperty(value = "性别（0未知，1男，2女）")
    @TableField("gender")
    private Integer gender;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    @TableField("phone")
    private String phone;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    @TableField("email")
    private String email;

    /**
     * 部门Id
     */
    @ApiModelProperty(value = "部门Id")
    @TableField("dept_id")
    private Long deptId;

    /**
     * 岗位Id
     */
    @ApiModelProperty(value = "岗位Id")
    @TableField("job_id")
    private Long jobId;

    /**
     * 最近访问时间
     */
    @ApiModelProperty(value = "最近访问时间")
    @TableField("visit_date")
    private LocalDateTime visitDate;


}
