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
 * 字典
 *
 * @author auto generator
 * @since 2020-09-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("lu_dict")
@ApiModel(value = "DictEntity对象", description = "字典")
public class DictEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 字典编码
     */
    @ApiModelProperty(value = "字典编码")
    @TableField("dict_code")
    private String dictCode;

    /**
     * 字典名称
     */
    @ApiModelProperty(value = "字典名称")
    @TableField("dict_name")
    private String dictName;

    /**
     * 父级字典编码
     */
    @ApiModelProperty(value = "父级字典编码")
    @TableField("parent_dict_code")
    private String parentDictCode;


}
