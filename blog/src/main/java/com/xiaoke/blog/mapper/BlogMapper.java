package com.xiaoke.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoke.blog.entity.Blog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xiaoke.blog.VO.BlogUserTabSortVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Ma Ke
 * @since 2020-06-28
 */
public interface BlogMapper extends BaseMapper<Blog> {

    /**
     *  得到博客贡献数
     * @param startTime
     * @param endTime
     * @return
     */
    @Select("SELECT DISTINCT\n" +
            "\tDATE_FORMAT( created, '%Y-%m-%d' ) DATE,\n" +
            "\tCOUNT( id ) COUNT \n" +
            "FROM\n" +
            "\tblog \n" +
            "WHERE\n" +
            "\tcreated > #{startTime} && created < #{endTime} \n" +
            "GROUP BY\n" +
            "\tDATE_FORMAT( created, '%Y-%m-%d' )")
    List<Map<String, Object>> getBlogContributeCount(String startTime, String endTime);

    /**
     * @param page 翻页对象，可以作为 xml 参数直接使用，传递参数 Page 即自动分页
     * @return
     */
    @Select("SELECT b.id, b.title, b.description, b.view, b.cover, b.content, u.nickname author, s.name sortName, t.NAME tabName, b.created FROM user u, tab t JOIN blog b on t.id=b.tab_id JOIN sort s on s.id=b.sort_id GROUP BY b.id ORDER BY created DESC")
    List<BlogUserTabSortVO> getBlogList(Page page);

    /**
     *  得到博客详情
     * @param id
     * @return
     */
    @Select("SELECT b.id, b.title, b.description, b.view, b.content,b.cover, u.nickname author, s.name sortName, t.NAME tabName, b.created FROM user u, tab t JOIN blog b on t.id=b.tab_id JOIN sort s on s.id=b.sort_id GROUP BY b.id HAVING b.id=#{id}")
    Map<String, Object> getBlogDetail(Integer id);

    /**
     * 通过博客分类得到博客列表
     * @param sortName
     * @return
     */
    @Select("SELECT b.id, b.title, b.description, b.view, b.cover, b.content, u.nickname author, s.name sortName, t.NAME tabName, b.created FROM user u, tab t JOIN blog b on t.id=b.tab_id JOIN sort s on s.id=b.sort_id GROUP BY b.id HAVING s.name=\'${sortName}\' ORDER BY created DESC")
    List<BlogUserTabSortVO> getBlogBySort(Page page, String sortName);

    /**
     * 通过博客标签得到博客列表
     * @param tagName
     * @return
     */
    @Select("SELECT b.id, b.title, b.description, b.view, b.cover, b.content, u.nickname author, s.name sortName, t.NAME tabName, b.created FROM user u, tab t JOIN blog b on t.id=b.tab_id JOIN sort s on s.id=b.sort_id GROUP BY b.id HAVING t.name=\'${tagName}\' ORDER BY created DESC")
    List<BlogUserTabSortVO> getBlogByTag(Page page, String tagName);

    /**
     * 通过搜索获得博客列表
     * @param page
     * @param title
     * @param sortName
     * @return
     */
    @Select("SELECT b.id, b.title, b.description, b.view, b.cover, b.content, u.nickname author, s.name sortName, t.NAME tabName, b.created FROM user u, tab t JOIN blog b on t.id=b.tab_id JOIN sort s on s.id=b.sort_id GROUP BY b.id HAVING b.title LIKE \"%${title}%\" and s.name LIKE \"%${sortName}%\" ORDER BY created DESC")
    List<BlogUserTabSortVO> getBlogBySearch(Page page, String title, String sortName);

    /**
     * 获得博客列表
     * @return
     */
    @Select("SELECT b.id, b.title, b.description, b.view, b.cover, b.content, u.nickname author, s.name sortName, t.NAME tabName, b.created FROM user u, tab t JOIN blog b on t.id=b.tab_id JOIN sort s on s.id=b.sort_id GROUP BY b.id ORDER BY created DESC")
    List<BlogUserTabSortVO> getBlogList();

    /**
     *  通过浏览量查询博客
     * @return
     */
    @Select("SELECT b.id, b.title, b.description, b.view, b.cover, b.content, u.nickname author, s.name sortName, t.NAME tabName, b.created FROM user u, tab t JOIN blog b on t.id=b.tab_id JOIN sort s on s.id=b.sort_id GROUP BY b.id ORDER BY view DESC LIMIT 0,5")
    List<BlogUserTabSortVO> getBlogByView();



}
