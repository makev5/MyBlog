package com.xiaoke.blog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoke.blog.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xiaoke.blog.VO.BlogUserTabSortVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ma Ke
 * @since 2020-06-28
 */
public interface BlogService extends IService<Blog> {

    /**
     * 得到博客列表
     * @param page
     * @return
     */
    Page<BlogUserTabSortVO> getBlogList(Page<BlogUserTabSortVO> page);

    /**
     * 通过搜索获得博客列表
     * @param page
     * @param title
     * @param sortName
     * @return
     */
    Page<BlogUserTabSortVO> getBlogBySearch(Page<BlogUserTabSortVO> page, String title, String sortName);

    /**
     *  通过博客标签得到博客列表
     * @param page
     * @param tagName
     * @return
     */
    Page<BlogUserTabSortVO> getBlogByTag(Page<BlogUserTabSortVO> page, String tagName);

    /**
     *  通过博客分类得到博客列表
     * @param page
     * @param sortName
     * @return
     */
    Page<BlogUserTabSortVO> getBlogBySort(Page<BlogUserTabSortVO> page, String sortName);


}
