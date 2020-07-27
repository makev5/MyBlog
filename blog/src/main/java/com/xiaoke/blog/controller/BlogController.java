package com.xiaoke.blog.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xiaoke.blog.VO.BlogUserTabSortVO;
import com.xiaoke.blog.common.Result;
import com.xiaoke.blog.entity.Blog;
import com.xiaoke.blog.entity.User;
import com.xiaoke.blog.mapper.BlogMapper;
import com.xiaoke.blog.service.BlogService;
import com.xiaoke.blog.service.UserService;
import com.xiaoke.blog.util.Myutil;
import com.xiaoke.blog.util.RedisUtil;
import com.xiaoke.blog.util.RedisUtils;
import com.xiaoke.blog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Ma Ke
 * @since 2020-06-28
 */
@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    BlogService blogService;

    @Autowired
    BlogMapper blogMapper;

    @Autowired
    UserService userService;

    @Resource
    private RedisUtil redisUtil;

    /**
     * 获取博客列表
     * @param currentPage
     * @return
     */
    @GetMapping("/list")
    public Result blogList(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize) throws IOException {
        Page<BlogUserTabSortVO> blogList = blogService.getBlogList(new Page<>(currentPage, pageSize));
        return Result.success(blogList);
    }

    /**
     * 博客详情 (前端)
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result blogDetail(@PathVariable(name = "id") Integer id){
        Blog blog = blogMapper.selectById(id);
        blog.setView(blog.getView() + 1);
        blogService.saveOrUpdate(blog);
        Map<String, Object> blogDetail = blogMapper.getBlogDetail(id);
        return Result.success(blogDetail);
    }

    /**
     * 博客详情（后端）
     * @param id
     * @return
     */
    @GetMapping("/detail/{id}")
    public Result getBlog(@PathVariable(name = "id") Integer id){
       Blog blog = blogService.getById(id);
       Assert.notNull(blog, "该博客已被删除");
       return Result.success(blog);
    }

    /**
     *  通过标题或者 分类搜索
     * @param currentPage
     * @param pageSize
     * @param title
     * @param sortName
     * @return
     */
    @GetMapping("/search")
    public Result getBlogBySearch(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "5") Integer pageSize, String title, String sortName){
        Page<BlogUserTabSortVO> blogBySearch = blogService.getBlogBySearch(new Page<>(currentPage, pageSize), title, sortName);
        return  Result.success(blogBySearch);
    }

    /**
     * 增加或修改博客
     * @param blog
     * @return
     */
    @RequiresPermissions(logical = Logical.AND, value = {"user:edit"})
    @PostMapping("/edit")
    public Result editBlog(@Validated @RequestBody Blog blog){
        Blog temp = null;
        User user = userService.getOne(new QueryWrapper<User>().eq("username", ShiroUtil.getProfile()));
        if (blog.getId() != null){
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的博客
            Assert.isTrue(temp.getUserId() == user.getId(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(user.getId());
            temp.setCreated(new Timestamp(System.currentTimeMillis()));
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created");
        blogService.saveOrUpdate(temp);
        return Result.success(null);
    }


    @DeleteMapping("delete/{id}")
    @RequiresAuthentication
    public Result deleteBlog(@PathVariable(name = "id") Integer id){
        int count = blogMapper.deleteById(id);
        if (count>0){
            return Result.success(null);
        }
        return Result.success("博客不存在");
    }

    /**
     *  博客贡献数
     * @return
     */
    @GetMapping("/contributeCount")
    public Result getBlogContributeCount(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取今天结束时间
        String endTime = sdf.format(new Date());

        // 获取365天前的日期
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -1);
        Date temp = c.getTime();

        String startTime = sdf.format(temp);

        List<Map<String, Object>> blogContributeMap = blogMapper.getBlogContributeCount(startTime, endTime);

        List<String> dateList = Myutil.getDayBetweenDates(startTime, endTime);

        Map<String, Object> dateMap = new HashMap<>();

        for(Map<String, Object> itemMap : blogContributeMap) {

            dateMap.put(itemMap.get("DATE").toString(), itemMap.get("COUNT"));
        }
        List<List<Object>> resultList = new ArrayList<>();
        for(String item : dateList) {
            Integer count = 0;
            if(dateMap.get(item) != null) {
                count = Integer.valueOf(dateMap.get(item).toString());
            }
            List<Object> objectList = new ArrayList<>();
            objectList.add(item);
            objectList.add(count);
            resultList.add(objectList);
        }

        Map<String, Object> resultMap = new HashMap<>();
        List<String> contributeDateList = new ArrayList<>();
        contributeDateList.add(startTime);
        contributeDateList.add(endTime);
        resultMap.put("contributeDate", contributeDateList);
        resultMap.put("blogContributeCount", resultList);

        return Result.success(MapUtil.builder()
                .put("data", resultMap)
                .map()
        );
    }


    /**
     * 通过博客分类得到博客列表
     * @param sortName
     * @return
     */
    @GetMapping("/getBlogBySort")
    public Result getBlogBySort(@RequestParam(defaultValue = "1") Integer currentPage,
                                @RequestParam(defaultValue = "5") Integer pageSize, String sortName){
        Page<BlogUserTabSortVO> blogBySort = blogService.getBlogBySort(new Page<>(currentPage, pageSize), sortName);
        return Result.success(blogBySort);
    }

    /**
     * 通过博客标签得到博客列表
     * @param tagName
     * @return
     */
    @GetMapping("/getBlogByTag")
    public Result getBlogByTag(@RequestParam(defaultValue = "1") Integer currentPage,
                               @RequestParam(defaultValue = "5") Integer pageSize, String tagName){
        Page<BlogUserTabSortVO> blogByTag = blogService.getBlogByTag(new Page<>(currentPage, pageSize), tagName);
        return Result.success(blogByTag);
    }

    /**
     * 通过浏览量查询博客
     * @return
     */
    @GetMapping("/rank")
    public Result getRank(){
        List<BlogUserTabSortVO> blogByView = blogMapper.getBlogByView();
        return Result.success(blogByView);
    }
}
