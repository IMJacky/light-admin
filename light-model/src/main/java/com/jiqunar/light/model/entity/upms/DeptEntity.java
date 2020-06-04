package com.jiqunar.light.model.entity.upms;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiqunar.light.model.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 部门
 *
 * @author auto generator
 * @since 2020-06-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("lu_dept")
@ApiModel(value = "DeptEntity对象", description = "部门")
public class DeptEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 部门名称
     */
    @ApiModelProperty(value = "部门名称")
    @TableField("dept_name")
    private String deptName;

    /**
     * 父级部门Id
     */
    @ApiModelProperty(value = "父级部门Id")
    @TableField("parent_dept_id")
    private Long parentDeptId;


}
