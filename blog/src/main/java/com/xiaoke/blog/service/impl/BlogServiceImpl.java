package com.xiaoke.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoke.blog.entity.Blog;
import com.xiaoke.blog.VO.BlogUserTabSortVO;
import com.xiaoke.blog.mapper.BlogMapper;
import com.xiaoke.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Ma Ke
 * @since 2020-06-28
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

    @Autowired
    BlogMapper blogMapper;

    @Override
    public Page<BlogUserTabSortVO> getBlogList(Page<BlogUserTabSortVO> page) {
        return page.setRecords(this.baseMapper.getBlogList(page));
    }

    @Override
    public Page<BlogUserTabSortVO> getBlogBySearch(Page<BlogUserTabSortVO> page, String title, String sortName) {
        return page.setRecords(this.baseMapper.getBlogBySearch(page, title, sortName));
    }

    @Override
    public Page<BlogUserTabSortVO> getBlogByTag(Page<BlogUserTabSortVO> page, String tagName) {
        return page.setRecords(this.baseMapper.getBlogByTag(page, tagName));
    }

    @Override
    public Page<BlogUserTabSortVO> getBlogBySort(Page<BlogUserTabSortVO> page, String sortName) {
        return page.setRecords(this.baseMapper.getBlogBySort(page, sortName));
    }
}
