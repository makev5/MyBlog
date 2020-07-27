package com.xiaoke.blog.entity;

import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *  轮播图
 * </p>
 *
 * @author Ma Ke
 * @since 2020-07-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Banner对象", description="轮播图")
public class Banner implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "轮播图ID")
    private Integer id;

    @ApiModelProperty(value = "轮播图片")
    private String img;

    @ApiModelProperty(value = "轮播URL")
    private String url;

}
