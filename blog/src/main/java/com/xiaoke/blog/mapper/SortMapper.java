package com.xiaoke.blog.mapper;

import com.xiaoke.blog.entity.Sort;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ma Ke
 * @since 2020-07-01
 */
public interface SortMapper extends BaseMapper<Sort> {

    /**
     *  通过分类得到博客数
     * @return
     */
    @Select("SELECT s.id,s.name,s.img,COUNT(b.sort_id) value FROM sort s LEFT JOIN blog b on s.id=b.sort_id GROUP BY s.id")
    List<Map<String,String>> getBlogCountBySort();

}
