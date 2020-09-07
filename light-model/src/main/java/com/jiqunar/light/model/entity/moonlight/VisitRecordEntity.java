package com.jiqunar.light.model.entity.moonlight;

import com.baomidou.mybatisplus.annotation.TableName;
import com.jiqunar.light.model.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 访问记录
 *
 * @author auto generator
 * @since 2020-07-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("visit_record")
@ApiModel(value = "VisitRecordEntity对象", description = "访问记录")
public class VisitRecordEntity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 微信openid
     */
    @ApiModelProperty(value = "微信openid")
    @TableField("open_id")
    private String openId;

    /**
     * 微信unionid
     */
    @ApiModelProperty(value = "微信unionid")
    @TableField("union_id")
    private String unionId;


}
