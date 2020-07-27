package com.xiaoke.blog.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Ma Ke
 * @since 2020-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Tool对象", description="")
public class Tool implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "工具ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "工具名字")
    private String name;

    @ApiModelProperty(value = "工具描述")
    private String description;

    @ApiModelProperty(value = "工具链接")
    private String url;

    @ApiModelProperty(value = "工具图片")
    private String img;
}
