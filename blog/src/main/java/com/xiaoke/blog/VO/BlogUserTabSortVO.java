package com.xiaoke.blog.VO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;
/**
 * @Document注解之后，默认情况下这个实体中所有的属性都会被建立索引、并且分词。
 * 我们通过@Field注解来进行详细的指定，如果没有特殊需求，那么只需要添加@Document即可。
 * @Field这里ik_max_word是按最细粒度分词，ik_smart是按最粗粒度分词。
 * 在搜索的时候用粗粒度，在写入的时候用细粒度。
 * @author Ma Ke
 * @since 2020-07-16
 */
@Data
@Document(indexName = "blog_search",type = "blog")
public class BlogUserTabSortVO implements Serializable {

    @ApiModelProperty(value = "博客ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "用户名")
    private String author;

    @ApiModelProperty(value = "标题")
    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_max_word")
    private String title;

    @ApiModelProperty(value = "摘要")
    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_max_word")
    private String description;

    @ApiModelProperty(value = "博客封面")
    private String cover;

    @ApiModelProperty(value = "内容")
    private String content;

    @ApiModelProperty(value = "文章浏览量")
    private  Integer view;

    @ApiModelProperty(value = "标签名字")
    private String tabName;

    @ApiModelProperty(value = "分类名字")
    private String sortName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime created;

}
