package com.xiaoke.blog.mapper;

import com.xiaoke.blog.entity.Tab;
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
public interface TabMapper extends BaseMapper<Tab> {

    /**
     *  通过标签得到博客数
     * @return
     */
    @Select("SELECT t.id,t.name,t.img,COUNT(b.tab_id) value FROM tab t LEFT JOIN blog b on t.id=b.tab_id GROUP BY t.id")
    List<Map<String,String>> getBlogCountByTag();
}
